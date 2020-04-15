package Swing1;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    private JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();

        setLayout(new BorderLayout());
        add(new JScrollPane(textArea),BorderLayout.CENTER);

    }

    void appendText(String s){
        textArea.append(s);
    }
}
