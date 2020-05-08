package MySQL;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputPanel extends JPanel{
    private final GridBagLayout LAYOUT = new GridBagLayout();


    private TextField productName, Barcode;
    private JButton addItem;
    private ActionPerformed listener;
    private StringListener stringListener;


    private GridBagConstraints bagConstraints;

    public InputPanel(){


        productName = new TextField();
        productName.setName("product name");
        Barcode = new TextField();
        Barcode.setName("Barcode");
        addItem = new JButton("ADD ITEM");
        bagConstraints = new GridBagConstraints();

        setLayout(LAYOUT);




        addProductName();

        addBarcode();

        addAddItem();

        AddItemListener();

        productNameKeyListener();

        setVisible(true);
    }

    private void productNameKeyListener() {
        productName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    stringListener.textEmitted(productName.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void AddItemListener() {
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed();
            }
        });
    }

    private void addAddItem() {
        bagConstraints.gridy = 2;
        bagConstraints.insets = new Insets(5,5,5,5);
        add(addItem,bagConstraints);
    }

    private void addProductName() {
        bagConstraints.weightx = 1;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.gridwidth = 2;
        bagConstraints.insets = new Insets(0,5,0,5);
        bagConstraints.anchor = GridBagConstraints.SOUTHWEST;
        add(new JLabel("Product Name"), bagConstraints);

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
        add(new JLabel("Barcode"),bagConstraints);
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
