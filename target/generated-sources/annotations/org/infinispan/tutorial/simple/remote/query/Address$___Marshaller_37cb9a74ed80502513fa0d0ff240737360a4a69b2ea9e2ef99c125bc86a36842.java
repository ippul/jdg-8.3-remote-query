/*
 Generated by org.infinispan.protostream.annotations.impl.processor.MarshallerSourceCodeGenerator
 for class org.infinispan.tutorial.simple.remote.query.Address
*/

package org.infinispan.tutorial.simple.remote.query;

import org.infinispan.tutorial.simple.remote.query.Address;

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
public final class Address$___Marshaller_37cb9a74ed80502513fa0d0ff240737360a4a69b2ea9e2ef99c125bc86a36842 extends org.infinispan.protostream.annotations.impl.GeneratedMarshallerBase implements org.infinispan.protostream.ProtobufTagMarshaller<org.infinispan.tutorial.simple.remote.query.Address> {

   @Override
   public Class<org.infinispan.tutorial.simple.remote.query.Address> getJavaClass() { return org.infinispan.tutorial.simple.remote.query.Address.class; }
   
   @Override
   public String getTypeName() { return "Address"; }
   
   @Override
   public org.infinispan.tutorial.simple.remote.query.Address read(org.infinispan.protostream.ProtobufTagMarshaller.ReadContext $1) throws java.io.IOException {
      final org.infinispan.protostream.TagReader $in = $1.getReader();
      final org.infinispan.tutorial.simple.remote.query.Address o = new org.infinispan.tutorial.simple.remote.query.Address();
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
               o.streetName = __v$1;
               break;
            }
            case (2 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               java.lang.String __v$2 = $in.readString();
               o.postcode = __v$2;
               break;
            }
            case (3 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               java.lang.String __v$3 = $in.readString();
               o.city = __v$3;
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
   public void write(org.infinispan.protostream.ProtobufTagMarshaller.WriteContext $1, org.infinispan.tutorial.simple.remote.query.Address $2) throws java.io.IOException {
      final org.infinispan.protostream.TagWriter $out = $1.getWriter();
      final org.infinispan.tutorial.simple.remote.query.Address o = (org.infinispan.tutorial.simple.remote.query.Address) $2;
      {
         final java.lang.String __v$1 = o.streetName;
         if (__v$1 != null) $out.writeString(1, __v$1);
      }
      {
         final java.lang.String __v$2 = o.postcode;
         if (__v$2 != null) $out.writeString(2, __v$2);
      }
      {
         final java.lang.String __v$3 = o.city;
         if (__v$3 != null) $out.writeString(3, __v$3);
      }
   }
}