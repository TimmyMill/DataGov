package com.timmy;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class UserInterface extends JFrame
{
    private JPanel rootPanel;
    private JPanel northPanel;
    private JComboBox<String> fuelTypeComboBox;
    private JComboBox<String> statesComboBox;
    private JTextField cityTextField;
    private JTextField zipTextField;
    private JButton searchButton;
    private JTextField stationNameTextField;
    private SearchParameters parameters;
    private DefaultTableModel tableModel;
    private ListSelectionModel tableListModel;
    private DefaultTableColumnModel columnModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
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

        /* JTable Settings
        */

        //Table Model
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
    }

    private void initCity(){}

    private void initFuelComboBox()
    { parameters.fuel.getFuelTypeValuesList().forEach(fuel -> fuelTypeComboBox.addItem(fuel)); }

    private void initStatesComboBox()
    { parameters.state.getStateValuesList().forEach(state -> statesComboBox.addItem(state)); }
}
