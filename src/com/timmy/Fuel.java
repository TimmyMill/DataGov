package com.timmy;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;


class Fuel
{
    protected String fuel_type;
    private SortedMap<String, String> fuel_types;

    protected Fuel(String fuel_type)
    {
        this.fuel_type = fuel_type;
        this.fuel_types = new TreeMap<>();
        this.fill_fuel_types();
    }

    protected void set_fuel_type(Scanner in)
    {
//        Scanner in = new Scanner(System.in);
        show_fuel_types();
        System.out.println("Select Fuel Type: ");
        int choice = in.nextInt();

        ArrayList<String> fuel_types_list = new ArrayList<>(fuel_types.keySet());
        this.fuel_type = fuel_types_list.get(choice - 1);
        System.out.println(this.fuel_type);

//        in.close();
    }

    protected void show_fuel_types()
    {
//        this.fuel_types.values().forEach(System.out::println);
        int num = 0;
        for (String key : this.fuel_types.values())
        {
            num += 1;
            System.out.println(Integer.toString(num) + ") " + key);
        }
    }

    private void fill_fuel_types()
    {
        fuel_types.put("all", "All");
        fuel_types.put("BD", "Biodiesel (B20 and above)");
        fuel_types.put("CNG", "Compressed Natural Gas");
        fuel_types.put("E85", "Ethanol (E85)");
        fuel_types.put("ELEC", "Electric");
        fuel_types.put("HY", "Hydrogen");
        fuel_types.put("LNG", "Liquefied Natural Gas");
        fuel_types.put("LPG", "Liquefied Petroleum Gas (Propane)");
    }
}