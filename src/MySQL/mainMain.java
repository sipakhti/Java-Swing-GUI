package MySQL;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class mainMain extends JFrame {

    public mainMain(){
        super();
        var constraints = new GridBagConstraints();
        Table table = new Table();
        setMinimumSize(new Dimension(300,100));
        setSize(500,500);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        requestFocus();



        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        constraints.weightx = 1;
//        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = 1;
        add(table,constraints);
        pack();


        String[] data = new String[4];
        Scanner input = new Scanner(System.in);
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                data[column] = input.nextLine();
            }
            table.updateRow(data);
            pack();
        }

    }

    public static void main(String[] args) {
        var test = new mainMain();

    }
}
