package com.timmy;

import java.util.Scanner;

public class SearchParameters
{
    Scanner in = new Scanner(System.in);

    Fuel fuel;
    State state;

    public SearchParameters()
    {
        this.fuel = new Fuel("");
        this.state = new State("");
//        this.init_parameters();
    }

    protected void init_parameters()
    {
        this.fuel.setFuelType(in);
        this.state.setState(in);
    }
}