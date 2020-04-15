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

    private GridBagConstraints c = new GridBagConstraints();

    private Updateable IOPanel;
    private JLabel inlabel, outlabel;
    private ArithematicButttons arithematicButttons;
    private NumberButtons numberPanel;
    private String equation = "";
    private JButton calculate, CLS;
    private ScientificSection scientificSection;
    private JButton test = new JButton("test");


    public MainFrame(String title){
        super(title);

        IOPanel = new InOutFields();
        inlabel = new JLabel("Input: ");
        outlabel  = new JLabel("Output: ");
        numberPanel = new NumberButtons();
        arithematicButttons = new ArithematicButttons();
        calculate = new JButton("CALCULATE");
        CLS = new JButton("CLS");
        scientificSection = new ScientificSection();

        scientificSection.setIOPanel(IOPanel);



        setLayout(new GridBagLayout());
// ADD TEXT FIELD LABELS
        ADDTEXTFIELDLABELS();
// ADD TEXT FIELDS
        ADDTEXTFIELDS();
// ADD CLEAR BUTTON
        ADDCLEARBUTTON();
// ADD ARTH BUTTONS
        ADDARTHBUTTONS();
// ADD NUMBER BUTTONS
        ADDNUMBERBUTTONS();
// ADD CALCULATE BUTTON
        ADDCALCULATEBUTTON();



        c.gridx = 4;
        c.gridwidth = 4;
        c.gridy = 1;
        c.gridheight = 9;
        add(scientificSection,c);


        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scientificSection.toggleVissible();
            }
        });

        scientificSection.setListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                IOPanel.updateInput(text);
                equation += text;
            }
        });

        pressNumbers();
        pressArithmeticButtons();
        pressCalculate();
        pressCLS();


        setMinimumSize(new Dimension(500,-1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        requestFocus();

    }

    private void ADDCALCULATEBUTTON() {
        c.gridheight = 1;
        c.gridy = 9;
        add(calculate,c);
    }

    private void ADDNUMBERBUTTONS() {
        c.gridwidth = 2;
        c.gridheight = 4;
        c.gridx = 1;
        c.gridy = 4;
        add(numberPanel,c);
    }

    private void ADDARTHBUTTONS() {
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        c.gridy = 2;
        c.weightx = 1;
        c.gridx = 0;
        add(arithematicButttons,c);
    }

    private void ADDCLEARBUTTON() {
        c.weightx = 0;
        c.fill = 1;
        c.gridwidth = 0;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 1;
        add(test,c);
        c.gridy = 0;
        add(CLS,c);

    }

    private void ADDTEXTFIELDS() {
        c.weightx = 1;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 1;
        c.gridy = 0;
        add((JPanel) IOPanel,c);
    }

    private void ADDTEXTFIELDLABELS() {

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        add(inlabel,c);

        c.gridy = 1;
        add(outlabel,c);
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
                try {
                    equation = ArithmeticalOperations.ArthOp(equation);
                    IOPanel.updateOutput(equation);
                }catch (NumberFormatException ex){
                    IOPanel.resetFields();
                }

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

}

