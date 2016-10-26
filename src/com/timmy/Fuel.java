package com.timmy;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Fuel
{
    private ArrayList<String> fuelTypeKeysList = new ArrayList<>(Arrays.asList(
            "all", "BD", "CNG", "E85", "ELEC", "HY", "LNG", "LPG"));
    private ArrayList<String> fuelTypeValuesList = new ArrayList<>(Arrays.asList(
            "All", "Biodiesel (B20 and above)", "Compressed Natural Gas", "Ethanol (E85)", "Electric", "Hydrogen",
            "Liquefied Natural Gas", "Liquefied Petroleum Gas (Propane)"));
    private SortedMap<String, String> fuelTypeMap;

    public ArrayList<String> getFuelTypeValuesList()
    { return fuelTypeValuesList; }

    protected String fuelType;

    protected Fuel(String fuel_type)
    {
        this.fuelType = fuel_type;
        this.fuelTypeMap = new TreeMap<>();
        populateFuelTypeMap();
    }

    protected void setFuelType(Scanner in)
    {
//        Scanner in = new Scanner(System.in);
        showFuelTypes();
        System.out.println("Select Fuel Type: ");
        int choice = in.nextInt();

        ArrayList<String> fuel_types_list = new ArrayList<>(fuelTypeMap.keySet());
        this.fuelType = fuel_types_list.get(choice - 1);
        System.out.println(this.fuelType);

//        in.close();
    }

    private void populateFuelTypeMap()
    {
        for (int item = 0; item < fuelTypeKeysList.size(); item++)
        {
            fuelTypeMap.put(fuelTypeKeysList.get(item), fuelTypeValuesList.get(item));
        }
    }

    protected void showFuelTypes()
    {
//        this.fuelTypeMap.values().forEach(System.out::println);
        int num = 0;
        for (String key : this.fuelTypeMap.values())
        {
            num += 1;
            System.out.println(Integer.toString(num) + ") " + key);
        }
    }
}