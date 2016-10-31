package com.timmy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class State
{
    private ArrayList<String> stateKeysList = new ArrayList<>(Arrays.asList(
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
            "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
            "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"));
    private ArrayList<String> stateValuesList = new ArrayList<>(Arrays.asList(
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
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

    protected State()
    {
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

    protected String getState(int index)
    {
        return stateKeysList.get(index - 1);
//        System.out.println(state);
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