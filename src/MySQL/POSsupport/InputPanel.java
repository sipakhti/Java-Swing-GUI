package MySQL.POSsupport;

import MySQL.Interfaces.ActionPerformed;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputPanel extends JPanel{
    private final GridBagLayout LAYOUT = new GridBagLayout();


    private TextField productName, Barcode;
    private JButton button;
    private ActionPerformed listener;
    private StringListener stringListener;



    GridBagConstraints bagConstraints;

    public InputPanel(String s){


        productName = new TextField();
        productName.setName("product name");
        Barcode = new TextField();
        Barcode.setName("Barcode");
        button = new JButton(s.toUpperCase());
        button.setActionCommand(Barcode.getText().toLowerCase());
        bagConstraints = new GridBagConstraints();

        setLayout(LAYOUT);




        addProductName();

        addBarcode();

        addButton();

        AddItemListener();

        productNameKeyListener();

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                productName.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


    }

    private void productNameKeyListener() {
        productName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    stringListener.searchQuery(productName.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void AddItemListener() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stringListener.searchQuery(productName.getText());
            }
        });
    }

    private void addButton() {
        bagConstraints.gridy = 2;
        bagConstraints.insets = new Insets(5,5,5,5);
        add(button,bagConstraints);
        button.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));
    }

    private void addProductName() {
        bagConstraints.weightx = 1;
        bagConstraints.weighty = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.gridwidth = 2;
        bagConstraints.insets = new Insets(0,5,0,5);
        bagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        JLabel productLabel = new JLabel("Product Name");
        productLabel.setForeground(ColoringConstants.foreground);
        add(productLabel, bagConstraints);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.weightx = 1;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.gridwidth = 2;
        add(productName,bagConstraints);
    }

    private void addBarcode() {
        bagConstraints.gridx = 3;
        bagConstraints.gridy = 0;
        bagConstraints.weightx = 0.25;
        bagConstraints.gridwidth = 1;
        JLabel barcodeLabel = new JLabel("Price");
        barcodeLabel.setForeground(ColoringConstants.foreground);
        add(barcodeLabel,bagConstraints);
        bagConstraints.gridy = 1;
        add(Barcode,bagConstraints);
    }

    public void setListener(ActionPerformed listener) {
        this.listener = listener;
    }

    public void setStringListener(StringListener stringListener) {
        this.stringListener = stringListener;
    }



}
