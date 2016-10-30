package com.timmy;

import java.sql.*;
import org.sqlite.*;

public class Database
{
    private static final String JDBC = "org.sqlite.JDBC";
    private static final String DB_CONNECTION_URL = "jdbc:sqlite:alternative_fuel.db";
    private static Connection conn = null;
    private static SQLiteConnection sqlConn = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;


    public Database()
    {
        loadDriver();
        initDB();
    }

    protected void loadDriver()
    {
        try
        { Class.forName(JDBC); }

        catch (ClassNotFoundException cnfe)
        {
            System.out.println();
            cnfe.printStackTrace();
            System.exit(-1); // If there is no driver the program won't run correctly. Quit to check configuration.
        }
    }

    protected void initDB()
    {
        try (SQLiteConnection sqlConn = (SQLiteConnection) DriverManager.getConnection(DB_CONNECTION_URL))
        {
            String createTableSQL =
                    "CREATE TABLE IF NOT EXISTS fuel_stations " +
                            "(fuel_type TEXT, " +
                            "station_name TEXT, " +
                            "city TEXT, " +
                            "state TEXT, " +
                            "street TEXT, " +
                            "zip TEXT)";
            statement = sqlConn.createStatement();
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Table");
        }

        catch (SQLException e)
        { e.printStackTrace(); }
    }

    protected static void addToFuelStations(FuelStation station)
    {
        String preparedString = "INSERT INTO fuel_stations VALUES (?, ?, ?, ?, ?, ?)";
        try (SQLiteConnection sqlConn = (SQLiteConnection) DriverManager.getConnection(DB_CONNECTION_URL))
        {
            preparedStatement = sqlConn.prepareStatement(preparedString);
            preparedStatement.setString(1, station.getFuelTypeCode());
            preparedStatement.setString(2, station.getStationName());
            preparedStatement.setString(3, station.getCity());
            preparedStatement.setString(4, station.getState());
            preparedStatement.setString(5, station.getStreetAddress());
            preparedStatement.setString(6, station.getZip());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        { e.printStackTrace(); }

    }



    public static void main(String[] args)
    {
        Database db = new Database();
        db.loadDriver();
        db.initDB();
    }


}
//    String sql = "CREATE TABLE IF NOT EXISTS fuel_stations (fuel_type VARCHAR(50), station_name VARCHAR(75), " +
//                    "city VARCHAR(50), state VARCHAR(2), street VARCHAR(60), zip VARCHAR(5))";
