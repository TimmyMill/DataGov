package com.timmy;
/*
 http://www.javacreed.com/gson-deserialiser-example/
 */

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FuelStationDeserializer implements JsonDeserializer<ArrayList>
{
    @Override
    public ArrayList<FuelStation> deserialize(final JsonElement json, final Type typeOfT, JsonDeserializationContext jdContext)
            throws JsonParseException
    {
        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonArray jsonStationsArray = jsonObject.getAsJsonArray("fuel_stations");
        final ArrayList<FuelStation> stations = new ArrayList<>();

        for (JsonElement stationElement : jsonStationsArray)
        {
            final JsonObject station = stationElement.getAsJsonObject();

            final String fuelTypeCode = station.get("fuel_type_code").getAsString();

            final String stationName = station.get("station_name").getAsString();

            final String city = station.get("city").getAsString();

            final String state = station.get("state").getAsString();

            final String streetAddress = station.get("street_address").getAsString();

            final String zip = station.get("zip").getAsString();

            stations.add(buildFuelStation(fuelTypeCode, stationName, city, state, streetAddress, zip));
        }

        return stations;
    }

    private FuelStation buildFuelStation(String fuelTypeCode, String stationName, String city, String state,
                                         String streetAddress, String zip)
    {
        final FuelStation fuelStation = new FuelStation();
        fuelStation.setFuelTypeCode(fuelTypeCode);
        fuelStation.setStationName(stationName);
        fuelStation.setCity(city);
        fuelStation.setState(state);
        fuelStation.setStreetAddress(streetAddress);
        fuelStation.setZip(zip);
        return fuelStation;

    }

}
