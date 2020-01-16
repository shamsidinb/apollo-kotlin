// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.union_inline_fragments;

import com.apollographql.apollo.api.Operation;
import com.apollographql.apollo.api.OperationName;
import com.apollographql.apollo.api.Query;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.ResponseField;
import com.apollographql.apollo.api.ResponseFieldMapper;
import com.apollographql.apollo.api.ResponseFieldMarshaller;
import com.apollographql.apollo.api.ResponseReader;
import com.apollographql.apollo.api.ResponseWriter;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser;
import com.apollographql.apollo.api.internal.UnmodifiableMapBuilder;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.internal.QueryDocumentMinifier;
import com.apollographql.apollo.response.ScalarTypeAdapters;
import com.example.union_inline_fragments.type.CustomType;
import com.example.union_inline_fragments.type.Episode;
import java.io.IOException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TestQuery implements Query<TestQuery.Data, Optional<TestQuery.Data>, Operation.Variables> {
  public static final String OPERATION_ID = "d917122adce28477721dc274dd7fce307cb1b714452af1df8bb26087b8ec33d0";

  public static final String QUERY_DOCUMENT = QueryDocumentMinifier.minify(
    "query TestQuery {\n"
        + "  search(text: \"test\") {\n"
        + "    __typename\n"
        + "    ... on Character {\n"
        + "      id\n"
        + "      name\n"
        + "      friends {\n"
        + "        __typename\n"
        + "        ... on Character {\n"
        + "          name\n"
        + "        }\n"
        + "        ... on Human {\n"
        + "          homePlanet\n"
        + "          friends {\n"
        + "            __typename\n"
        + "            ... on Character {\n"
        + "              firstAppearsIn\n"
        + "            }\n"
        + "          }\n"
        + "        }\n"
        + "        ... on Droid {\n"
        + "          primaryFunction\n"
        + "          friends {\n"
        + "            __typename\n"
        + "            id\n"
        + "            deprecated\n"
        + "          }\n"
        + "        }\n"
        + "      }\n"
        + "    }\n"
        + "    ... on Starship {\n"
        + "      name\n"
        + "    }\n"
        + "  }\n"
        + "}"
  );

  public static final OperationName OPERATION_NAME = new OperationName() {
    @Override
    public String name() {
      return "TestQuery";
    }
  };

  private final Operation.Variables variables;

  public TestQuery() {
    this.variables = Operation.EMPTY_VARIABLES;
  }

  @Override
  public String operationId() {
    return OPERATION_ID;
  }

  @Override
  public String queryDocument() {
    return QUERY_DOCUMENT;
  }

  @Override
  public Optional<TestQuery.Data> wrapData(TestQuery.Data data) {
    return Optional.fromNullable(data);
  }

  @Override
  public Operation.Variables variables() {
    return variables;
  }

  @Override
  public ResponseFieldMapper<TestQuery.Data> responseFieldMapper() {
    return new Data.Mapper();
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public OperationName name() {
    return OPERATION_NAME;
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source,
      @NotNull final ScalarTypeAdapters scalarTypeAdapters) throws IOException {
    return SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters);
  }

  @Override
  @NotNull
  public Response<Optional<TestQuery.Data>> parse(@NotNull final BufferedSource source) throws
      IOException {
    return parse(source, ScalarTypeAdapters.DEFAULT);
  }

  public static final class Builder {
    Builder() {
    }

    public TestQuery build() {
      return new TestQuery();
    }
  }

  public static class Data implements Operation.Data {
    static final ResponseField[] $responseFields = {
      ResponseField.forList("search", "search", new UnmodifiableMapBuilder<String, Object>(1)
      .put("text", "test")
      .build(), true, Collections.<ResponseField.Condition>emptyList())
    };

    final Optional<List<Search>> search;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Data(@Nullable List<Search> search) {
      this.search = Optional.fromNullable(search);
    }

    public Optional<List<Search>> search() {
      return this.search;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeList($responseFields[0], search.isPresent() ? search.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((Search) item).marshaller());
              }
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Data{"
          + "search=" + search
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Data) {
        Data that = (Data) o;
        return this.search.equals(that.search);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= search.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Data> {
      final Search.Mapper searchFieldMapper = new Search.Mapper();

      @Override
      public Data map(ResponseReader reader) {
        final List<Search> search = reader.readList($responseFields[0], new ResponseReader.ListReader<Search>() {
          @Override
          public Search read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Search>() {
              @Override
              public Search read(ResponseReader reader) {
                return searchFieldMapper.map(reader);
              }
            });
          }
        });
        return new Data(search);
      }
    }
  }

  public interface Search {
    @NotNull String __typename();

    ResponseFieldMarshaller marshaller();

    default <T> T visit(Visitor<T> visitor) {
      if (this instanceof AsCharacter) {
        return visitor.visit((AsCharacter) this);
      } else if (this instanceof AsStarship) {
        return visitor.visit((AsStarship) this);
      } else if (this instanceof AsSearchResult) {
        return visitor.visit((AsSearchResult) this);
      }
      return visitor.visitDefault(this);
    }

    final class Mapper implements ResponseFieldMapper<Search> {
      static final ResponseField[] $responseFields = {
        ResponseField.forFragment("__typename", "__typename", Arrays.<ResponseField.Condition>asList(
          ResponseField.Condition.typeCondition(new String[] {"Human", "Droid"})
        )),
        ResponseField.forFragment("__typename", "__typename", Arrays.<ResponseField.Condition>asList(
          ResponseField.Condition.typeCondition(new String[] {"Starship"})
        ))
      };

      final AsCharacter.Mapper asCharacterFieldMapper = new AsCharacter.Mapper();

      final AsStarship.Mapper asStarshipFieldMapper = new AsStarship.Mapper();

      final AsSearchResult.Mapper asSearchResultFieldMapper = new AsSearchResult.Mapper();

      @Override
      public Search map(ResponseReader reader) {
        final AsCharacter asCharacter = reader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsCharacter>() {
          @Override
          public AsCharacter read(ResponseReader reader) {
            return asCharacterFieldMapper.map(reader);
          }
        });
        if (asCharacter != null) {
          return asCharacter;
        }
        final AsStarship asStarship = reader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsStarship>() {
          @Override
          public AsStarship read(ResponseReader reader) {
            return asStarshipFieldMapper.map(reader);
          }
        });
        if (asStarship != null) {
          return asStarship;
        }
        return asSearchResultFieldMapper.map(reader);
      }
    }

    interface Visitor<T> {
      T visitDefault(@NotNull Search search);

      T visit(@NotNull AsCharacter asCharacter);

      T visit(@NotNull AsStarship asStarship);

      T visit(@NotNull AsSearchResult asSearchResult);
    }
  }

  public static class AsCharacter implements Search {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("friends", "friends", null, true, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String id;

    final @NotNull String name;

    final Optional<List<Friend>> friends;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsCharacter(@NotNull String __typename, @NotNull String id, @NotNull String name,
        @Nullable List<Friend> friends) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.id = Utils.checkNotNull(id, "id == null");
      this.name = Utils.checkNotNull(name, "name == null");
      this.friends = Optional.fromNullable(friends);
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The ID of the character
     */
    public @NotNull String id() {
      return this.id;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    /**
     * The friends of the character, or an empty list if they have none
     */
    public Optional<List<Friend>> friends() {
      return this.friends;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
          writer.writeString($responseFields[2], name);
          writer.writeList($responseFields[3], friends.isPresent() ? friends.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((Friend) item).marshaller());
              }
            }
          });
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsCharacter{"
          + "__typename=" + __typename + ", "
          + "id=" + id + ", "
          + "name=" + name + ", "
          + "friends=" + friends
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsCharacter) {
        AsCharacter that = (AsCharacter) o;
        return this.__typename.equals(that.__typename)
         && this.id.equals(that.id)
         && this.name.equals(that.name)
         && this.friends.equals(that.friends);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= id.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        h *= 1000003;
        h ^= friends.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsCharacter> {
      final Friend.Mapper friendFieldMapper = new Friend.Mapper();

      @Override
      public AsCharacter map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String name = reader.readString($responseFields[2]);
        final List<Friend> friends = reader.readList($responseFields[3], new ResponseReader.ListReader<Friend>() {
          @Override
          public Friend read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Friend>() {
              @Override
              public Friend read(ResponseReader reader) {
                return friendFieldMapper.map(reader);
              }
            });
          }
        });
        return new AsCharacter(__typename, id, name, friends);
      }
    }
  }

  public interface Friend {
    @NotNull String __typename();

    /**
     * The name of the character
     */
    @NotNull String name();

    ResponseFieldMarshaller marshaller();

    default <T> T visit(Visitor<T> visitor) {
      if (this instanceof AsHuman) {
        return visitor.visit((AsHuman) this);
      } else if (this instanceof AsDroid) {
        return visitor.visit((AsDroid) this);
      } else if (this instanceof AsCharacter1) {
        return visitor.visit((AsCharacter1) this);
      }
      return visitor.visitDefault(this);
    }

    final class Mapper implements ResponseFieldMapper<Friend> {
      static final ResponseField[] $responseFields = {
        ResponseField.forFragment("__typename", "__typename", Arrays.<ResponseField.Condition>asList(
          ResponseField.Condition.typeCondition(new String[] {"Human"})
        )),
        ResponseField.forFragment("__typename", "__typename", Arrays.<ResponseField.Condition>asList(
          ResponseField.Condition.typeCondition(new String[] {"Droid"})
        ))
      };

      final AsHuman.Mapper asHumanFieldMapper = new AsHuman.Mapper();

      final AsDroid.Mapper asDroidFieldMapper = new AsDroid.Mapper();

      final AsCharacter1.Mapper asCharacter1FieldMapper = new AsCharacter1.Mapper();

      @Override
      public Friend map(ResponseReader reader) {
        final AsHuman asHuman = reader.readFragment($responseFields[0], new ResponseReader.ObjectReader<AsHuman>() {
          @Override
          public AsHuman read(ResponseReader reader) {
            return asHumanFieldMapper.map(reader);
          }
        });
        if (asHuman != null) {
          return asHuman;
        }
        final AsDroid asDroid = reader.readFragment($responseFields[1], new ResponseReader.ObjectReader<AsDroid>() {
          @Override
          public AsDroid read(ResponseReader reader) {
            return asDroidFieldMapper.map(reader);
          }
        });
        if (asDroid != null) {
          return asDroid;
        }
        return asCharacter1FieldMapper.map(reader);
      }
    }

    interface Visitor<T> {
      T visitDefault(@NotNull Friend friend);

      T visit(@NotNull AsHuman asHuman);

      T visit(@NotNull AsDroid asDroid);

      T visit(@NotNull AsCharacter1 asCharacter1);
    }
  }

  public static class AsHuman implements Friend {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("homePlanet", "homePlanet", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("friends", "friends", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final Optional<String> homePlanet;

    final Optional<List<Friend1>> friends;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsHuman(@NotNull String __typename, @Nullable String homePlanet,
        @Nullable List<Friend1> friends, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.homePlanet = Optional.fromNullable(homePlanet);
      this.friends = Optional.fromNullable(friends);
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The home planet of the human, or null if unknown
     */
    public Optional<String> homePlanet() {
      return this.homePlanet;
    }

    /**
     * This human's friends, or an empty list if they have none
     */
    public Optional<List<Friend1>> friends() {
      return this.friends;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], homePlanet.isPresent() ? homePlanet.get() : null);
          writer.writeList($responseFields[2], friends.isPresent() ? friends.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((Friend1) item).marshaller());
              }
            }
          });
          writer.writeString($responseFields[3], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsHuman{"
          + "__typename=" + __typename + ", "
          + "homePlanet=" + homePlanet + ", "
          + "friends=" + friends + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsHuman) {
        AsHuman that = (AsHuman) o;
        return this.__typename.equals(that.__typename)
         && this.homePlanet.equals(that.homePlanet)
         && this.friends.equals(that.friends)
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= homePlanet.hashCode();
        h *= 1000003;
        h ^= friends.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsHuman> {
      final Friend1.Mapper friend1FieldMapper = new Friend1.Mapper();

      @Override
      public AsHuman map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String homePlanet = reader.readString($responseFields[1]);
        final List<Friend1> friends = reader.readList($responseFields[2], new ResponseReader.ListReader<Friend1>() {
          @Override
          public Friend1 read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Friend1>() {
              @Override
              public Friend1 read(ResponseReader reader) {
                return friend1FieldMapper.map(reader);
              }
            });
          }
        });
        final String name = reader.readString($responseFields[3]);
        return new AsHuman(__typename, homePlanet, friends, name);
      }
    }
  }

  public static class Friend1 {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("firstAppearsIn", "firstAppearsIn", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull Episode firstAppearsIn;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Friend1(@NotNull String __typename, @NotNull Episode firstAppearsIn) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.firstAppearsIn = Utils.checkNotNull(firstAppearsIn, "firstAppearsIn == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The movie this character first appears in
     */
    public @NotNull Episode firstAppearsIn() {
      return this.firstAppearsIn;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], firstAppearsIn.rawValue());
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Friend1{"
          + "__typename=" + __typename + ", "
          + "firstAppearsIn=" + firstAppearsIn
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Friend1) {
        Friend1 that = (Friend1) o;
        return this.__typename.equals(that.__typename)
         && this.firstAppearsIn.equals(that.firstAppearsIn);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= firstAppearsIn.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Friend1> {
      @Override
      public Friend1 map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String firstAppearsInStr = reader.readString($responseFields[1]);
        final Episode firstAppearsIn;
        if (firstAppearsInStr != null) {
          firstAppearsIn = Episode.safeValueOf(firstAppearsInStr);
        } else {
          firstAppearsIn = null;
        }
        return new Friend1(__typename, firstAppearsIn);
      }
    }
  }

  public static class AsDroid implements Friend {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("primaryFunction", "primaryFunction", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forList("friends", "friends", null, true, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final Optional<String> primaryFunction;

    final Optional<List<Friend2>> friends;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsDroid(@NotNull String __typename, @Nullable String primaryFunction,
        @Nullable List<Friend2> friends, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.primaryFunction = Optional.fromNullable(primaryFunction);
      this.friends = Optional.fromNullable(friends);
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * This droid's primary function
     */
    public Optional<String> primaryFunction() {
      return this.primaryFunction;
    }

    /**
     * This droid's friends, or an empty list if they have none
     */
    public Optional<List<Friend2>> friends() {
      return this.friends;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], primaryFunction.isPresent() ? primaryFunction.get() : null);
          writer.writeList($responseFields[2], friends.isPresent() ? friends.get() : null, new ResponseWriter.ListWriter() {
            @Override
            public void write(List items, ResponseWriter.ListItemWriter listItemWriter) {
              for (Object item : items) {
                listItemWriter.writeObject(((Friend2) item).marshaller());
              }
            }
          });
          writer.writeString($responseFields[3], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsDroid{"
          + "__typename=" + __typename + ", "
          + "primaryFunction=" + primaryFunction + ", "
          + "friends=" + friends + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsDroid) {
        AsDroid that = (AsDroid) o;
        return this.__typename.equals(that.__typename)
         && this.primaryFunction.equals(that.primaryFunction)
         && this.friends.equals(that.friends)
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= primaryFunction.hashCode();
        h *= 1000003;
        h ^= friends.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsDroid> {
      final Friend2.Mapper friend2FieldMapper = new Friend2.Mapper();

      @Override
      public AsDroid map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String primaryFunction = reader.readString($responseFields[1]);
        final List<Friend2> friends = reader.readList($responseFields[2], new ResponseReader.ListReader<Friend2>() {
          @Override
          public Friend2 read(ResponseReader.ListItemReader listItemReader) {
            return listItemReader.readObject(new ResponseReader.ObjectReader<Friend2>() {
              @Override
              public Friend2 read(ResponseReader reader) {
                return friend2FieldMapper.map(reader);
              }
            });
          }
        });
        final String name = reader.readString($responseFields[3]);
        return new AsDroid(__typename, primaryFunction, friends, name);
      }
    }
  }

  public static class Friend2 {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forCustomType("id", "id", null, false, CustomType.ID, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("deprecated", "deprecated", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String id;

    final @NotNull @Deprecated String deprecated;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public Friend2(@NotNull String __typename, @NotNull String id,
        @NotNull @Deprecated String deprecated) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.id = Utils.checkNotNull(id, "id == null");
      this.deprecated = Utils.checkNotNull(deprecated, "deprecated == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The ID of the character
     */
    public @NotNull String id() {
      return this.id;
    }

    /**
     * Test deprecated field
     * @deprecated For test purpose only
     */
    public @NotNull @Deprecated String deprecated() {
      return this.deprecated;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeCustom((ResponseField.CustomTypeField) $responseFields[1], id);
          writer.writeString($responseFields[2], deprecated);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "Friend2{"
          + "__typename=" + __typename + ", "
          + "id=" + id + ", "
          + "deprecated=" + deprecated
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Friend2) {
        Friend2 that = (Friend2) o;
        return this.__typename.equals(that.__typename)
         && this.id.equals(that.id)
         && this.deprecated.equals(that.deprecated);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= id.hashCode();
        h *= 1000003;
        h ^= deprecated.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<Friend2> {
      @Override
      public Friend2 map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String id = reader.readCustomType((ResponseField.CustomTypeField) $responseFields[1]);
        final String deprecated = reader.readString($responseFields[2]);
        return new Friend2(__typename, id, deprecated);
      }
    }
  }

  public static class AsCharacter1 implements Friend {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsCharacter1(@NotNull String __typename, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The name of the character
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsCharacter1{"
          + "__typename=" + __typename + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsCharacter1) {
        AsCharacter1 that = (AsCharacter1) o;
        return this.__typename.equals(that.__typename)
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsCharacter1> {
      @Override
      public AsCharacter1 map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        return new AsCharacter1(__typename, name);
      }
    }
  }

  public static class AsStarship implements Search {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList()),
      ResponseField.forString("name", "name", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    final @NotNull String name;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsStarship(@NotNull String __typename, @NotNull String name) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
      this.name = Utils.checkNotNull(name, "name == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    /**
     * The name of the starship
     */
    public @NotNull String name() {
      return this.name;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
          writer.writeString($responseFields[1], name);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsStarship{"
          + "__typename=" + __typename + ", "
          + "name=" + name
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsStarship) {
        AsStarship that = (AsStarship) o;
        return this.__typename.equals(that.__typename)
         && this.name.equals(that.name);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        h *= 1000003;
        h ^= name.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsStarship> {
      @Override
      public AsStarship map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        final String name = reader.readString($responseFields[1]);
        return new AsStarship(__typename, name);
      }
    }
  }

  public static class AsSearchResult implements Search {
    static final ResponseField[] $responseFields = {
      ResponseField.forString("__typename", "__typename", null, false, Collections.<ResponseField.Condition>emptyList())
    };

    final @NotNull String __typename;

    private transient volatile String $toString;

    private transient volatile int $hashCode;

    private transient volatile boolean $hashCodeMemoized;

    public AsSearchResult(@NotNull String __typename) {
      this.__typename = Utils.checkNotNull(__typename, "__typename == null");
    }

    public @NotNull String __typename() {
      return this.__typename;
    }

    @SuppressWarnings("unchecked")
    public ResponseFieldMarshaller marshaller() {
      return new ResponseFieldMarshaller() {
        @Override
        public void marshal(ResponseWriter writer) {
          writer.writeString($responseFields[0], __typename);
        }
      };
    }

    @Override
    public String toString() {
      if ($toString == null) {
        $toString = "AsSearchResult{"
          + "__typename=" + __typename
          + "}";
      }
      return $toString;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof AsSearchResult) {
        AsSearchResult that = (AsSearchResult) o;
        return this.__typename.equals(that.__typename);
      }
      return false;
    }

    @Override
    public int hashCode() {
      if (!$hashCodeMemoized) {
        int h = 1;
        h *= 1000003;
        h ^= __typename.hashCode();
        $hashCode = h;
        $hashCodeMemoized = true;
      }
      return $hashCode;
    }

    public static final class Mapper implements ResponseFieldMapper<AsSearchResult> {
      @Override
      public AsSearchResult map(ResponseReader reader) {
        final String __typename = reader.readString($responseFields[0]);
        return new AsSearchResult(__typename);
      }
    }
  }
}
