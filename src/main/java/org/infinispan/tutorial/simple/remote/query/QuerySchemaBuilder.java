package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.infinispan.protostream.types.java.math.BigDecimalAdapter;

@AutoProtoSchemaBuilder(
    dependsOn = {
        org.infinispan.protostream.types.java.CommonTypes.class,
        org.infinispan.protostream.types.java.CommonContainerTypes.class
    },
    
    // schemaPackageName = "org.ippul.test", 
    schemaFilePath = "protofiles",
    schemaFileName = "custom-java-types.proto",
    includeClasses = {
        Person.class,
        Address.class,
        BigDecimalAdapter.class
    })
public interface QuerySchemaBuilder extends SerializationContextInitializer {
}
