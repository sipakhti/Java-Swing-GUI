package MySQL;

import Experimentatoins.support.GridBagConstraintCustom;
import MySQL.Interfaces.ActionPerformed;
import MySQL.POSsupport.*;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class POSMain extends JFrame {

    private ProductTable productTable;
    private InputPanel inputPanel;
    private GridBagConstraintCustom constraints;
    private ColumnNames columnNames;
    private DataBaseConnection dbconn;


    private Table table = new Table(false);
    private GrandTotal grandTotal;
    private SavePanel savePanel;
    private JScrollPane scrollPane;
    private Box box;

    public POSMain(String username, String password) throws SQLException {
        super("test");




        dbconn = new DataBaseConnection(username,password);

        inputPanel = new InputPanel("Remove Item");
        productTable = new ProductTable();
        productTable.setMatrix(1,4);
        constraints = new GridBagConstraintCustom();
        columnNames = new ColumnNames();
        grandTotal = new GrandTotal();
        savePanel = new SavePanel();
        box = Box.createVerticalBox();
        box.add(table);
        scrollPane = new JScrollPane(box);
        setLayout(new GridBagLayout());

        addInputPanel();

        addcolumnLabels();

        addProductTable();

        addGrandTotal();

        addSavePanel();

        savePanelStringListener();

        inputPanelListeners();

        tableRowActionPerformed();

        mainPanelProperties();
    }

    private void tableRowActionPerformed() {
        table.setListener(new ActionPerformed() {
            @Override
            public void actionPerformed() {
                grandTotal.setTotal(table.getTotal());
            }

            @Override
            public void actionCommand(String text) {

            }
        });
    }

    private void savePanelStringListener() {
        savePanel.setListener(new StringListener() {
            @Override
            public void searchQuery(String text) {
                if (text == "new") tablePurge();

                else if (text == "save"){
                    transactionEnd();
                }
            }

            @Override
            public void updateItem(String[] rowData) {

            }

            @Override
            public void itemSelected(int n) {

            }
        });
    }



    private void inputPanelListeners() {

        // listens to press of the ADD_ITEM Button
        inputPanel.setListener(new ActionPerformed() {
            @Override
            public void actionPerformed() {
//
                table.removeRow();

                box.revalidate();
                box.repaint();
                scrollPane.revalidate();
                revalidate();
                repaint();
            }

            @Override
            public void actionCommand(String text) {

            }
        });

        // Listens to ProductName textfield and executes when ENTER is pressed
        inputPanel.setStringListener(new StringListener() {
            @Override
            public void searchQuery(String text) {
                try {
//                    table.removeAll();
                    dbconn.searchQuery(text);
                    table.updateRow(dbconn.rowData());
                    grandTotal.setTotal(table.getTotal());
//                    table.requestFocus();
                    inputPanel.requestFocus();
                    box.revalidate();
                    box.repaint();
                    scrollPane.revalidate();
                    revalidate();
                    repaint();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void updateItem(String[] rowData) {

            }

            @Override
            public void itemSelected(int n) {

            }
        });
    }




    private void addProductTable() {
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        constraints.weightx = 1;
        constraints.insets = new Insets(5,5,10,5);
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        add(scrollPane,constraints);
        constraints.reset();
        pack();
    }

    private void addInputPanel() {
        constraints.gridwidth = 6;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
        add(inputPanel, constraints);
        constraints.reset();
        pack();
    }

    private void addSavePanel() {
        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        constraints.insets = new Insets(5,5,5,5);
        add(savePanel,constraints);
        constraints.reset();
    }

    private void addGrandTotal() {
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        constraints.weightx = 0;
        constraints.insets = new Insets(5,5,5,5);
        constraints.ipadx = 1;
        add(grandTotal,constraints);
        box.revalidate();
        box.repaint();
        scrollPane.revalidate();
        revalidate();
        repaint();
        constraints.reset();
    }

    private void addcolumnLabels() {
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.weightx = 1;
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,5,0,5);
        constraints.gridwidth = 6;
        add(columnNames,constraints);
        constraints.reset();
        pack();
    }

    private void mainPanelProperties() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    dbconn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


//        setPreferredSize(new Dimension(1300,400));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        pack();
        requestFocus();
    }



    private void tablePurge(){
        table.removeAll();
        revalidate();
        repaint();
    }

    private void transactionEnd(){
        String[][] tableData = table.tableSaveData();
        int productCount = tableData.length;
        String barcode = "";
        try {
            dbconn.addTransaction();
            long transactionID = dbconn.getTransactionID();
            for (int i = 0; i < productCount; i++) {
                barcode = dbconn.getBarcode(tableData[i][0]);
                dbconn.addTransactionDetails(transactionID,barcode,Integer.parseInt(tableData[i][1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
