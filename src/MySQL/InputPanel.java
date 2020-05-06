package MySQL;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {

    private TextField productName, Barcode;
    private JButton addItem;
    private final GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints bagConstraints;

    public InputPanel(){

        productName = new TextField();
        productName.setName("product name");
        Barcode = new TextField();
        Barcode.setName("Barcode");
        addItem = new JButton("ADD ITEM");
        bagConstraints = new GridBagConstraints();

        setLayout(gridBagLayout);

        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        bagConstraints.weightx = 1;
        bagConstraints.insets = new Insets(0,5,0,10);
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.gridwidth = 3;
        add(productName,bagConstraints);

        bagConstraints.gridx = 3;
        bagConstraints.gridy = 0;
        bagConstraints.weightx = 0.25;
        bagConstraints.gridwidth = 1;
        add(Barcode,bagConstraints);

        bagConstraints.gridy = 1;
        add(addItem,bagConstraints);


        setVisible(true);
    }
}
