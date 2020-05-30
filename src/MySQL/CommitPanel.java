package MySQL;


import MySQL.Interfaces.CommitListener;
import MySQL.POSsupport.ColoringConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Holds three buttons that either return or update the database accordingly, and the 3rd button clears the table
 */
public class CommitPanel extends JPanel {


    private JButton Return, Update, purge;
    private CommitListener listener;

    public CommitPanel(){

        Return = new JButton("Commit Return");

        Update = new JButton("Commit Update");

        purge = new JButton("Clear Table");



        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(Return);
        add(Update);
        add(purge);

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

        purge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.purgeListener();
            }
        });


        setBackground(ColoringConstants.backgroud);

    }

    /**
     *
     * @param listener is an implementation of Interface <code>CommitListener</code>
     *
     *   sets the listener so that individual button press can be controlled by the controller class
     */

    public void setListener(CommitListener listener) {
        this.listener = listener;
    }

}
