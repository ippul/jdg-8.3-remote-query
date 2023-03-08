package org.ippul.infinispan.example;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.Search;
import org.infinispan.client.hotrod.configuration.ClientIntelligence;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;
import org.infinispan.commons.configuration.StringConfiguration;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import static org.infinispan.query.remote.client.ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RemoteQueryTest {

    public static final String USER = "admin";
    
    public static final String PASSWORD = "password";
    
    public static final String HOST = "127.0.0.1";
 
    public static final String TUTORIAL_CACHE_CONFIG =
          "<local-cache name=\"PERSON\">\n"
          + "    <indexing storage=\"local-heap\"/>\n"
          + "    <encoding media-type=\"application/x-protostream\"/>\n"
          + "    <indexing>\n"
          + "    <indexed-entities>\n"
          + "    <indexed-entity>Person</indexed-entity>\n"         
          + "    </indexed-entities>\n"
          + "    </indexing>\n"    
          + "</local-cache>";


    private RemoteCacheManager remoteCacheManager;

    private RemoteCache<String, Person> indexedCache;

    @BeforeEach
    public void init() throws Exception {
        remoteCacheManager = createRemoteCacheManaager();
        indexedCache = remoteCacheManager.administration().getOrCreateCache("PERSON", new StringConfiguration(TUTORIAL_CACHE_CONFIG));
        populateCache(indexedCache);
    }
    
    @AfterEach
    public void dispose() throws Exception {
        remoteCacheManager.administration().removeCache("PERSON");
        remoteCacheManager.close();
    }

    @Test
    public void queryPersonByFirstNameTest() {
        final QueryFactory queryFactory = Search.getQueryFactory(indexedCache);
        final Query<Person> query = queryFactory.create("FROM Person p WHERE p.firstName = :firstName");
        query.setParameter("firstName", "Mickey");
        //
        final List<Person> result = query.execute().list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mickey", result.get(0).getFirstName());
        assertEquals("Mouse", result.get(0).getLastName());
    }

    @Test
    public void queryPersonByLastNameTest() {
        final QueryFactory queryFactory = Search.getQueryFactory(indexedCache);
        final Query<Person> query = queryFactory.create("FROM Person p WHERE p.lastName = :lastName");
        query.setParameter("lastName", "Mouse");
        //
        final List<Person> result = query.execute().list();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.stream().filter(p -> p.getFirstName().equals("Mickey")).count());
        assertEquals(1, result.stream().filter(p -> p.getFirstName().equals("Minnie")).count());
        assertEquals("Mouse", result.get(0).getLastName());
    }

    @Test
    public void queryPersonWithNullDateOfBirthTest() {
        final QueryFactory queryFactory = Search.getQueryFactory(indexedCache);
        final Query<Person> query = queryFactory.create("FROM Person p WHERE p.dateOfBirth is null");
        //
        final List<Person> result = query.execute().list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.stream().filter(p -> p.getFirstName().equals("Pinocchio")).count());
        assertEquals("Burattino", result.get(0).getLastName());
    }
    
    @Test
    public void queryPersonByAgeTest() {
        final QueryFactory queryFactory = Search.getQueryFactory(indexedCache);
        final Query<Person> query = queryFactory.create("FROM Person p WHERE p.age.value = :age");
        query.setParameter("age", new BigDecimalCustomAdapter().getValue(BigDecimal.valueOf(89)));
        //
        final List<Person> result = query.execute().list();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Donald", result.get(0).getFirstName());
        assertEquals("Duck", result.get(0).getLastName());
    }

    private RemoteCacheManager createRemoteCacheManaager() throws Exception{
        final ConfigurationBuilder builder = new ConfigurationBuilder();
        // create client
        builder.addServer().host("127.0.0.1").port(ConfigurationProperties.DEFAULT_HOTROD_PORT).security()
              .authentication()
              .username(USER)
              .password(PASSWORD)
        .clientIntelligence(ClientIntelligence.BASIC)
        .marshaller(org.infinispan.commons.marshall.ProtoStreamMarshaller.class)
        .addContextInitializer(new QuerySchemaBuilderImpl());
        final RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
        final RemoteCache<String, String> metadataCache = cacheManager.getCache(PROTOBUF_METADATA_CACHE_NAME);
        final GeneratedSchema querySchemaBuilder = new QuerySchemaBuilderImpl();
        metadataCache.put(querySchemaBuilder.getProtoFileName(), querySchemaBuilder.getProtoFile());
        return cacheManager;
     }


    private void populateCache(RemoteCache<String, Person> remoteCache){
        Person mickeyMouse = new Person();
        mickeyMouse.setFirstName("Mickey");
        mickeyMouse.setLastName("Mouse");
        mickeyMouse.setDateOfBirth(new GregorianCalendar(1901, GregorianCalendar.DECEMBER, 5).getTime());
        mickeyMouse.setAge(BigDecimal.valueOf(122));
        remoteCache.put(UUID.randomUUID().toString(), mickeyMouse);
        //
        Person minnieMouse = new Person();
        minnieMouse.setFirstName("Minnie");
        minnieMouse.setLastName("Mouse");
        minnieMouse.setDateOfBirth(new GregorianCalendar(1901, GregorianCalendar.DECEMBER, 5).getTime());
        minnieMouse.setAge(BigDecimal.valueOf(122));
        remoteCache.put(UUID.randomUUID().toString(), minnieMouse);
        //
        Person donaldDuck = new Person();
        donaldDuck.setFirstName("Donald");
        donaldDuck.setLastName("Duck");
        donaldDuck.setDateOfBirth(new GregorianCalendar(1934, GregorianCalendar.JUNE, 9).getTime());
        donaldDuck.setAge(BigDecimal.valueOf(89));
        remoteCache.put(UUID.randomUUID().toString(), donaldDuck);
        //
        Person claudio = new Person();
        claudio.setFirstName("Pinocchio");
        claudio.setLastName("Burattino");
        remoteCache.put(UUID.randomUUID().toString(), claudio);
}
}
