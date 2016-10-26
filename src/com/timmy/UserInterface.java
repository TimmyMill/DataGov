package com.timmy;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;

public class UserInterface extends JFrame
{
    private JPanel rootPanel;
    private JPanel northPanel;
    private JComboBox<String> fuelComboBox;
    private JComboBox<String> statesComboBox;
    private SearchParameters parameters;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public UserInterface(SearchParameters parameters)
    {
        super("Alternative Fuel Stations");
        setContentPane(rootPanel);
        setVisible(true);

        setPreferredSize(screenSize);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.parameters = parameters;
        initFuelComboBox();
        initStatesComboBox();
    }

    private void initFuelComboBox()
    { parameters.fuel.getFuelTypeValuesList().forEach(fuel -> fuelComboBox.addItem(fuel)); }

    private void initStatesComboBox()
    { parameters.state.getStateValuesList().forEach(state -> statesComboBox.addItem(state)); }
}
