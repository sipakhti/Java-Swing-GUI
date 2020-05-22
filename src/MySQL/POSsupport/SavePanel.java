package MySQL.POSsupport;

import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel extends JPanel implements ActionListener {

    private final FlowLayout LAYOUT = new FlowLayout(FlowLayout.RIGHT);

    private JButton saveBtn, newBtn;
    private StringListener listener;


    public SavePanel(){
        setLayout(LAYOUT);
        setEnabled(true);
        setVisible(true);

        saveBtn = new JButton("SAVE");
        saveBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        saveBtn.setActionCommand("save");
        add(saveBtn);
        saveBtn.addActionListener(this);

        newBtn = new JButton("NEW");
        newBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        newBtn.setActionCommand("new");
        add(newBtn);
        newBtn.addActionListener(this);



    }

    public void setListener(StringListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.searchQuery(e.getActionCommand());
    }
}
