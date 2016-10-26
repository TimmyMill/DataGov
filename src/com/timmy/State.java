package com.timmy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class State {
    private ArrayList<String> stateKeysList = new ArrayList<>(Arrays.asList(
            "all", "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY",
            "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH",
            "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));
    private ArrayList<String> stateValuesList = new ArrayList<>(Arrays.asList(
            "All", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
            "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana",
            "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
            "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
            "Wisconsin", "Wyoming"));
    private SortedMap<String, String> statesMap;

    public ArrayList<String> getStateValuesList()
    { return stateValuesList; }

    protected String state;

    protected State(String state)
    {
        this.state = state;
        this.statesMap = new TreeMap<>();
        populateStatesMap();
    }

    private void populateStatesMap()
    {
        for (int item = 0; item < stateValuesList.size(); item++)
        {
            statesMap.put(stateKeysList.get(item), stateValuesList.get(item));
        }
    }

    protected void setState(Scanner in)
    {
//        Scanner in = new Scanner(System.in);
        showStates();
        System.out.println("Select State: ");
        int choice = in.nextInt();

        this.state = stateKeysList.get(choice - 1);
        System.out.println(this.state);

//        in.close();
    }

    private void showStates()
    {
        int i = 0;
        for (String j : stateValuesList)
        {
            i += 1;
            System.out.println(String.format("%d) %s", i, j));
        }
    }
}