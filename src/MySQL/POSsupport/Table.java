package MySQL.POSsupport;

import MySQL.Interfaces.ActionPerformed;
import MySQL.Interfaces.Row;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel{
    /*
    Main table class that holds TableRow class as rows

    couples with the controller class to update the rows
     */
    private final BoxLayout LAYOUT = new BoxLayout(this,BoxLayout.Y_AXIS);
    private final int MINIMUM_ROWS = 20;

    private GridBagConstraints constraints;
    private ActionPerformed actionListener;

    private boolean rowEnabled;



    public Table(boolean rowEnabled){
        this.rowEnabled = rowEnabled;
        
        setLayout(LAYOUT);


        for (int i = 0; i <= MINIMUM_ROWS; i++) {
            add(new TableRow(rowEnabled));
        }


    }


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
//                    previousRow.incrementQuantiy();
//                    updated = true;
//                    break;
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
        Component[] components = getComponents();
        for (Component component : components) {
            TableRow row = (TableRow) component;
            row.setListener(actionListener);
        }



    }

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
        Component[] components = getComponents();
        for (Component component : components) {
            TableRow row = (TableRow) component;
            row.setListener(actionListener);
        }



    }


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

//    public void updateRow(String[][] Data){
//        /*
//        accepts 2D Array of String data and passes it to corresponding row to update fields
//         */
//
//        for (String[] datum : Data) {
//            constraints.gridy = getComponentCount();
//            add(new TableRow(), constraints);
//            TableRow row = (TableRow) getComponent(getComponentCount()-1);
//            row.updateRow(datum);
//            System.out.println(getComponentCount());
//        }
//
//
//    }


    public void setListener(ActionPerformed action) {
        this.actionListener = action;
    }

    public boolean removeRow(){
        int totalRows = getComponentCount();
        Component[] components = getComponents();
        if (totalRows > MINIMUM_ROWS){
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

    public void clear(){
        Component[] components = getComponents();
        for (Component component : components) {
            ((TableRow) component).reset();
        }
    }
}
