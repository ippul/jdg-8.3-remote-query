package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.protostream.DescriptorParserException;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.types.java.CommonContainerTypesSchema;
import org.infinispan.protostream.types.java.CommonTypesSchema;
import org.infinispan.query.dsl.FilterConditionContextQueryBuilder;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.tutorial.simple.connect.Infinispan;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.infinispan.query.remote.client.ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME;

public class InfinispanRemoteQuery {

   public static void main(String[] args) throws Exception {
      ConfigurationBuilder builder = Infinispan.connectionConfig();

      // Add the Protobuf serialization context in the client
      builder.addContextInitializers(new QuerySchemaBuilderImpl(), new CommonContainerTypesSchema() /* , new CommonTypesSchema()*/);
 
      // Connect to the server
      RemoteCacheManager client = new RemoteCacheManager(builder.build());

      // Create and add the Protobuf schema in the server
      addPersonSchema(client);

      // Get the people cache, create it if needed with the default configuration
      RemoteCache<String, Person> peopleCache = client.getCache(Infinispan.TUTORIAL_CACHE_NAME);
      peopleCache.clear();
      // Create the persons dataset to be stored in the cache
      Person person = new Person();
      person.setFirstName("Oihana");
      person.setaNumber(new BigDecimal("10000000000"));
      person.setLastName("Rossignol");
      peopleCache.put("1", person);
      // Get a query factory from the cache
      QueryFactory queryFactory = Search.getQueryFactory(peopleCache);
      try{
         Query<Person> query = queryFactory.create("FROM Person p WHERE p.aNumber.value = :aNumber");
         query.setParameter("aNumber","10000000000");
   
         List<Person> result = query.execute().list();
         System.out.println("New API" + result);
      } catch(Exception e) {
         e.printStackTrace();
      }
      client.stop();
   }

   private static void addPersonSchema(RemoteCacheManager cacheManager) throws DescriptorParserException, IOException {
      // Retrieve metadata cache
      RemoteCache<String, String> metadataCache = cacheManager.getCache(PROTOBUF_METADATA_CACHE_NAME);
      // Define the new schema on the server too
      GeneratedSchema schema = new QuerySchemaBuilderImpl();
      metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
      // GeneratedSchema schema1 = new CommonContainerTypesSchema();
      // metadataCache.put(schema1.getProtoFileName(), schema1.getProtoFile());
      // GeneratedSchema schema2 = new CommonTypesSchema();
      // metadataCache.put(schema2.getProtoFileName(), schema2.getProtoFile());
   }

}
