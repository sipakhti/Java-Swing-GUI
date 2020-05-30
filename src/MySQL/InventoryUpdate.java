package MySQL;

import MySQL.Interfaces.ActionPerformed;
import MySQL.Interfaces.AutoCompleter;
import MySQL.Interfaces.CommitListener;
import MySQL.POSsupport.ColoringConstants;
import MySQL.POSsupport.InputPanel;
import MySQL.POSsupport.Table;
import MySQL.POSsupport.TableRow;
import Swing1.StringListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class InventoryUpdate extends JFrame {

    private final GridBagLayout LAYOUT = new GridBagLayout();
    private final GridBagConstraints CONSTRAINTS = new GridBagConstraints();

    private Box box;
    private JScrollPane scrollPane;
    private InputPanel panel;
    private Table table;
    private CommitPanel commit;
    private TableRow header;
    private DataBaseConnection dataBaseConnection;

    public InventoryUpdate() throws SQLException {
        super("Inventory Update");
        InitializeFields();

        setLayout(LAYOUT);

        addInputPanel();

        addTableHeader();

        addScrollPane();

        addCommitButton();


        inputPanelListeners();

        commitPanelListeners();

        tableListeners();


        MainPanelProperties();
    }

    private void tableListeners() {
        table.setListener(new ActionPerformed() {
            @Override
            public void quantityUpdated() {

            }

            @Override
            public void rowUpdated() {
                table.addRow();
                table.revalidate();
                box.revalidate();
                scrollPane.revalidate();
                table.requestFocus();
                SwingUtilities.invokeLater(() -> {
                    JScrollBar scroll = scrollPane.getVerticalScrollBar();
                    scroll.setValue(scroll.getMaximum());
                });
                System.out.println("TEST");
            }
        });
    }

    private void commitPanelListeners() {
        commit.setListener(new CommitListener() {
            @Override
            public void returnListener() {
                System.out.println("RETURN");
                panel.reset();
                commitReturn();
                table.clear();
            }

            @Override
            public void updateListener() {
                System.out.println("UPDATE");
                panel.reset();
                commitUpdate();
                table.clear();
            }

            @Override
            public void purgeListener(){
                panel.reset();
                table.clear();
            }
        });
    }

    private void inputPanelListeners() {
        panel.setStringListener(new StringListener() {
            @Override
            public void searchQuery(String text) {
                try {
                    dataBaseConnection.searchQuery(text);
                    table.updateRow(dataBaseConnection.rowData());
                    box.revalidate();
                    box.repaint();
                    scrollPane.revalidate();

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

        panel.setListener(new ActionPerformed() {
            @Override
            public void quantityUpdated() {
                table.removeRow();
            }

            @Override
            public void rowUpdated() {

            }
        });

        panel.setCompleter(new AutoCompleter() {
            @Override
            public void autoCompleteQuery(String subString) {
                try {
                    dataBaseConnection.searchQuery(subString);
                    panel.populateSuggestions(dataBaseConnection.productNames());
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void MainPanelProperties() {
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        requestFocus();
        getContentPane().setBackground(new Color(43, 43, 43));
        pack();
        System.out.println(getSize());
        setMinimumSize(new Dimension(1328,717));
        scrollPane.getViewport().setViewSize(new Dimension(1290,525));
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    dataBaseConnection.close();
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
    }

    private void InitializeFields() throws SQLException {
        dataBaseConnection = new DataBaseConnection("dbadmin","cherry476");
        panel = new InputPanel();
        panel.setBackground(ColoringConstants.backgroud);
        table = new Table(true);
        commit = new CommitPanel();
        header = new TableRow(false,true);
        box = Box.createVerticalBox();
        box.add(table);
        scrollPane = new JScrollPane(box,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void addInputPanel() {
        CONSTRAINTS.gridx = 0;
        CONSTRAINTS.gridy = 0;
        CONSTRAINTS.anchor = GridBagConstraints.NORTH;
        CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        CONSTRAINTS.insets = new Insets(0,10,5,10);
        CONSTRAINTS.weightx = 1;
        CONSTRAINTS.weighty = 1;
        add(panel,CONSTRAINTS);
    }

    private void addTableHeader() {
        CONSTRAINTS.anchor = GridBagConstraints.SOUTH;
        CONSTRAINTS.fill = GridBagConstraints.HORIZONTAL;
        CONSTRAINTS.insets = new Insets(0,10,0,10);
        CONSTRAINTS.gridy++;
        add(header,CONSTRAINTS);
    }

    private void addScrollPane() {
        CONSTRAINTS.insets = new Insets(0,10,5,10);
        CONSTRAINTS.anchor = GridBagConstraints.NORTH;
        CONSTRAINTS.gridy++;
        add(scrollPane,CONSTRAINTS);
        scrollPane.setBorder(new LineBorder(ColoringConstants.border));
    }

    private void addCommitButton() {
        CONSTRAINTS.gridy++;
        CONSTRAINTS.fill = GridBagConstraints.BOTH;
        add(commit,CONSTRAINTS);
//        commit.setBorder(new LineBorder(ColoringConstants.border));
//        commit.setBackground(ColoringConstants.commitBackground);
//        commit.setForeground(ColoringConstants.commitForeground);
    }

    public static void main(String[] args) throws SQLException {

        UIManager.put("InternalFrame.activeTitleBackground",new ColorUIResource(ColoringConstants.backgroud));
        UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(ColoringConstants.foreground));
        var test = new InventoryUpdate();
    }

    private void commitReturn(){
        String[][] tableData = table.tableSaveData();
        String barcode = "";
        try {
            dataBaseConnection.addReturn();
            long ReturnId = dataBaseConnection.getReturnID();
            for (String[] tableDatum : tableData) {
                barcode = dataBaseConnection.getBarcode(tableDatum[0]);
                dataBaseConnection.addReturnDetails(ReturnId, barcode, Integer.parseInt(tableDatum[1]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void commitUpdate() {
        String[][] tableData = table.tableUpdateData();

        try {
            for (String[] tableDatum : tableData) {
                dataBaseConnection.updateInventory(tableDatum[0], tableDatum[3], // Product name, barcode
                        Integer.parseInt(tableDatum[1].strip()), // price
                        Integer.parseInt(tableDatum[2].strip())); // quantity
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}

