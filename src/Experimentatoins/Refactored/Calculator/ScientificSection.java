package Experimentatoins.Refactored.Calculator;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificSection extends JPanel implements ActionListener {

    JButton exponent, sqaureroot,cuberoot, factorial ;
    private StringListener listener;
    private Updateable IOPanel;

    public ScientificSection(){
        exponent = new JButton("^x");
        exponent.setName("^");
        sqaureroot = new JButton("sqrt");
        cuberoot = new JButton("cbrt");


        setLayout(new GridLayout(3,3,6,3));

        add(exponent);
        add(sqaureroot);
        add(cuberoot);
        setVisible(false);

        exponent.addActionListener(this);
        pressCbrt();
        pressSqrt();

    }

    public void toggleVissible(){
        setVisible(!isVisible());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        listener.searchQuery(btn.getName());

    }

    public void setListener(StringListener listener) {
        this.listener = listener;
    }

    public void setIOPanel(Updateable panel){
        this.IOPanel = panel;
    }

    private void pressSqrt(){
        sqaureroot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = IOPanel.toString();
                s = Double.toString(Math.sqrt(Double.parseDouble(s)));
                IOPanel.updateOutput(s);

            }
        });
    }

    private void pressCbrt(){
        cuberoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = IOPanel.toString();
                s = Double.toString(Math.cbrt(Double.parseDouble(s)));
                IOPanel.updateOutput(s);
            }

        });
    }
}
