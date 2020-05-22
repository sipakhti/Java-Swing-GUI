package Experimentatoins.MatrixForm;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixFunctions extends JPanel implements ActionListener {
    private JButton inverse, add, subtract, multiply;
    private StringListener listener;


    public MatrixFunctions(){
        inverse = new JButton("INVERSE");
        add = new JButton("ADD");
        subtract = new JButton("SUBTRACT");
        multiply = new JButton("MULTIPLY");

        add.setActionCommand("add");
        subtract.setActionCommand("sub");
        multiply.setActionCommand("mul");
        inverse.setActionCommand("inv");

        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(add);
        add(subtract);
        add(multiply);
        add(inverse);

        inverse.addActionListener(this);
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
    }

    public void setListener(StringListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       listener.searchQuery(e.getActionCommand());
    }
}
