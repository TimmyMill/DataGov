package com.timmy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class URLHandler {
    String stringURL = "";
    private final String KEY = getKey();
    protected Database db;

    protected URLHandler(Database db)
    {
        this.db = db;
    }

    private String getKey()
    {
        String line = "";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/home/timmy/IdeaProjects/DataGov/src/com/timmy/key.txt"))))
        // Our key is on the first line so this is all that's needed
        { line = bufferedReader.readLine(); }

        catch (FileNotFoundException fnf)
        { System.out.println("Could not find Key File "); }

        catch (IOException ioe)
        { ioe.printStackTrace(); }

        return line;
    }

    protected void get()
    {
        //TODO: Use parameters to take dynamic input from the User
        String initialURL = "https://developer.nrel.gov/api/alt-fuel-stations/v1.json?api_key=";
        stringURL = initialURL + KEY + "&fuel_type=E85" + "&state=MN&limit=100";
//        System.out.println(stringURL);
        RequestFuelStationData(stringURL);
    }

    private void RequestFuelStationData(String stringURL)
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Type stationType = new TypeToken<ArrayList<FuelStation>>() {}.getType();
        gsonBuilder.registerTypeAdapter(stationType, new FuelStationDeserializer());

        Gson gson = gsonBuilder.create();


        try
        {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (InputStream responseStream = connection.getInputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream)))
            {
                ArrayList<FuelStation>stations = gson.fromJson(bufferedReader, stationType);
                stations.forEach(System.out::println);

                // Pass each FuelStation object as a parameter into the method from the Database class,
                // which will add them to the database
                // Check to se
                stations.forEach(station -> {
                    if (!db.uniqueKeyExists(station.getId())) { db.addToFuelStations(station); }
                });
            }

            catch (IOException ioe)
            {
                System.out.println(ioe.getMessage());
            }



            catch (Exception e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

        catch (MalformedURLException mue)
        {
            System.out.println("Mal");
        }

        catch (IOException ioe)
        {
            System.out.println("Ioe");
        }

    }
}
