package org.infinispan.tutorial.simple.remote.query;

import java.util.List;

import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.annotations.ProtoName;

@ProtoName("Address")
public class Address {
    @ProtoField(number = 1)
    String streetName;

    @ProtoField(number = 2)
    String postcode;

    @ProtoField(number = 3)
    String city;

    List<Person> persons;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    
    @Override
    public String toString() {
       return "Person{" +
             "city='" + city + '\'' +
             ", postcode='" + postcode + '\'' +
             ", streetName='" + streetName + '\'' +
             '}';
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    
}
