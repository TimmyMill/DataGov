package com.timmy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class State {
    private ArrayList<String> states_list = new ArrayList<>(Arrays.asList(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida",
            "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
            "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
            "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "All"));
    private ArrayList<String> states_abbreviated_list = new ArrayList<>(Arrays.asList(
            "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
            "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK",
            "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY", "all"));
    private SortedMap<String, String> states_map;
    protected String state;

    protected State(String state)
    {
        this.state = state;
        this.states_map = new TreeMap<>();
        this.populate_states_map();
    }

    private void populate_states_map()
    {
        for (int i = 0; i < states_list.size(); i++)
        {
            states_map.put(states_abbreviated_list.get(i), states_list.get(i));
        }
    }

    protected void set_state(Scanner in)
    {
//        Scanner in = new Scanner(System.in);
        show_states();
        System.out.println("Select State: ");
        int choice = in.nextInt();

        this.state = states_abbreviated_list.get(choice - 1);
        System.out.println(this.state);

//        in.close();
    }

    private void show_states()
    {
        int i = 0;
        for (String j : states_list)
        {
            i += 1;
            System.out.println(String.format("%d) %s", i, j));
        }
    }
}