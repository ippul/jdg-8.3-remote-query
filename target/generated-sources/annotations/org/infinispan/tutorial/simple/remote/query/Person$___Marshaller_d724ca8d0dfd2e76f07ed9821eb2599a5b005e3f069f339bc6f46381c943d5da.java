/*
 Generated by org.infinispan.protostream.annotations.impl.processor.MarshallerSourceCodeGenerator
 for class org.infinispan.tutorial.simple.remote.query.Person
*/

package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.tutorial.simple.remote.query.Person;

/**
 * WARNING: Generated code! Do not edit!
 *
 * @private
 */
@javax.annotation.Generated(
   value = "org.infinispan.protostream.annotations.impl.processor.AutoProtoSchemaBuilderAnnotationProcessor",
   comments = "Please do not edit this file!"
)
@SuppressWarnings("all")
public final class Person$___Marshaller_d724ca8d0dfd2e76f07ed9821eb2599a5b005e3f069f339bc6f46381c943d5da extends org.infinispan.protostream.annotations.impl.GeneratedMarshallerBase implements org.infinispan.protostream.ProtobufTagMarshaller<org.infinispan.tutorial.simple.remote.query.Person> {

   private org.infinispan.protostream.impl.BaseMarshallerDelegate __md$3;
   
   @Override
   public Class<org.infinispan.tutorial.simple.remote.query.Person> getJavaClass() { return org.infinispan.tutorial.simple.remote.query.Person.class; }
   
   @Override
   public String getTypeName() { return "Person"; }
   
   @Override
   public org.infinispan.tutorial.simple.remote.query.Person read(org.infinispan.protostream.ProtobufTagMarshaller.ReadContext $1) throws java.io.IOException {
      final org.infinispan.protostream.TagReader $in = $1.getReader();
      final org.infinispan.tutorial.simple.remote.query.Person o = new org.infinispan.tutorial.simple.remote.query.Person();
      boolean done = false;
      while (!done) {
         final int tag = $in.readTag();
         switch (tag) {
            case 0: {
               done = true;
               break;
            }
            case (1 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               java.lang.String __v$1 = $in.readString();
               o.firstName = __v$1;
               break;
            }
            case (2 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               java.lang.String __v$2 = $in.readString();
               o.lastName = __v$2;
               break;
            }
            case (3 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               if (__md$3 == null) __md$3 = ((org.infinispan.protostream.impl.SerializationContextImpl) $1.getSerializationContext()).getMarshallerDelegate(java.math.BigDecimal.class);
               int length = $in.readUInt32();
               int oldLimit = $in.pushLimit(length);
               java.math.BigDecimal __v$3 = (java.math.BigDecimal) readMessage(__md$3, $1);
               $in.checkLastTagWas(0);
               $in.popLimit(oldLimit);
               o.aNumber = __v$3;
               break;
            }
            default: {
               if (!$in.skipField(tag)) done = true;
            }
         }
      }
      return o;
   }
   
   @Override
   public void write(org.infinispan.protostream.ProtobufTagMarshaller.WriteContext $1, org.infinispan.tutorial.simple.remote.query.Person $2) throws java.io.IOException {
      final org.infinispan.protostream.TagWriter $out = $1.getWriter();
      final org.infinispan.tutorial.simple.remote.query.Person o = (org.infinispan.tutorial.simple.remote.query.Person) $2;
      {
         final java.lang.String __v$1 = o.firstName;
         if (__v$1 != null) $out.writeString(1, __v$1);
      }
      {
         final java.lang.String __v$2 = o.lastName;
         if (__v$2 != null) $out.writeString(2, __v$2);
      }
      {
         final java.math.BigDecimal __v$3 = (java.math.BigDecimal) o.aNumber;
         if (__v$3 != null) {
            if (__md$3 == null) __md$3 = ((org.infinispan.protostream.impl.SerializationContextImpl) $1.getSerializationContext()).getMarshallerDelegate(java.math.BigDecimal.class);
            writeNestedMessage(__md$3, $1, 3, __v$3);
         }
      }
   }
}