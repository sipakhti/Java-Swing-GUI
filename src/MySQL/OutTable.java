package MySQL;

import Experimentatoins.support.GridBagConstraintCustom;
import Swing1.StringListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class OutTable extends JFrame {

    private ProductTable productTable;
    private InputPanel inputPanel;
    private GridBagConstraintCustom constraints;
    private ColumnNames columnNames;
    private DataBaseConnection dbconn;


    private Table table = new Table();

    public OutTable() {
        super("test");


        dbconn = new DataBaseConnection();
        try {
            dbconn.connectDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inputPanel = new InputPanel();
        productTable = new ProductTable();
        productTable.setMatrix(1,4);
        constraints = new GridBagConstraintCustom();
        columnNames = new ColumnNames();
        setLayout(new GridBagLayout());

        addInputPanel();

        addcolumnLabels();

        addProductTable();

        inputPanelListeners();

        tableKeyListener();

        mainPanelProperties();
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

    private void inputPanelListeners() {

        // listens to press of the ADD_ITEM Button
        inputPanel.setListener(new ActionPerformed() {
            @Override
            public void actionPerformed() {
                try {
                    table.updateRow(dbconn.rowData());
                    table.requestFocus();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                pack();
            }
        });

        // Listens to ProductName textfield and executes when ENTER is pressed
        inputPanel.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                try {
                    table.removeAll();
                    dbconn.searchQuery(text);
                    table.updateRow(dbconn.completeData());
                    pack();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void tableKeyListener() {
        table.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE){

                        table.remove(table.getComponentCount() - 1);
                        try {
                            dbconn.previousRow();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        revalidate();
                        repaint();
                        pack();

                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }


    private void addProductTable() {
        constraints.gridy = 2;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 6;
        constraints.weightx = 1;
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        add(table,constraints);
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

        setVisible(true);
        pack();
        requestFocus();
    }

    public static void main(String[] args) {
        var out = new OutTable();
    }
}
