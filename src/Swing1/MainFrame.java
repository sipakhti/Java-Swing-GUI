package Swing1;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    private TextPanel textPanel;
    private ToolBar toolBar;

    public MainFrame(String title){
        super(title);


        setLayout(new BorderLayout());
        textPanel = new TextPanel();

        toolBar = new ToolBar();



        add(textPanel);

        add(toolBar,BorderLayout.NORTH);

        toolBar.setTextListener(new StringListener() {
            @Override
            public void searchQuery(String text) {
                textPanel.appendText(text);
            }

            @Override
            public void updateItem(String[] rowData) {

            }

            @Override
            public void itemSelected(int n) {

            }
        });


        setSize(600,600);
        setLocation(100,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        requestFocus();
        setResizable(false);




    }



}
