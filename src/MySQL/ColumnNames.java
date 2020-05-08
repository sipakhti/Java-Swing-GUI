package MySQL;

import javax.swing.*;
import java.awt.*;

public class ColumnNames extends JPanel {
    private final GridBagLayout LAYOUT = new GridBagLayout();

    private GridBagConstraints constraints;
    private JLabel col1, col2, col3, col4;


    public ColumnNames(){
        /*
        provide labels for table columns
         */
        //setBackground(Color.PINK);
        col1 = new JLabel("PRODUCT");
        col2 = new JLabel("PRICE");
        col3 = new JLabel("QTY");
        col4 = new JLabel("SUB TOTAL");
        constraints = new GridBagConstraints();

        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = 1;
        col1.setPreferredSize(new Dimension(700,10));
        col1.setMaximumSize(col1.getPreferredSize());
        col1.setMinimumSize(col1.getPreferredSize());
        col1.setBackground(Color.BLUE);
        add(col1,constraints);


        constraints.gridx = 3;
        constraints.gridwidth = 1;
        col2.setPreferredSize(new Dimension(144,10));
        col2.setMaximumSize(col2.getPreferredSize());
        col2.setMinimumSize(col2.getPreferredSize());
        col2.setBackground(Color.CYAN);
        add(col2,constraints);

        constraints.gridx = 4;
        col3.setPreferredSize(new Dimension(144,10));
        col3.setMaximumSize(col3.getPreferredSize());
        col3.setMinimumSize(col3.getPreferredSize());
        col3.setBackground(Color.DARK_GRAY);
        add(col3,constraints);

        constraints.gridx = 5;
        col4.setPreferredSize(new Dimension(300,10));
        col4.setMaximumSize(col4.getPreferredSize());
        col4.setMinimumSize(col4.getPreferredSize());
        col4.setBackground(Color.GREEN);
        add(col4,constraints);


    }
}
