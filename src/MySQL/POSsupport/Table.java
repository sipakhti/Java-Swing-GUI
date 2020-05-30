package MySQL.POSsupport;

import MySQL.Interfaces.ActionPerformed;
import MySQL.Interfaces.Row;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Provides a Jpanel with <code>TabelRow</code> as individual rows
 * it acts as the holder for the rows as well as the mediator between <code>TableRow</code> as the controller
 */

public class Table extends JPanel{
    /*
    Main table class that holds TableRow class as rows

    couples with the controller class to update the rows
     */
    private final BoxLayout LAYOUT = new BoxLayout(this,BoxLayout.Y_AXIS);
    private final int MINIMUM_ROWS = 31;

    private GridBagConstraints constraints;
    private ActionPerformed actionListener;

    private boolean rowEnabled;


    /**
     *
     * @param rowEnabled true then all the columns are editable
     *                   false if only the 3rd column (quantity) is editable
     */
    public Table(boolean rowEnabled){
        this.rowEnabled = rowEnabled;
        
        setLayout(LAYOUT);


        if (rowEnabled) {
            for (int i = 0; i <= MINIMUM_ROWS; i++) {
                add(new TableRow(true));
            }
        }
        else {
            for (int i = 0; i <= MINIMUM_ROWS; i++) {
                add(new TableRow());
            }
        }


        fcousListener();

    }

    private void fcousListener() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getComponents()[getComponentCount()-1].requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    private void injectActionListener() {
        Component[] components = getComponents();
        for (Component component : components) {
            TableRow row = (TableRow) component;
            row.setListener(actionListener);
        }
    }

    /**
     * used to accept data from
     * <code>{@link MySQL.DataBaseConnection}.<i>rowData()</i>
     *</code>
     *
     * @param data values for corresponding row's columns
     *             NOTE: String is formatted such that individual column data is seperated by "!!!"
     */
    public void updateRow(String data){
        /*
        accepts Array of String data and passes it to corresponding row to update fields
         */


        boolean updated = false;
        try {
            Component[] components = getComponents();

            // increments quantity is the same entry is found in the table
            for (Component component : components) {
                TableRow previousRow = (TableRow) component;
                if (previousRow.getProductName().equals(data.split("!!!")[0])) {
                    previousRow.incrementQuantity();
                    updated = true;
                    break;
                }
            }
            // updates row if an empty row is found
            if (!updated) {
                for (Component component : components) {
                    Row row = (Row) component;
                    if (row.isEmpty()) {
                        row.updateRow(data);
                        updated = true;
                        break;
                    }
                }
            }
            if (!updated) throw new IndexOutOfBoundsException();

        }catch(IndexOutOfBoundsException e) {
            // if no empty row is present then add a new row and populate the columns
            if (rowEnabled)
                add(new TableRow(rowEnabled));
            else
                add(new TableRow());
            Row row = (Row) getComponent(getComponentCount() - 1);
            row.updateRow(data);

        }
        injectActionListener();



    }
    /**
     *
     * @param data values for corresponding row's columns
     *
     */
    public void updateRow(String[] data){
        /*
        accepts Array of String data and passes it to corresponding row to update fields
         */


        boolean updated = false;
        try {
            Component[] components = getComponents();

            // increments quantity is the same entry is found in the table
            for (Component component : components) {
                TableRow previousRow = (TableRow) component;
                if (previousRow.getProductName().equals(data[0])) {
                    previousRow.incrementQuantity();
                    updated = true;
                    break;
                }
            }
            // updates row if an empty row is found
            if (!updated) {
                for (Component component : components) {
                    Row row = (Row) component;
                    if (row.isEmpty()) {
                        row.updateRow(data);
                        updated = true;
                        break;
                    }
                }
            }
            if (!updated) throw new IndexOutOfBoundsException();

        }catch(IndexOutOfBoundsException e) {
            // if no empty row is present then add a new row and populate the columns
            add(new TableRow(rowEnabled));
            Row row = (Row) getComponent(getComponentCount() - 1);
            row.updateRow(data);

        }
        injectActionListener();


    }

    /**
     * adds a new row by calling
     *
     * <code>
     *     {@link TableRow}
     * </code>
     *
     */
    public void addRow(){
        boolean newFieldRequired = true;
        Component[] components = getComponents();
        for (Component component : components) {
            if (((TableRow) component).isEmpty()){
                newFieldRequired = false;
                break;
            }
        }
        if (newFieldRequired) add(new TableRow(true));

        injectActionListener();
    }


    /**
     *
     * @return total computed by adding all the values in the fourth column
     */
    public float getTotal(){
        Component[] components = getComponents();
        float total = 0;
        for (Component component : components) {
            Row row = (Row) component;
            if (!row.isEmpty())
                total += row.subtotal();
        }
        return total;
    }

    /**
     *
     * @return <p>a 2D array of string containing all the first 2 columns data </p>
     *
     *         <b>NOTE:</b>
     *         <i>
     *             only the data of the 1st and 2nd columns are included
     *             that is only the product name and quantity
     *         </i>
     */
    public String[][] tableSaveData(){
        int rows = 0;
        Component[] components = getComponents();

        for (Component component : components) {
            if (!((Row) component).isEmpty()) rows++;
        }

        String[][] data = new String[rows][2];
        for (int i = 0; i < rows; i++) {
            Row row = (Row) components[i];
            if (row.isEmpty()) break;
            else data[i] = row.saveData();
        }

        return data;
    }

    /**
     *
     * @return <p>
     *     a 2D array of String containing all the data in the table
     * </p>
     */
    public String[][] tableUpdateData(){
        int rows = 0;
        Component[] components = getComponents();

        for (Component component : components) {
            if (!((Row) component).isEmpty()) rows++;
        }

        String[][] data = new String[rows][4];
        for (int i = 0; i < rows; i++) {
            Row row = (Row) components[i];
            if (row.isEmpty()) break;
            else data[i] = row.updateData();
        }

        return data;
    }


    /**
     *
     * @param action Inject an Actionperformed Interface instance to allow the controller to communicate with
     *               {@link TableRow}
     */
    public void setListener(ActionPerformed action) {
        this.actionListener = action;
        injectActionListener();
    }

    /**
     * deletes the most recent entry in the table
     * @return true if the function finished without any errors
     */
    public boolean removeRow(){
        int totalRows = getComponentCount();
        Component[] components = getComponents();
        if (totalRows > MINIMUM_ROWS + 1){
            remove(totalRows-1);
            return true;
        }

        for (int i = 0; i < totalRows; i++) {
            Row row = (Row) components[i];
            if (row.isEmpty()){
                row = (Row) components[i-1];
                row.reset();
                break;
            }
            row = (Row) components[MINIMUM_ROWS-1];
            if (!row.isEmpty()){
                row.reset();
                break;
            }
        }
        return true;
    }

    /**
     * Clears all the table fields
     */
    public void clear(){
        Component[] components = getComponents();
        for (Component component : components) {
            ((TableRow) component).reset();
        }
    }
}
