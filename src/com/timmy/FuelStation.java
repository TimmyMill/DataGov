package com.timmy;


class FuelStation {
    private String fuel_type_code;
    private String station_name;
    private String city;
    private String state;
    private String street_address;
    private String zip;

    @Override
    public String toString() {
        return "FuelStation{" +
                "fuel_type_code='" + fuel_type_code + '\'' +
                ", station_name='" + station_name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", street_address='" + street_address + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
