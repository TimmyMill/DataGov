package com.timmy;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import com.google.gson.reflect.TypeToken;

        import javax.swing.*;
        import java.io.InputStream;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.lang.reflect.Type;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;


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
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(ArrayList.class, new FuelStationDeserializer());
        Type stationType = new TypeToken<ArrayList<FuelStation>>() {}.getType();
        gsonBuilder.registerTypeAdapter(stationType, new FuelStationDeserializer());

//        gsonBuilder.registerTypeAdapter(FuelStation.class, new FuelStationDeserializer());


        Gson gson = gsonBuilder.create();

        try
        {
            URL url = new URL(stringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String line;
            try (InputStream responseStream = connection.getInputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseStream)))
            {
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ((line = bufferedReader.readLine()) != null)
//                {
//                    stringBuilder.append(line);
//                }
//
//                String response = stringBuilder.toString();

//                Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
//                Type stationType = new TypeToken<ArrayList<FuelStation>>() {}.getType();
                ArrayList<FuelStation> stations = gson.fromJson(bufferedReader, stationType);
//                ArrayList tempList = gson.fromJson(bufferedReader, ArrayList.class);

//                ArrayList<FuelStation> stations = gson.fromJson(bufferedReader, (Class<T>) FuelStation.class);

//                tempList.forEach(System.out::println);
                stations.forEach(System.out::println);
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
