package Experimentatoins.Refactored.Calculator;

import Experimentatoins.support.ArithmeticalOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InOutFields extends JPanel
                        implements Updateable{

    private TextField inputField, outputField;
    private String input ="";

    public InOutFields() {

        inputField = new TextField();
        outputField= new TextField();

        setLayout(new GridLayout(2,1));

        add(inputField);
        add(outputField);

        getKey();

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


    private void getKey() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    inputField.setText("");
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
}
