package MySQL.POSsupport;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePanel extends InputPanel {

    private TextField quantity;
    private JLabel quantityLabel;
    private JButton updateBtn;

    private StringListener listener;


        public UpdatePanel() {
        super("Return Item");

        quantity = new TextField();
        quantityLabel = new JLabel("Quantity");
        updateBtn = new JButton("UPDATE");
        updateBtn.setActionCommand(updateBtn.getText().toLowerCase());


        bagConstraints.insets = new Insets(0,5,0,5);
        bagConstraints.gridx = 4;
        bagConstraints.gridy = 0;
        bagConstraints.weightx = 0.1;
        add(quantityLabel,bagConstraints);
        quantityLabel.setForeground(ColoringConstants.foreground);
        bagConstraints.gridy++;
        add(quantity,bagConstraints);
        bagConstraints.gridy++;
        bagConstraints.insets = new Insets(5,5,5,5);
        add(updateBtn,bagConstraints);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder data = new StringBuilder();
                for (int i = 0; i < getComponentCount(); i++) {
                    if (getComponents()[i] instanceof TextField){
                        data.append(((TextField) getComponent(i)).getText());
                        data.append(" ".repeat(5));
                    }
                }
                listener.updateItem(data.toString().stripLeading().split(" ".repeat(5)));
            }
        });

        setMinimumSize(new Dimension(500,150));

        Component[] components = getComponents();
            for (Component component : components) {
                if (component instanceof TextField) {
                    component.setBackground(ColoringConstants.textFieldsBackground);
                    System.out.println(component.getSize());
                }
            }



    }

    public void setListener(StringListener listener) {
        this.listener = listener;
        super.setStringListener(listener);
    }
}

