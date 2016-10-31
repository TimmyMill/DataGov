package com.timmy;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;

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
    private JPanel centerPanel;
    private JScrollPane tableScrollPane;
    private JTable stationDataTable;
    private DefaultTableModel tableModel;
    private ListSelectionModel tableListModel;
    private DefaultTableColumnModel columnModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private Dimension screenSize;

    private URLHandler urlHandler;
    private SearchParameters parameters;

    public UserInterface(URLHandler urlHandler, SearchParameters parameters)
    {
        this.urlHandler = urlHandler;
        this.parameters = parameters;

        initUI();

        //JFrame Settings
        setTitle("Alternative Fuel Stations");
        setPreferredSize(screenSize);
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null); // centers JFrame
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*
         JTable Settings
        */

        //Table Model
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        //Create columns
        String[] columnHeadings = {"ID", "Station Name", "Fuel Types", "Phone", "Address", "City", "State", "Zipcode"};
        tableModel.setColumnIdentifiers(columnHeadings); //use a string array to give each column a name

        //Create rows


        //Table List Model
        tableListModel = new DefaultListSelectionModel();

        //Table Column Model
        columnModel = new DefaultTableColumnModel();
        columnModel.setSelectionModel(tableListModel);


        rowSorter = new TableRowSorter<>();
        rowSorter.setModel(tableModel);

        stationDataTable.setModel(tableModel);
        stationDataTable.setSelectionModel(tableListModel);
        stationDataTable.setRowSorter(rowSorter);

        stationDataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stationDataTable.setRowSelectionAllowed(true);
        // Creates a reference to the first column in the table so we can remove it from the column model
        final TableColumn stationIDColumn = stationDataTable.getColumnModel().getColumn(0);
        stationDataTable.getColumnModel().removeColumn(stationIDColumn); // column still exists, just isn't displayed

        /*
         Event Listeners
        */

        fuelTypeComboBox.addItemListener(event -> {
                JComboBox cb = (JComboBox) event.getSource();
                System.out.println(String.format("Fuel Type: %s, Index: %d", cb.getSelectedItem().toString(),cb.getSelectedIndex()));
        });

        statesComboBox.addItemListener(event -> {
            JComboBox cb = (JComboBox) event.getSource();
            System.out.println(String.format("State: %s, Index: %d", cb.getSelectedItem().toString(),cb.getSelectedIndex()));
        });

        // Search Button Action Listener
        searchButton.addActionListener(event -> {
            System.out.println(String.format("Station Height %d Width %d", stationNameTextField.getHeight(), stationNameTextField.getWidth()));
            System.out.println(String.format("City Height %d Width %d", cityTextField.getHeight(), cityTextField.getWidth()));
            System.out.println(String.format("Zip Height %d Width %d", zipTextField.getHeight(), zipTextField.getWidth()));
            if (fuelTypeComboBox.getSelectedIndex() != 0 && statesComboBox.getSelectedIndex() != 0)
            {
//                parameters.state.getState(statesComboBox.getSelectedIndex());
                String fuelType = parameters.fuel.getFuelType(fuelTypeComboBox.getSelectedIndex());
                String state = parameters.state.getState(statesComboBox.getSelectedIndex());

                //
                addRowsToTable(urlHandler.db.searchFuelStations(fuelType, state));
            }
        });

        /*TODO: Add code to listen to text fields and get what the user is typing. Can use this to handle errors and
        to show descriptive text in each text box describing what to enter
        */

        stationNameTextField.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
    }

    private void initUI()
    {
        urlHandler.get();
        setScreenSize();
        initFuelComboBox();
        initStatesComboBox();
    }

    private void initFuelComboBox()
    {
        fuelTypeComboBox.addItem("Select Fuel Type");
        parameters.fuel.getFuelTypeValuesList().forEach(fuel -> fuelTypeComboBox.addItem(fuel));
    }

    private void initStatesComboBox()
    {
        statesComboBox.addItem("Select State");
        parameters.state.getStateValuesList().forEach(state -> statesComboBox.addItem(state));
    }

    private void setScreenSize()
    {
        // initialize using the dimensions of the screen size
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // for both height and width, we want 75% of the actual screen size
        Double width = screenSize.getWidth() * 0.75;
        Double height = screenSize.getHeight() * 0.75;
        screenSize.setSize(width, height);
    }

    private void addRowsToTable(ArrayList<FuelStation> stations)
    { stations.forEach(station -> tableModel.addRow(station.toArray())); }
}
