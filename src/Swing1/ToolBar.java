package Swing1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar
        extends JPanel
        implements ActionListener {

    private JButton helloButton, goodByeButton;
    private StringListener textListener;

    public ToolBar(){

        helloButton = new JButton("Hello");
        goodByeButton = new JButton("GOODBYE");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(helloButton);
        add(goodByeButton);

        helloButton.addActionListener(this);
        goodByeButton.addActionListener(this);


    }

    public void setTextListener(StringListener listener){
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var button = e.getSource();
        if (button == helloButton)
            textListener.searchQuery("Hello\n");
        else
            textListener.searchQuery("GoodBye\n");
    }
}
