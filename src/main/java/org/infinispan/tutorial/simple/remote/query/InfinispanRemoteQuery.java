package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.types.java.CommonContainerTypesSchema;
import org.infinispan.protostream.types.java.CommonTypesSchema;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.infinispan.tutorial.simple.connect.Infinispan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.infinispan.query.remote.client.ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME;

/**
 * The Remote Query simple tutorial.
 *
 * Infinispan Server includes a default property realm that requires
 * authentication. Create some credentials before you run this tutorial.
 *
 * @author Katia Aresti, karesti@redhat.com
 */
public class InfinispanRemoteQuery {

   public static void main(String[] args) throws Exception {
      ConfigurationBuilder builder = Infinispan.connectionConfig();

      // Add the Protobuf serialization context in the client
      builder.addContextInitializer(new QuerySchemaBuilderImpl());
      builder.addContextInitializer(new CommonContainerTypesSchema());
      builder.addContextInitializer(new CommonTypesSchema());
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
      Query<Person> query = queryFactory.create("FROM org.infinispan.tutorial.simple.remote.query.Person p WHERE p.number = :aNumber");
      query.setParameter("aNumber", new BigDecimal("10000000000"));

      List<Person> result = query.execute().list();
      System.out.println(result);
      client.stop();
   }

   private static void addPersonSchema(RemoteCacheManager cacheManager) {
      // Retrieve metadata cache
      RemoteCache<String, String> metadataCache =
            cacheManager.getCache(PROTOBUF_METADATA_CACHE_NAME);
      // Define the new schema on the server too
      GeneratedSchema schema = new QuerySchemaBuilderImpl();
      metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
      GeneratedSchema schema1 = new CommonContainerTypesSchema();
      metadataCache.put(schema1.getProtoFileName(), schema1.getProtoFile());
      GeneratedSchema schema2 = new CommonTypesSchema();
      metadataCache.put(schema2.getProtoFileName(), schema2.getProtoFile());
   }
}
