package com.timmy;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
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
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private URLHandler urlHandler;
    private SearchParameters parameters;

    public UserInterface(URLHandler urlHandler, SearchParameters parameters)
    {
        super("Alternative Fuel Stations");

        this.urlHandler = urlHandler;
        this.parameters = parameters;
        //JFrame Settings
        setContentPane(rootPanel);
        setVisible(true);


        setPreferredSize(screenSize);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initUI();

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

        searchButton.addActionListener(event -> {
            if (fuelTypeComboBox.getSelectedIndex() != 0 && statesComboBox.getSelectedIndex() != 0)
            {
//                parameters.state.getState(statesComboBox.getSelectedIndex());
                String fuelType = parameters.fuel.getFuelType(fuelTypeComboBox.getSelectedIndex());
                String state = parameters.state.getState(statesComboBox.getSelectedIndex());

                //
                addRowsToTable(urlHandler.db.searchFuelStations(fuelType, state));
            }
        });
    }

    private void initUI()
    {
        urlHandler.get();
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

    private void addRowsToTable(ArrayList<FuelStation> stations)
    { stations.forEach(station -> tableModel.addRow(station.toArray())); }
}
