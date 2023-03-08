package org.ippul.infinispan.example;

import java.math.BigDecimal;

import org.infinispan.protostream.annotations.ProtoAdapter;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

@ProtoAdapter(BigDecimal.class)
public class BigDecimalCustomAdapter {

    @ProtoFactory
    BigDecimal create(String value) {
       return new BigDecimal(value);
    }
 
    @ProtoField(1)
    String getValue(BigDecimal bigDecimal) {
       return bigDecimal.toPlainString();
    }
}