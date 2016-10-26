package com.timmy;

public class Location {
    private String street;
    private String city;
    private String state;
    private String postalCode;

    protected Location() {}

    protected Location(String street, String city, String state, String postalCode)
    {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}
