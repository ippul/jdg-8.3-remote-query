package org.infinispan.tutorial.simple.remote.query;

import java.math.BigDecimal;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoName;

/**
 * This class is annotated with the infinispan Protostream support annotations.
 * With this method, you don't need to define a protobuf file and a marshaller for the object.
 */
@ProtoName("Person")
public final class Person {

   @ProtoField(number = 1)
   String firstName;

   @ProtoField(number = 2)
   String lastName;

   @ProtoField(number = 3)
   BigDecimal aNumber;

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public BigDecimal getaNumber() {
      return aNumber;
   }

   public void setaNumber(BigDecimal aNumber) {
      this.aNumber = aNumber;
   }

   @Override
   public String toString() {
      return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", aNumber='" + aNumber + '\'' +
            '}';
   }
}
