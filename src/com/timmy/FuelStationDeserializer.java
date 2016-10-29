package com.timmy;
/*
 http://www.javacreed.com/gson-deserialiser-example/
 */

import com.google.gson.*;

import java.lang.reflect.Type;

public class FuelStationDeserializer implements JsonDeserializer<FuelStation>
{
    @Override
    public FuelStation deserialize(final JsonElement json, final Type type, JsonDeserializationContext jdContext)
            throws JsonParseException
    {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray jsonStationsArray = jsonObject.getAsJsonArray("fuel_stations");

        final String[] stations = new String[jsonStationsArray.size()];

        FuelStation fuelStation;

        for (int index = 0; index < stations.length; index++)
        {
            final JsonObject station = jsonStationsArray.get(index).getAsJsonObject();

            final String fuelTypeCode = station.get("fuel_type_code").getAsString();

            final String stationName = station.get("station_name").getAsString();

            final String city = station.get("city").getAsString();

            final String state = station.get("state").getAsString();

            final String streetAddress = station.get("street_address").getAsString();

            final String zip = station.get("zip").getAsString();

            fuelStation = buildFuelStation(fuelTypeCode, stationName, city, state, streetAddress, zip);

        }

        return fuelStation;
    }

    private FuelStation buildFuelStation(String fuelTypeCode, String city, String state, String stationName,
                                         String streetAddress, String zip)
    {
        final FuelStation fuelStation = new FuelStation();
        fuelStation.setFuelTypeCode(fuelTypeCode);
        fuelStation.setCity(city);
        fuelStation.setState(state);
        fuelStation.setStationName(stationName);
        fuelStation.setStreetAddress(streetAddress);
        fuelStation.setZip(zip);
        return fuelStation;

    }

}
