package MySQL;

import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    private final GridLayout LAYOUT = new GridLayout(1,1);
    private GridBagConstraints constraints;


    public Table(){
        setBackground(Color.black);
        constraints = new GridBagConstraints();
        setLayout(LAYOUT);

        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        add(new TableRow());


    }

    public void updateRow(String[] data){
        Component[] components =  getComponents();
        boolean hasSpace = false;

        for (Component component : components) {
            TableRow row = (TableRow) component;
            if (row.isEmpty()) {
                hasSpace = true;
                break;
            }
        }

        if (hasSpace){
            constraints.gridy = getComponentCount();
            LAYOUT.setRows(LAYOUT.getRows()+1);
            add(new TableRow());
        }

        for (Component component : components) {
            TableRow row = (TableRow) component;
            if (row.isEmpty()) {
                row.updateRow(data);
                break;
            }
            else continue;
        }
    }

}
