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
        { Class.forName("org.sqlite.JDBC"); }

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
                    "CREATE TABLE IF NOT EXISTS fuel_stations( " +
                            "id INTEGER UNIQUE NOT NULL," +
                            "fuel_type TEXT, " +
                            "station_name TEXT, " +
                            "station_phone TEXT, " +
                            "city TEXT, " +
                            "state TEXT, " +
                            "street TEXT, " +
                            "zip TEXT)";
            statement = sqlConn.createStatement();
            statement.executeUpdate(createTableSQL);
            System.out.println("Created Table");
        }

        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    protected static void addToFuelStations(FuelStation station)
    {
        String preparedString = "INSERT INTO fuel_stations VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try
                (SQLiteConnection sqlConn = (SQLiteConnection) DriverManager.getConnection(DB_CONNECTION_URL))
        {
            preparedStatement = sqlConn.prepareStatement(preparedString);
            preparedStatement.setInt(1, station.getId());
            preparedStatement.setString(2, station.getFuelTypeCode());
            preparedStatement.setString(3, station.getStationName());
            preparedStatement.setString(4, station.getStationPhone());
            preparedStatement.setString(5, station.getCity());
            preparedStatement.setString(6, station.getState());
            preparedStatement.setString(7, station.getStreetAddress());
            preparedStatement.setString(8, station.getZip());
            preparedStatement.executeUpdate();
        }

        catch (SQLIntegrityConstraintViolationException constraintException)
        {
//            constraintException.addSuppressed(new SQLIntegrityConstraintViolationException());
            System.out.println("Unique");
        }

        catch (SQLException e)
        {
            System.out.println(String.format("%s %d",e.getMessage(), e.getErrorCode()));
        }


    }



//    public static void main(String[] args)
//    {
//        Database db = new Database();
//        db.loadDriver();
//        db.initDB();
//    }


}
//    String sql = "CREATE TABLE IF NOT EXISTS fuel_stations (fuel_type VARCHAR(50), station_name VARCHAR(75), " +
//                    "city VARCHAR(50), state VARCHAR(2), street VARCHAR(60), zip VARCHAR(5))";
