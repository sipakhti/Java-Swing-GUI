package Experimentatoins.Refactored.Calculator;

import Experimentatoins.support.ArithmeticalOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InOutFields extends JPanel
                        implements Updateable,
                                    getAble{

    private TextField inputField, outputField;
    private String input ="";

    public InOutFields() {

        inputField = new TextField();
        outputField= new TextField();

        setLayout(new GridLayout(2,1));

        add(inputField);
        add(outputField);


        outputField.setEditable(false);
        //inputField.requestFocusInWindow();
    }
    @Override
    public void updateInput(String s){
        try{
            int i = Integer.parseInt(s);
            input += s;
            inputField.setText(input);
        } catch (NumberFormatException ex) {
            inputField.setText("");
            input = "";
        }

    }
    @Override
    public void updateOutput(String s){
        outputField.setText(s);
        input="";
    }
    @Override
    public void resetFields(){
        inputField.setText("");
        outputField.setText("");
        input = "";
    }

    public String getInput() {
        return input;
    }

    @Override
    public String toString() {
        return inputField.getText();
    }
}
