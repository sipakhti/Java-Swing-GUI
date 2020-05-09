package MySQL;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel{
    /*
    Main table class that holds TableRow class as rows

    couples with the controller class to update the rows
     */
    private final GridBagLayout LAYOUT = new GridBagLayout();
    private GridBagConstraints constraints;

    private ActionPerformed listener;


    public Table(){
        setBackground(Color.black);


        setLayout(LAYOUT);


        constraintParams();

    }

    private void constraintParams() {
        constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
    }


    public void updateRow(String data){
        /*
        accepts Array of String data and passes it to corresponding row to update fields
         */
        boolean updated = false;
        try {
            Component[] components = getComponents();
            for (Component component : components) {
                TableRow previousRow = (TableRow) component;
                if (previousRow.getProductName().equals(data.split("!!!")[0])) {
                    previousRow.incrementQuantiy();
                    updated = true;
                    break;
                }
            }
            if (!updated) throw new IndexOutOfBoundsException();

        }catch(IndexOutOfBoundsException e) {
            constraints.gridy = getComponentCount();
            add(new TableRow(), constraints);
            TableRow row = (TableRow) getComponent(getComponentCount() - 1);
            row.updateRow(data);

        }
        System.out.println(getComponentCount());

    }

    public float getTotal(){
        Component[] components = getComponents();
        float total = 0;
        for (Component component : components) {
            TableRow row = (TableRow) component;
            total += row.subtotal();
        }
        return total;
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


}
