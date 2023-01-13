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
      builder.addContextInitializers(new QuerySchemaBuilderImpl(), new CommonContainerTypesSchema() /* , new CommonTypesSchema()*/);
      RemoteCacheManager client = new RemoteCacheManager(builder.build());
      addPersonSchema(client);
      RemoteCache<String, Person> peopleCache = client.getCache(Infinispan.TUTORIAL_CACHE_NAME);
      peopleCache.clear();
      Person person = new Person();
      person.setFirstName("Oihana");
      person.setaNumber(new BigDecimal("10000000000"));
      person.setLastName("Rossignol");
      peopleCache.put("1", person);
      QueryFactory queryFactory = Search.getQueryFactory(peopleCache);
      Query<Person> query = queryFactory.create("FROM Person p WHERE p.aNumber.value = :aNumber");
      query.setParameter("aNumber","10000000000");
      List<Person> result = query.execute().list();
      System.out.println("New API" + result);
      client.stop();
   }

   private static void addPersonSchema(RemoteCacheManager cacheManager) throws DescriptorParserException, IOException {
      RemoteCache<String, String> metadataCache = cacheManager.getCache(PROTOBUF_METADATA_CACHE_NAME);
      GeneratedSchema schema = new QuerySchemaBuilderImpl();
      metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());
   }

}
