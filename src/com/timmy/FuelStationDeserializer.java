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

            final int id = station.get("id").getAsInt();

            final String stationName = station.get("station_name").getAsString();

            final String stationPhone = station.get("station_phone").getAsString();

            final String city = station.get("city").getAsString();

            final String state = station.get("state").getAsString();

            final String streetAddress = station.get("street_address").getAsString();

            final String zip = station.get("zip").getAsString();

            stations.add(buildFuelStation(id, fuelTypeCode, stationName, stationPhone, city, state, streetAddress, zip));
        }

        return stations;
    }

    private FuelStation buildFuelStation(int id, String fuelTypeCode, String stationName, String stationPhone, String city, String state,
                                         String streetAddress, String zip)
    {
        final FuelStation fuelStation = new FuelStation();
        fuelStation.setId(id);
        fuelStation.setFuelTypeCode(fuelTypeCode);
        fuelStation.setStationName(stationName);
        fuelStation.setStationPhone(stationPhone);
        fuelStation.setCity(city);
        fuelStation.setState(state);
        fuelStation.setStreetAddress(streetAddress);
        fuelStation.setZip(zip);
        return fuelStation;

    }

}
