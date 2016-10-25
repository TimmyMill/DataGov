package com.timmy;

import java.util.Scanner;

public class Search_Parameters
{
    Scanner in = new Scanner(System.in);

    Fuel fuel;
    State state;

    public Search_Parameters()
    {
        this.fuel = new Fuel("");
        this.state = new State("");
        this.init_parameters();
    }

    protected void init_parameters()
    {
        this.fuel.set_fuel_type(in);
        this.state.set_state(in);
    }
}