package Experimentatoins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Experimentatoins.support.ArithmeticalOperations.ArthOp;
import static java.awt.event.KeyEvent.*;

public class Calculator extends JFrame {
    private JPanel mainPanel;
    private JButton resetButton;
    private JTextField inputField;
    private JButton a7Button;
    private JButton a1Button;
    private JButton a4Button;
    private JButton a2Button;
    private JButton a5Button;
    private JButton a8Button;
    private JButton calculate;
    private JButton a3Button;
    private JButton a6Button;
    private JButton a9Button;
    private JButton a0Button;
    private JButton ADDButton;
    private JButton SUBTRACTButton;
    private JButton DIVIDEButton;
    private JButton MULTIPLYButton;
    private JTextField outputField;
    private JButton sciButton;
    private JPanel science;
    private JButton button1;
    private JButton button2;
    private JComboBox modeSelector;
    private String input = "";
    private String equation = "";
    private String output = "";
    private ScientificCalculator Sci;


    public Calculator(String title){
        super(title);
        outputField.setEditable(false);
        Sci = new ScientificCalculator();
        add(Sci);

        science.setVisible(false);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        modeSelector.addItem("Normal");
        modeSelector.addItem("Scientific");
        pressButtons();
        getKey();


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void pressButtons() {
        pressNumericButton(a1Button);
        pressNumericButton(a2Button);
        pressNumericButton(a3Button);
        pressNumericButton(a4Button);
        pressNumericButton(a5Button);
        pressNumericButton(a6Button);
        pressNumericButton(a7Button);
        pressNumericButton(a8Button);
        pressNumericButton(a9Button);
        pressNumericButton(a0Button);

        pressArthButtons(ADDButton);
        pressArthButtons(SUBTRACTButton);
        pressArthButtons(DIVIDEButton);
        pressArthButtons(MULTIPLYButton);

        pressCalculate();
        reset();
        pressSci();

    }

    private void reset(){
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                outputField.setText("");
                input = "";
                equation = "";

            }
        });
    }

    private void pressArthButtons(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                equation += button.getText();
                inputField.setText("");
                input = "";

            }
        });
    }


    private void pressNumericButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input += button.getText();
                equation += button.getText();
                inputField.setText(input);
            }
        });
    }


    private void pressCalculate() {
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                output = ArthOp(equation);
                outputField.setText(output);
                equation = output;
                input = "";
                inputField.setText("");

            }
        });
    }

    private void pressSci() {

        modeSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox option =(JComboBox) e.getSource();
                if (option.getSelectedItem() == "Normal") {
                    setSize(250, 250);
                    science.setVisible(false);
                }
                else {
                    setSize(500, 250);
                    science.setVisible(true);
                }
            }
        });
    }

    private void getKey(){
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    equation += inputField.getText();
                    output = ArthOp(equation);
                    outputField.setText(output);
                    equation = output;
                    input = "";
                    inputField.setText("");
                }
                if (key == VK_ADD) {
                    equation += inputField.getText();
                    inputField.setText("");
                    input = "";
                }
                if (key == VK_SUBTRACT){
                    equation += inputField.getText() ;
                    inputField.setText("");
                    input = "";
                }
                if (key == VK_DIVIDE) {
                    equation +=  inputField.getText();
                    inputField.setText("");
                    input = "";
                }
                if (key == VK_MULTIPLY) {
                    equation += inputField.getText();
                    inputField.setText("");
                    input = "";
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new Calculator("TETS");
        frame.setVisible(true);
        frame.requestFocus();
        frame.setAlwaysOnTop(true);


    }
}
