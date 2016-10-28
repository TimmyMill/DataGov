package com.timmy;
/*
 http://www.javacreed.com/gson-deserialiser-example/
 */

import com.google.gson.*;

import java.lang.reflect.Type;

public class FuelStationDeserializer implements JsonDeserializer<FuelStation> {
    @Override
    public FuelStation deserialize(final JsonElement json, final Type type, JsonDeserializationContext jdContext)
            throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray jsonStationsArray = jsonObject.getAsJsonArray("fuel_stations");
        final String[] stations = new String[jsonStationsArray.size()];
//        for (int i = 0; i < stations.length; i++) {
//            final JsonElement jsonElement = jsonStationsArray.get(i);
//
//        }

        final JsonObject first = jsonStationsArray.get(0).getAsJsonObject();
        final String fuelTypeCode = first.get("fuel_type_code").getAsString();
//        final JsonElement jsonFuelTypeCode = jsonObject.get("fuel_type_code");
//        final String fuelTypeCode = jsonFuelTypeCode.getAsString();

        final String stationName = first.get("station_name").getAsString();

        final String city = first.get("city").getAsString();

        final String state = first.get("state").getAsString();

        final String streetAddress = first.get("street_address").getAsString();

        final String zip = first.get("zip").getAsString();

//        final JsonElement jsonStationName = jsonObject.get("station_name");
//
//        final JsonElement jsonCity = jsonObject.get("city");
//
//        final JsonElement jsonState = jsonObject.get("state");
//
//        final JsonElement jsonStreetAddress = jsonObject.get("street_address");
//
//        final JsonElement jsonZip = jsonObject.get("zip");

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
