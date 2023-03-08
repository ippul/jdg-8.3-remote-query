package org.ippul.infinispan.example;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
    schemaFilePath = "protofiles",
    schemaFileName = "custom-java-types.proto",
    includeClasses = {
        Person.class, 
        BigDecimalCustomAdapter.class
    })
public interface QuerySchemaBuilder extends SerializationContextInitializer {
}
