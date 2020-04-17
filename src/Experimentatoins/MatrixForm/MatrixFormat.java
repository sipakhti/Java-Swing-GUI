package Experimentatoins.MatrixForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixFormat extends JPanel {

    private JButton addCol, addRow,removeCol,removeRow;
    private Incrementable fields;

    public MatrixFormat(){
        addCol = new JButton("+ COL");
        removeCol = new JButton("- COL");
        addRow = new JButton("+ ROW");
        removeRow = new JButton("- ROW");

        setLayout(new GridLayout(2,2));
        add(addCol);
        add(removeCol);
        add(addRow);
        add(removeRow);

        addColumn();
        addRow();
        removeColumn();
        removeRow();

        setVisible(true);
    }

    public void setFields(Incrementable fields) {
        this.fields = fields;
    }

    private void addColumn(){
        addCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fields.incrementMatrixColumn();
            }
        });
    }

    private void addRow() {
        addRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fields.incrementMatrixRow();
            }
        });
    }

    private void removeColumn(){
        removeCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fields.decrementMatrixColumn();
            }
        });
    }

    private void removeRow(){
        removeRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fields.decrementMatrixRow();
            }
        });
    }
}
