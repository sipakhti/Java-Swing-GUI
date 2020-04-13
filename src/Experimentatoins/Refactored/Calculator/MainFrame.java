package Experimentatoins.Refactored.Calculator;

import Experimentatoins.support.ArithmeticalOperations;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {

    private Updateable IOPanel;
    private JLabel inlabel, outlabel;
    private ArithematicButttons arithematicButttons;
    private NumberButtons numberPanel;
    private String equation = "";
    private JButton calculate, CLS;


    public MainFrame(String title){
        super(title);

        IOPanel = new InOutFields();
        inlabel = new JLabel("Input: ");
        outlabel  = new JLabel("Output: ");
        numberPanel = new NumberButtons();
        arithematicButttons = new ArithematicButttons();
        calculate = new JButton("CALCULATE");
        CLS = new JButton("CLS");



        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
// ADD LABELS
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        add(inlabel,c);

        c.gridy = 1;
        add(outlabel,c);
// ADD TEXT FIELDS
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        add((JPanel) IOPanel,c);

// ADD CLEAR BUTTON
        c.weightx = 0;
        c.fill = 0;
        c.gridwidth = 0;
        c.gridheight = 2;
        c.gridx = 3;
        c.gridy = 0;
        add(CLS,c);
// ADD ARTH BUTTONS
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridy = 2;
        c.weightx = 1;
        c.gridx = 0;
        add(arithematicButttons,c);
// ADD NUMBER BUTTONS
        c.gridwidth = 2;
        c.gridheight = 4;
        c.gridx = 1;
        c.gridy = 4;
        add(numberPanel,c);
// ADD CALCULATE BUTTON
        c.gridheight = 1;
        c.gridy = 9;
        add(calculate,c);


        pressNumbers();
        pressArithmeticButtons();
        pressCalculate();
        pressCLS();
        getKey();

        setMinimumSize(new Dimension(500,-1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();

    }

    private void pressNumbers() {
        numberPanel.setTextlistener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                IOPanel.updateInput(text);
                equation += text;
            }
        });
    }

    private void pressArithmeticButtons() {
        arithematicButttons.setListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                IOPanel.updateInput(text);
                equation += text;
            }
        });
    }

    private void pressCalculate() {
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = ArithmeticalOperations.ArthOp(equation);
                IOPanel.updateOutput(equation);
            }
        });
    }

    private void pressCLS() {
        CLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equation = "";
                IOPanel.resetFields();
            }
        });
    }

    private void getKey() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    equation = ArithmeticalOperations.ArthOp(equation);
                    IOPanel.updateOutput(equation);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
}

