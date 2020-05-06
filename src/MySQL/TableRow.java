package MySQL;

import Experimentatoins.support.GridBagConstraintCustom;

import javax.swing.*;
import java.awt.*;

public class TableRow extends JPanel {

    //private final BoxLayout LAYOUT = new BoxLayout(this,BoxLayout.X_AXIS);
    private final GridBagLayout LAYOUT = new GridBagLayout();
    private final int COLUMNS = 4;

    private JTextField[] row;
    private GridBagConstraintCustom constraints;

    public TableRow(){

        row = new JTextField[COLUMNS];
        constraints = new GridBagConstraintCustom();

        setLayout(LAYOUT);
        setVisible(true);
        setAutoscrolls(true);

        for (int i = 0; i <  COLUMNS; i++) {
            row[i] = new JTextField();
        }


        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = 1;
        add(row[0],constraints);
        row[0].setPreferredSize(new Dimension(600,30));
        row[0].setMaximumSize(row[0].getPreferredSize());
        row[0].setMinimumSize(row[0].getPreferredSize());

        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        add(row[1],constraints);
        row[1].setPreferredSize(new Dimension(200,30));
        row[1].setMaximumSize(row[0].getPreferredSize());
        row[1].setMinimumSize(row[0].getPreferredSize());


        constraints.gridx = 4;
        add(row[2],constraints);
        row[2].setPreferredSize(new Dimension(200,30));
        row[2].setMaximumSize(row[0].getPreferredSize());
        row[2].setMinimumSize(row[0].getPreferredSize());



        constraints.gridx = 5;
        add(row[3],constraints);
        row[3].setPreferredSize(new Dimension(200,30));
        row[3].setMaximumSize(row[0].getPreferredSize());
        row[3].setMinimumSize(row[0].getPreferredSize());



    }

    public void updateRow(String[] data){
        for (int i = 0; i < COLUMNS; i++) {
            row[i].setText(data[i]);
        }
        row[0].setSize(100,10);
    }

    public boolean isEmpty(){
        if (row[0].getText().isEmpty()) return true;
        else return false;
    }
}
