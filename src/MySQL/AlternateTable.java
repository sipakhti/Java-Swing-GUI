package MySQL;

import MySQL.CustomCellRenderer.FloatRenderer;
import MySQL.CustomCellRenderer.NumberRenderer;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AlternateTable extends JTable {


    private DefaultTableModel tableModel;

    public AlternateTable(){


        tableModel = new DefaultTableModel();
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Price(Rs.)");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Sub Total");
        tableModel.addRow(new Object[]{"umer",20.5F,5,30.10});
        this.setModel(tableModel);
        this.setPreferredSize(new Dimension(500,500));


        NumberRenderer numberRenderer = new NumberRenderer();
        FloatRenderer floatRenderer = new FloatRenderer();

        floatRenderer.setHorizontalAlignment(JLabel.RIGHT);
        numberRenderer.setHorizontalAlignment(JLabel.RIGHT);
        this.getColumnModel().getColumn(2).setCellRenderer(numberRenderer);
        this.getColumnModel().getColumn(1).setCellRenderer(floatRenderer);
        this.getColumnModel().getColumn(3).setCellRenderer(floatRenderer);
        this.setRowHeight(30);

        if (isEditing()) {
            this.cellEditor.addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    if (getEditingColumn() == 2) {
                        setValueAt((Float) getValueAt(getEditingRow(), 1) * (int) getValueAt(getEditingRow(), 2),
                                getEditingRow(), 3);
                    }
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }
            });

        }

    }

    public class TableModel extends DefaultTableModel {

        public TableModel (){
            super();
            this.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {

                }
            });
        }

        @Override
        public Object getValueAt(int row, int column) {
            return super.getValueAt(row, column);
        }

    }


    public void updateData(String data){
        Object[] DATA = new Object[]{
                data.split("!!!")[0],
                Float.parseFloat(data.split("!!!")[1]),
                1
        };
        tableModel.addRow(DATA);
        updateSubTotal(getRowCount()-1);
    }

    private void updateSubTotal(int row){
        setValueAt((float) getValueAt(row,1) *(int) getValueAt(row,2),row,3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("table");
        AlternateTable table = new AlternateTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setEnabled(true);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        table.updateData("Umer!!!500.00");
        int x =(int) table.getValueAt(1,2);
        float f = (float) table.getValueAt(1,1);
        System.out.println(x + x);



    }
}
