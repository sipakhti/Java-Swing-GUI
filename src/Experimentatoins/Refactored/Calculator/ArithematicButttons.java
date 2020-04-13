package Experimentatoins.Refactored.Calculator;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class ArithematicButttons extends JPanel  implements ActionListener {
    private JButton add, subtract, divide, multpily;
    private StringListener listener;

    public ArithematicButttons(){
        add = new JButton("+");
        subtract = new JButton("-");
        divide = new JButton("/");
        multpily = new JButton("*");

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(add);
        add(subtract);
        add(divide);
        add(multpily);

        add.addActionListener(this);
        subtract.addActionListener(this);
        divide.addActionListener(this);
        multpily.addActionListener(this);
    }

    public void setListener(StringListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        listener.textEmitted(btn.getText());
    }
}
