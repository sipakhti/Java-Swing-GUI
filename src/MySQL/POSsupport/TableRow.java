package MySQL.POSsupport;

import Experimentatoins.support.GridBagConstraintCustom;
import MySQL.Interfaces.ActionPerformed;
import MySQL.Interfaces.Row;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * the class that holds 4 JtextFields and thus acts as the rows of the Table
 *
 * @author sipkahti
 *
 * @version 1.0
 */
public class TableRow extends JPanel implements Row {
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
    private ActionPerformed action;

    private boolean rowEnabled;

    /**
     * Default constructor that only enables the 3rd Column i.e <i>Quantity</i>
     */

    public TableRow() {
        /*
        default constructor that the POSMain class uses as rows
         */
        row = new JTextField[COLUMNS];
        constraints = new GridBagConstraintCustom();


        setLayout(LAYOUT);
        setVisible(true);
        setAutoscrolls(true);

        // Initialize row with JTextFields
        for (int i = 0; i < COLUMNS; i++) {
            row[i] = new JTextField();
            row[i].setBorder(new LineBorder(ColoringConstants.foreground));
        }


        ProductName();

        Price();

        Quantity();

        subTotal();

        quantityEditor();

        addFocus();
    }


    /**
     *
     * @param rowEnabled true enables every {@link JTextField}
     *                   false disables all {@link JTextField} except the one responsible for <i>Quantity</i>
     */
    public TableRow(boolean rowEnabled){
        /*
         * this constructor is used when the all table columns should be editable
         *
         * used by the InventoryUpdate class.
         */
        this.rowEnabled = rowEnabled;

        row = new JTextField[COLUMNS];
        constraints = new GridBagConstraintCustom();


        setLayout(LAYOUT);
        setVisible(true);
        setAutoscrolls(true);

        // Initialize row with JTextFields
        for (int i = 0; i <  COLUMNS; i++) {
            row[i] = new JTextField();
            row[i].setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
            row[i].setBorder(new LineBorder(ColoringConstants.border));
            row[i].setForeground(ColoringConstants.textFieldsForeground);
            row[i].setCaretColor(Color.RED);
        }




        ProductName();

        Price();

        Quantity();

        subTotal();

        if (!rowEnabled) subTotal();

        setRowEnabled(rowEnabled);

        row[3].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) action.rowUpdated();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        addFocus();
        setBackground(ColoringConstants.textFieldsBackground);

    }

    /**
     * use this constrcutor when you want to use this class as a table header
     * @param rowEnabled sets every {@link JTextField} to non editable
     * @param isHeader identifies that this class is to used as a table header and sets header specific settings
     *
     */

    public TableRow(boolean rowEnabled, boolean isHeader){


        this.rowEnabled = rowEnabled;
        row = new JTextField[COLUMNS];
        constraints = new GridBagConstraintCustom();


        setLayout(LAYOUT);
        setVisible(true);
        setAutoscrolls(true);

        // Initialize row with JTextFields
        for (int i = 0; i <  COLUMNS; i++) {
            row[i] = new JTextField();
            row[i].setForeground(ColoringConstants.foreground);
            row[i].setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
            row[i].setBorder(new LineBorder(ColoringConstants.border));


        }
        setBackground(new Color(43, 43, 43));

        row[0].setText("PRODUCT");
        row[1].setText("PRICE");
        row[2].setText("QUANTITY");
        row[3].setText("BARCODE");


        ProductName();

        Price();

        Quantity();

        subTotal();

        setRowEnabled(rowEnabled);
    }

    private void addFocus() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                row[0].requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    private void ProductName() {
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.fill = 1;
        add(row[0],constraints);
        row[0].setPreferredSize(new Dimension(700,25));
        row[0].setMaximumSize(row[0].getPreferredSize());
        row[0].setMinimumSize(row[0].getPreferredSize());
        row[0].setEditable(false);
        row[0].setOpaque(false);
    }

    private void Price() {
        constraints.gridwidth = 1;
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 0.25;
        add(row[1],constraints);
        row[1].setPreferredSize(new Dimension(145,25));
        row[1].setMaximumSize(row[1].getPreferredSize());
        row[1].setMinimumSize(row[1].getPreferredSize());
        row[1].setEditable(false);
        row[1].setOpaque(false);
        row[1].setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void Quantity() {
        // Quantity
        constraints.gridx = 4;
        add(row[2],constraints);
        row[2].setPreferredSize(new Dimension(145,25));
        row[2].setMaximumSize(row[2].getPreferredSize());
        row[2].setMinimumSize(row[2].getPreferredSize());
        row[2].setEnabled(true);
        row[2].setOpaque(false);
        row[2].setHorizontalAlignment(SwingConstants.RIGHT);

    }

    private void subTotal() {
        constraints.gridx = 5;
        add(row[3],constraints);
        row[3].setPreferredSize(new Dimension(300,25));
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
                            action.quantityUpdated();
                        }

                    }
                });
            }
        });
    }


    /**
     * updates row data according to the constructor used
     */
    @Override
    public void updateRow(String data) {
        System.out.println(data);
        if (rowEnabled) {
            row[0].setText(data.split("!!!")[0]);
            row[1].setText(data.split("!!!")[1]);
            row[2].setText("1");
            row[3].setText(data.split("!!!")[2]);
        } else {
            row[0].setText(data.split("!!!")[0]);
            row[1].setText(data.split("!!!")[1]);
            row[2].setText("1");
            row[3].setText(Float.toString(Float.parseFloat(row[1].getText()) * Integer.parseInt(row[2].getText())));
        }
    }

    public void updateRow(String[] data){
        for (int i = 0; i < data.length; i++) {
            row[i].setText(data[i]);
        }
    }

    /**
     * @return <P>true if the row doesn't have any data
     *         false is row has some data</p>
     *         <p>
     *         <b>NOTE:</b> <i>it only checks the first column</i>
     *         </p>
     */
    @Override
    public boolean isEmpty(){
        if (row[0].getText().isEmpty() || row[0].getText().isBlank()) return true;
        else return false;
    }

    /**
     *
     * @return the vale of the first column
     */
    public String getProductName(){
        return row[0].getText();
    }

    /**
     * increments value in the 3rd column i.e <i>Quantity</i> by <b>1</b>
     */
    @Override
    public void incrementQuantity(){
        if (row[2].getText().isEmpty() || row[2].getText().isBlank())
            row[2].setText("1");
        row[2].setText(Integer.toString(Integer.parseInt(row[2].getText()) + 1));
        row[3].setText(Float.toString(Float.parseFloat(row[1].getText()) * Integer.parseInt(row[2].getText())));
    }

    /**
     * <p>
     *     increments value in the 3rd Column i.e <i>Quantity</i>
     * </p>
     * @param val the value to be added to the orginal value
     */
    @Override
    public void incrementQuantity(int val){
        if (row[2].getText().isEmpty() || row[2].getText().isBlank())
            row[2].setText("1");
        row[2].setText(Integer.toString(Integer.parseInt(row[2].getText()) + val));
    }

    /**
     *
     * @return value in the 4th column i.e <i>Sub-Total</i>
     */
    @Override
    public float subtotal(){
        return Float.parseFloat(row[3].getText());
    }

    /**
     * Used to get <b><i>product name</i> and <i>quantity</i></b> respectively
     *
     * @return an array of String containing data of the <b>1st</b> and <b>2nd</b>
     *         Column
     */
    @Override
    public String[] saveData(){
        String[] data = new String[2];
        data[0] = row[0].getText();
        data[1] = row[2].getText();

        return data;
    }

    /**
     *
     * @return an array of string containing data of all the columns
     */
    @Override
    public String[] updateData(){
        String[] data = new String[COLUMNS];
        for (int i = 0; i < COLUMNS; i++) {
            data[i] = row[i].getText();
        }
        return data;
    }

    /**
     * clears all the columns. calls {@link JTextField}.<i>setText</i>(<b>""</b>)
     */
    @Override
    public void reset(){

        row[0].setText("");
        row[1].setText("");
        row[2].setText("");
        row[3].setText("");

    }

    /**
     *
     * @param rowEnabled true: enables all columns
     *                   false: disables all columns
     */
    public void setRowEnabled(boolean rowEnabled){
            for (int i = 0; i < row.length; i++) {
                row[i].setEditable(rowEnabled);
            }
    }

    /**
     * <p>
     *     provides other classes to be notified when quantity is updated
     *      or row is updated
     * </p>
     * @param action inject instance of {@link ActionPerformed}
     */
    public void setListener(ActionPerformed action) {
        this.action = action;
    }

    /**
     *
     * @param color desired foreground color of Columns
     */
    public void setallForeground(Color color){
        for (JTextField textField : row) {
            textField.setForeground(color);
        }
    }
}
