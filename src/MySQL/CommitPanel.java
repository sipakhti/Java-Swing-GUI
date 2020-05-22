package MySQL;

import MySQL.Interfaces.CommitListener;
import MySQL.POSsupport.ColoringConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommitPanel extends JPanel {

    private JButton Return, Update;
    private CommitListener listener;

    public CommitPanel(){
        Return = new JButton("Commit Return");
        Return.setActionCommand("return");

        Update = new JButton("Commit Update");
        Update.setActionCommand("update");

        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(Return);
        add(Update);

        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.returnListener();
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.updateListener();
            }
        });

        setBackground(ColoringConstants.backgroud);

    }

    public void setListener(CommitListener listener) {
        this.listener = listener;
    }

}
