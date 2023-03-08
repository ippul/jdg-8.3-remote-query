package org.ippul.infinispan.example;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.ippul.infinispan.example.adapter.BigDecimalCustomAdapter;
import org.ippul.infinispan.example.model.Person;

@AutoProtoSchemaBuilder(
    schemaFilePath = "protofiles",
    schemaFileName = "custom-java-types.proto",
    includeClasses = {
        Person.class, 
        BigDecimalCustomAdapter.class
    })
public interface QuerySchemaBuilder extends SerializationContextInitializer {
}
