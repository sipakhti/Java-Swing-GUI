package Experimentatoins.Refactored.Calculator;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberButtons extends JPanel implements ActionListener {

    private JButton one, two, three,
            four, five, six,
            seven, eight, nine, zero;

    private StringListener textListener;

    public NumberButtons() {
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");

        setLayout(new GridLayout(4, 3, 1, 1));

        add(one);
        add(two);
        add(three);
        add(four);
        add(five);
        add(six);
        add(seven);
        add(eight);
        add(nine);
        add(new JPanel());
        add(zero);

        addlistners();

    }

    private void addlistners() {
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        zero.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        textListener.searchQuery(btn.getText());
    }


    public void setTextlistener(StringListener listener) {
        this.textListener = listener;
    }
}