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
    private JTable stationDataTable;
    private JScrollPane tableScrollPane;
    private SearchParameters parameters;
    private DefaultTableModel tableModel;
    private ListSelectionModel tableListModel;
    private DefaultTableColumnModel columnModel;
    private TableRowSorter<DefaultTableModel> rowSorter;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public UserInterface(SearchParameters parameters)
    {
        super("Alternative Fuel Stations");

        //JFrame Settings
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

        //Create columns
        String[] columnHeadings = {"Station Name", "Fuel Types", "Phone", "Address", "City", "State", "Zipcode"};
        tableModel.setColumnIdentifiers(columnHeadings); //use a string array to give each column a name

//        //Create rows
//        if (Database.getLibraryList() != null) {
//            for (AudioFile file : Database.getLibraryList()) { //iterate the library list created in Database
////            tableModel.addRow(file.getSongInfo().toArray());
//                ArrayList<String> lst = file.getSongInfo();
//                lst.add(file.getPath());
//                Object[] str = lst.toArray();
//                tableModel.addRow(str);
//            /* for each audio file, use the getSongInfo method to extract the metadata and then add it to an array */
//            /* use the array to add a new row to the table */
//            }
//        }

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
        final TableColumn filePathColumn = stationDataTable.getColumnModel().getColumn(3);
        stationDataTable.getColumnModel().removeColumn(filePathColumn);

    }

    private void initCity(){}

    private void initFuelComboBox()
    { parameters.fuel.getFuelTypeValuesList().forEach(fuel -> fuelTypeComboBox.addItem(fuel)); }

    private void initStatesComboBox()
    { parameters.state.getStateValuesList().forEach(state -> statesComboBox.addItem(state)); }
}
