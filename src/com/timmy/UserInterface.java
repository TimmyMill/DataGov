package com.timmy;

import javax.swing.*;

public class UserInterface extends JFrame
{
    private JPanel rootPanel;
    private JPanel northPanel;
    private JComboBox fuelComboBox;
    private JComboBox<String> statesComboBox;
    private SearchParameters parameters;

    public UserInterface(SearchParameters parameters)
    {
        setContentPane(rootPanel);
        setVisible(true);
        pack();
        this.parameters = parameters;
        init_statesComboBox();
    }

    private void init_fuelComboBox()
    { parameters.state.getStatesList().forEach(fuel -> statesComboBox.addItem(fuel)); }

    private void init_statesComboBox()
    { parameters.state.getStatesList().forEach(state -> statesComboBox.addItem(state)); }
}
