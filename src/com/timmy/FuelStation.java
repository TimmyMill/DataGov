package com.timmy;

public class FuelStation
{
    private String fuelTypeCode;
    private String stationName;
    private String city;
    private String state;
    private String streetAddress;
    private String zip;

    public FuelStation() {}

    public String getFuelTypeCode()
    { return fuelTypeCode; }

    public void setFuelTypeCode(String fuelTypeCode)
    { this.fuelTypeCode = fuelTypeCode; }

    public String getStationName()
    { return stationName; }

    public void setStationName(String stationName)
    { this.stationName = stationName; }

    public String getCity()
    { return city; }

    public void setCity(String city)
    { this.city = city; }

    public String getState()
    { return state; }

    public void setState(String state)
    { this.state = state; }

    public String getStreetAddress()
    { return streetAddress; }

    public void setStreetAddress(String streetAddress)
    { this.streetAddress = streetAddress; }

    public String getZip()
    { return zip; }

    public void setZip(String zip)
    { this.zip = zip; }

    @Override
    public String toString()
    { return String.format("%s \t%s \t%s, %s %s", stationName, streetAddress, city, state, zip); }
}
