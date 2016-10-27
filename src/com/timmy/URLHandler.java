package com.timmy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class URLHandler {
    String stringURL = "";
    protected void get()
    {
        String initialURL = "https://developer.nrel.gov/api/alt-fuel-stations/v1.json?api_key=";
        System.out.println("");
        String API_KEY = "jnex5lhLYPKD5hj2uGQUOKYmyCXLm6IJQ6Gs2L6U";
        stringURL = initialURL + API_KEY + "&fuel_type=E85" + "&state=MN";
        System.out.println(stringURL);
        RequestFuelStationData(stringURL);
    }

    public static void main(String[] args)
    {
        URLHandler urlHandler = new URLHandler();
        urlHandler.get();
    }

    private void RequestFuelStationData(String stringURL)
    {
        try
        {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String line;
            try (InputStream responseStream = connection.getInputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream)))
            {
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line);
                }

                String response = stringBuilder.toString();

                Gson gson = new GsonBuilder().create();
                FuelStation station = gson.fromJson(bufferedReader, FuelStation.class);
                System.out.println(station);
            }

            catch (IOException ioe)
            {
                System.out.println(ioe.getMessage());
            }

            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("");
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
