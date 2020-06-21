package com.quetzalcoatl.jpacourses.entity;


import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String city;
    private String zip;

    public Address() {
    }

    public Address(String country, String city, String zip) {
        this.country = country;
        this.city = city;
        this.zip = zip;
    }
}
