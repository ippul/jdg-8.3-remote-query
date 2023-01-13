package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
    // dependsOn = {
    //     org.infinispan.protostream.types.java.CommonTypes.class,
    //     org.infinispan.protostream.types.java.CommonContainerTypes.class
    // },
    schemaFilePath = "protofiles",
    schemaFileName = "custom-java-types.proto",
    includeClasses = {
        Person.class,
        BigDecimalCustomAdapter.class
    })
public interface QuerySchemaBuilder extends SerializationContextInitializer {
}
