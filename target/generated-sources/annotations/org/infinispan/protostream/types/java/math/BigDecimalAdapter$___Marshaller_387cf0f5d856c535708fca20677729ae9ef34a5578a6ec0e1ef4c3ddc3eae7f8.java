/*
 Generated by org.infinispan.protostream.annotations.impl.processor.MarshallerSourceCodeGenerator
 for class org.infinispan.protostream.types.java.math.BigDecimalAdapter
*/

package org.infinispan.protostream.types.java.math;

import java.math.BigDecimal;

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
public final class BigDecimalAdapter$___Marshaller_387cf0f5d856c535708fca20677729ae9ef34a5578a6ec0e1ef4c3ddc3eae7f8 extends org.infinispan.protostream.annotations.impl.GeneratedMarshallerBase implements org.infinispan.protostream.ProtobufTagMarshaller<java.math.BigDecimal> {

   private final org.infinispan.protostream.types.java.math.BigDecimalAdapter __a$ = new org.infinispan.protostream.types.java.math.BigDecimalAdapter();
   
   @Override
   public Class<java.math.BigDecimal> getJavaClass() { return java.math.BigDecimal.class; }
   
   @Override
   public String getTypeName() { return "BigDecimal"; }
   
   @Override
   public java.math.BigDecimal read(org.infinispan.protostream.ProtobufTagMarshaller.ReadContext $1) throws java.io.IOException {
      final org.infinispan.protostream.TagReader $in = $1.getReader();
      byte[] __v$1 = null;
      int __v$2 = 0;
      boolean done = false;
      while (!done) {
         final int tag = $in.readTag();
         switch (tag) {
            case 0: {
               done = true;
               break;
            }
            case (1 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_LENGTH_DELIMITED): {
               __v$1 = $in.readByteArray();
               break;
            }
            case (2 << org.infinispan.protostream.descriptors.WireType.TAG_TYPE_NUM_BITS | org.infinispan.protostream.descriptors.WireType.WIRETYPE_VARINT): {
               __v$2 = $in.readInt32();
               break;
            }
            default: {
               if (!$in.skipField(tag)) done = true;
            }
         }
      }
      return __a$.create(__v$1, __v$2);
   }
   
   @Override
   public void write(org.infinispan.protostream.ProtobufTagMarshaller.WriteContext $1, java.math.BigDecimal $2) throws java.io.IOException {
      final org.infinispan.protostream.TagWriter $out = $1.getWriter();
      final java.math.BigDecimal o = (java.math.BigDecimal) $2;
      {
         final byte[] __v$1 = __a$.getUnscaledValue(o);
         if (__v$1 != null) $out.writeBytes(1, __v$1);
      }
      {
         final int __v$2 = __a$.getScale(o);
         $out.writeInt32(2, __v$2);
      }
   }
}
