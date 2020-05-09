package MySQL;

import Experimentatoins.support.GridBagConstraintCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TableRow extends JPanel {
    /*
    A single row class used by table class as row
    made up of four distinct JtextField which act as columns
    contains method to update the corresponding Jtextfields
     */

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

        // Initialize row with JTextFields
        for (int i = 0; i <  COLUMNS; i++) {
            row[i] = new JTextField();
        }

        ProductName();

        Price();

        Quantity();

        subTotal();


    }

    private void ProductName() {
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = 1;
        add(row[0],constraints);
        row[0].setPreferredSize(new Dimension(700,30));
        row[0].setMaximumSize(row[0].getPreferredSize());
        row[0].setMinimumSize(row[0].getPreferredSize());
        row[0].setEditable(false);
        row[0].setOpaque(false);
        row[0].setForeground(Color.black);
    }

    private void Price() {
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        add(row[1],constraints);
        row[1].setPreferredSize(new Dimension(145,30));
        row[1].setMaximumSize(row[1].getPreferredSize());
        row[1].setMinimumSize(row[1].getPreferredSize());
        row[1].setEditable(false);
        row[1].setOpaque(false);
        row[1].setForeground(Color.BLACK);
        row[1].setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void Quantity() {
        // Quantity
        constraints.gridx = 4;
        add(row[2],constraints);
        row[2].setPreferredSize(new Dimension(145,30));
        row[2].setMaximumSize(row[2].getPreferredSize());
        row[2].setMinimumSize(row[2].getPreferredSize());
        row[2].setEnabled(true);
        row[2].setOpaque(false);
        row[2].setHorizontalAlignment(SwingConstants.RIGHT);
        quantityEditor();
    }

    private void subTotal() {
        constraints.gridx = 5;
        add(row[3],constraints);
        row[3].setPreferredSize(new Dimension(300,30));
        row[3].setMaximumSize(row[3].getPreferredSize());
        row[3].setMinimumSize(row[3].getPreferredSize());
        row[3].setEditable(false);
        row[3].setOpaque(false);
        row[3].setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void quantityEditor() {
        row[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                textField.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {


                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER){
                            row[3].setText(Float.toString(Float.parseFloat(row[1].getText()) * Integer.parseInt(row[2].getText())));
                        }

                    }
                });
            }
        });
    }


    public void updateRow(String data){
        row[0].setText(data.split("!!!")[0]);
        row[1].setText(data.split("!!!")[1]);
        row[2].setText("1");
        row[3].setText(Float.toString(Float.parseFloat(row[1].getText()) * Integer.parseInt(row[2].getText())));
    }

    public boolean isEmpty(){
        if (row[0].getText().isEmpty()) return true;
        else return false;
    }

    public String getProductName(){
        return row[0].getText();
    }

    public void incrementQuantiy(){
        row[2].setText(Integer.toString(Integer.parseInt(row[2].getText()) + 1));
        row[3].setText(Float.toString(Float.parseFloat(row[1].getText()) * Integer.parseInt(row[2].getText())));
    }

    public float subtotal(){
        return Float.parseFloat(row[3].getText());
    }
}
