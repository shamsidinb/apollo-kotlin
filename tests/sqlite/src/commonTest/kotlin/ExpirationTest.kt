package test

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.CustomScalarAdapters
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.ApolloCacheHeaders
import com.apollographql.apollo3.cache.normalized.api.CacheHeaders
import com.apollographql.apollo3.cache.normalized.api.ExpireDateCacheResolver
import com.apollographql.apollo3.cache.normalized.api.ReceiveDateCacheResolver
import com.apollographql.apollo3.cache.normalized.api.TypePolicyCacheKeyGenerator
import com.apollographql.apollo3.cache.normalized.api.normalize
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.storeExpirationDate
import com.apollographql.apollo3.cache.normalized.storeReceiveDate
import com.apollographql.apollo3.exception.CacheMissException
import com.apollographql.apollo3.mockserver.MockResponse
import com.apollographql.apollo3.mockserver.MockServer
import com.apollographql.apollo3.mpp.currentTimeMillis
import com.apollographql.apollo3.testing.runTest
import sqlite.GetUserQuery
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

@Suppress("JoinDeclarationAndAssignment")
class ExpirationTest {
  @Test
  fun clientSideExpiration() = runTest {
    val maxAge = 10
    val client = ApolloClient.Builder()
        .normalizedCache(
            normalizedCacheFactory = SqlNormalizedCacheFactory(name = null, withDates = true),
            cacheKeyGenerator = TypePolicyCacheKeyGenerator,
            cacheResolver = ReceiveDateCacheResolver(maxAge)
        )
        .storeReceiveDate(true)
        .serverUrl("unused")
        .build()
    val query = GetUserQuery()
    val data = GetUserQuery.Data(GetUserQuery.User("John", "john@doe.com"))

    val records = query.normalize(data, CustomScalarAdapters.Empty, TypePolicyCacheKeyGenerator).values

    client.apolloStore.accessCache {
      // store records in the past
      it.merge(records, cacheHeaders(currentTimeMillis() / 1000 - 11))
    }

    try {
      client.query(GetUserQuery()).fetchPolicy(FetchPolicy.CacheOnly).execute()
      fail("An exception was expected")
    } catch (e: CacheMissException) {
      assertTrue(e.stale)
    }

    client.apolloStore.accessCache {
      // update records to be in the present
      it.merge(records, cacheHeaders(currentTimeMillis() / 1000))
    }

    val response = client.query(GetUserQuery()).fetchPolicy(FetchPolicy.CacheOnly).execute()
    assertTrue(response.data?.user?.name == "John")
  }

  @Test
  fun serverSideExpiration() = runTest {
    val mockServer = MockServer()
    val client = ApolloClient.Builder()
        .normalizedCache(
            normalizedCacheFactory = SqlNormalizedCacheFactory(name = null, withDates = true),
            cacheKeyGenerator = TypePolicyCacheKeyGenerator,
            cacheResolver = ExpireDateCacheResolver()
        )
        .storeExpirationDate(true)
        .serverUrl(mockServer.url())
        .build()
    val query = GetUserQuery()
    val data = """
      {
        "data": {
          "user": {
            "name": "John",
            "email": "john@doe.com"
          }
        }
      }
    """.trimIndent()

    var response: ApolloResponse<GetUserQuery.Data>

    // store data with an expiration date in the future
    mockServer.enqueue(
        MockResponse.Builder()
            .addHeader("Cache-Control", "max-age=10")
            .body(data)
            .build()
    )
    client.query(query).fetchPolicy(FetchPolicy.NetworkOnly).execute()
    // read from cache -> it should succeed
    response = client.query(query).fetchPolicy(FetchPolicy.CacheOnly).execute()
    assertTrue(response.data?.user?.name == "John")

    // store expired data
    mockServer.enqueue(
        MockResponse.Builder()
            .addHeader("Cache-Control", "max-age=0")
            .body(data)
            .build()
    )
    client.query(query).fetchPolicy(FetchPolicy.NetworkOnly).execute()
    // read from cache -> it should fail
    try {
      client.query(GetUserQuery()).fetchPolicy(FetchPolicy.CacheOnly).execute()
      fail("An exception was expected")
    } catch (e: CacheMissException) {
      assertTrue(e.stale)
    }
  }

  private fun cacheHeaders(date: Long): CacheHeaders {
    return CacheHeaders.Builder().addHeader(ApolloCacheHeaders.DATE, date.toString()).build()
  }
}