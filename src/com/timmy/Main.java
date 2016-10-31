package com.timmy;

public class Main
{
    public static void main(String args[])
    {
        new UserInterface(new URLHandler(new Database()), new SearchParameters());
    }
}