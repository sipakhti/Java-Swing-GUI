package MySQL;

import java.sql.*;

public class DataBaseConnection {

    private  String username, password;
    private  String connURL = "jdbc:sqlserver://SIPAKHTI\\POINTOFSALES;";
    private  Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DataBaseConnection(){}

    public void connectDatabase() throws SQLException {

        try {
            username = "sa";
            password = "cherry476";
            connection = DriverManager.getConnection(connURL,username,password);
            connection.setCatalog("POS_Data");
            if (connection != null)
                System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String rowData() throws SQLException {

        resultSet.next();

        return resultSet.getString(1) + "!!!" + resultSet.getString(2);
    }

    public void close() throws SQLException {
        statement.close();
        resultSet.close();
        connection.close();
        if (statement.isClosed() && resultSet.isClosed() && connection.isClosed()) System.out.println("Connection " +
                "CLosed");
    }

    public void previousRow() throws SQLException {
        // call ResultSet.previous() Method to move cursor to previous row
        resultSet.previous();

    }

    public void searchQuery(String str) throws SQLException {
        // runs a search query and saves the output in resultSet
        String query = "SELECT product_name, price" +
                " " +
                "FROM Product_Table " +
                "WHERE product_name LIKE '%" + str + "%'";
        System.out.println(query);
        resultSet = statement.executeQuery(query);

    }

//    public String[][] completeData() throws SQLException {
//        // returns the complete resultSet rows data in a 2D String Array
//        resultSet.last();
//        int rowCount = resultSet.getRow();
//        System.out.println(rowCount);
//        resultSet.beforeFirst();
//        String[][] Data = new String[rowCount][4];
//
//        for (int i = 0; i < rowCount; i++) {
//            Data[i] = rowData();
//        }
//
//        return Data;
//
//    }

    public void addTransaction() throws SQLException {
        String update = "INSERT INTO Transactions VALUES (GETDATE())";
        statement.executeQuery(update);
    }

    public long getTransactionID() throws SQLException{
        String query = "SELECT MAX(transaction_ID) FROM Transactions";
        ResultSet transactionid = statement.executeQuery(query);
        transactionid.next();

        return Long.parseLong(transactionid.getString(1));
    }

    public String getBarcode(String productName) throws SQLException {
        String query = String.format("SELECT barcode FROM Product_Table WHERE product_name = '%s'",productName);
        ResultSet barcodeSet = statement.executeQuery(query);
        barcodeSet.next();
        return barcodeSet.getString(1);
    }

    public void addTransactionDetails(long transaction_id, String barcode, int quantity) throws SQLException {
        String query = String.format("INSERT INTO Transaction_Details VALUES " +
                "(%d,'%s',%d",transaction_id,barcode,quantity);

        statement.executeQuery(query);
    }
}
