/*
 Generated by org.infinispan.protostream.annotations.impl.processor.AutoProtoSchemaBuilderAnnotationProcessor
 for class org.infinispan.tutorial.simple.remote.query.QuerySchemaBuilder
 annotated with @org.infinispan.protostream.annotations.AutoProtoSchemaBuilder(dependsOn=org.infinispan.protostream.types.java.CommonTypes,org.infinispan.protostream.types.java.CommonContainerTypes, marshallersOnly=false, service=true, autoImportClasses=false, excludeClasses=, includeClasses=org.infinispan.tutorial.simple.remote.query.Person,org.infinispan.tutorial.simple.remote.query.Address,org.infinispan.protostream.types.java.math.BigDecimalAdapter, basePackages={}, value={}, schemaPackageName="", schemaFilePath="protofiles", schemaFileName="custom-java-types.proto", className="")
 */

package org.infinispan.tutorial.simple.remote.query;

/**
 * WARNING: Generated code! Do not edit!
 */
@javax.annotation.Generated(
   value = "org.infinispan.protostream.annotations.impl.processor.AutoProtoSchemaBuilderAnnotationProcessor",
   comments = "Please do not edit this file!"
)
@org.infinispan.protostream.annotations.impl.processor.OriginatingClasses({
   org.infinispan.protostream.types.java.math.BigDecimalAdapter.class,
   org.infinispan.tutorial.simple.remote.query.Address.class,
   org.infinispan.tutorial.simple.remote.query.Person.class
})
/*@org.infinispan.protostream.annotations.AutoProtoSchemaBuilder(
   className = "QuerySchemaBuilderImpl",
   schemaFileName = "custom-java-types.proto",
   schemaFilePath = "protofiles",
   dependsOn = {
      
      org.infinispan.protostream.types.java.CommonTypesSchema.class,
      org.infinispan.protostream.types.java.CommonContainerTypesSchema.class
   },
   service = true,
   marshallersOnly = false,
   autoImportClasses = false,
   includeClasses = {
      org.infinispan.protostream.types.java.math.BigDecimalAdapter.class,
      org.infinispan.tutorial.simple.remote.query.Address.class,
      org.infinispan.tutorial.simple.remote.query.Person.class
   }
)*/
@SuppressWarnings("all")
public class QuerySchemaBuilderImpl implements org.infinispan.tutorial.simple.remote.query.QuerySchemaBuilder, org.infinispan.protostream.GeneratedSchema {

   private final org.infinispan.protostream.types.java.CommonTypesSchema dep0 = new org.infinispan.protostream.types.java.CommonTypesSchema();
   
   private final org.infinispan.protostream.types.java.CommonContainerTypesSchema dep1 = new org.infinispan.protostream.types.java.CommonContainerTypesSchema();
   
   @Override
   public String getProtoFileName() { return "custom-java-types.proto"; }
   
   @Override
   public String getProtoFile() { return org.infinispan.protostream.impl.ResourceUtils.getResourceAsString(getClass(), "/protofiles/custom-java-types.proto"); }
   
   @Override
   public java.io.Reader getProtoFileReader() { return org.infinispan.protostream.impl.ResourceUtils.getResourceAsReader(getClass(), "/protofiles/custom-java-types.proto"); }
   
   @Override
   public void registerSchema(org.infinispan.protostream.SerializationContext serCtx) {
      dep0.registerSchema(serCtx);
      dep1.registerSchema(serCtx);
      serCtx.registerProtoFiles(org.infinispan.protostream.FileDescriptorSource.fromString(getProtoFileName(), getProtoFile()));
   }
   
   @Override
   public void registerMarshallers(org.infinispan.protostream.SerializationContext serCtx) {
      dep0.registerMarshallers(serCtx);
      dep1.registerMarshallers(serCtx);
      serCtx.registerMarshaller(new org.infinispan.protostream.types.java.math.BigDecimalAdapter$___Marshaller_387cf0f5d856c535708fca20677729ae9ef34a5578a6ec0e1ef4c3ddc3eae7f8());
      serCtx.registerMarshaller(new org.infinispan.tutorial.simple.remote.query.Address$___Marshaller_37cb9a74ed80502513fa0d0ff240737360a4a69b2ea9e2ef99c125bc86a36842());
      serCtx.registerMarshaller(new org.infinispan.tutorial.simple.remote.query.Person$___Marshaller_d724ca8d0dfd2e76f07ed9821eb2599a5b005e3f069f339bc6f46381c943d5da());
   }
}