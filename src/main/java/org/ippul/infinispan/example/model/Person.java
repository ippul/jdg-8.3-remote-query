package org.ippul.infinispan.example.model;

import java.math.BigDecimal;
import java.util.Date;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoName;

/**
 * This class is annotated with the infinispan Protostream support annotations.
 * With this method, you don't need to define a protobuf file and a marshaller for the object.
 */
@ProtoName("Person")
@ProtoDoc("@Indexed")
public final class Person {


   private String firstName;

   private String lastName;

   private Date dateOfBirth;

   private BigDecimal age;

   @ProtoField(number = 1)
   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   @ProtoField(number = 2)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.YES)")
   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @ProtoField(number = 3)
   public Date getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   @ProtoField(number = 4)
   @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.NO, store = Store.YES)")
   public BigDecimal getAge() {
      return age;
   }

   public void setAge(BigDecimal age) {
      this.age = age;
   }

   @Override
   public String toString() {
      return "Person [firstName=" + firstName + 
      ", lastName=" + lastName + 
      ", dateOfBirth=" + dateOfBirth + 
      ", age=" + age + "]";
   }
}
