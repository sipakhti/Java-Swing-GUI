package MySQL;

import java.sql.*;

public class DataBaseConnection {

    public final String TRANSACTION = "Transaction";
    public final String RETURN = "Return";
    public final String UPDATE = "Update";

    private  String username, password;
    private  String connURL = "jdbc:sqlserver://SIPAKHTI\\POINTOFSALES;";
    private  Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DataBaseConnection(String username,String password) throws SQLException {
        this.username = username;
        this.password = password;
        connectDatabase();
    }

    public void connectDatabase() throws SQLException {

//            username = "dbadmin";
//            password = "cherry476";
            connection = DriverManager.getConnection(connURL,username,password);
            connection.setCatalog("POS_Data");
            if (connection != null)
                System.out.println(connection);

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    }

    public String rowData(int n) throws SQLException {

        resultSet.beforeFirst();
        for (int i = 0; i < n; i++) {
            resultSet.next();
            System.out.printf("RESULT SET: %d\n",resultSet.getRow());
        }
        return resultSet.getString(1) + "!!!" + resultSet.getString(2);
    }

    public String rowData() throws SQLException {
        resultSet.next();

        return resultSet.getString(1) + "!!!" + resultSet.getString(2);
    }

    public String[] productNames() throws SQLException{
        resultSet.last();
        int rows = resultSet.getRow();
        resultSet.beforeFirst();
        String[] prodcutNames = new String[rows];
        for (int i = 0; i < rows; i++) {
            resultSet.next();
            prodcutNames[i] = resultSet.getString(1);
        }
        return prodcutNames;
    }

    public void close() throws SQLException {
        try{
            resultSet.close();
        } catch (NullPointerException e) {

        }
        finally {
            statement.close();
            connection.close();
            if (statement.isClosed() && connection.isClosed()) System.out.println("Connection Closed!");


        }
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
                "WHERE product_name LIKE '%" + str + "%' " +
                "OR barcode LIKE '%" + str + "%'";
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
        System.out.println(update);
        statement.executeUpdate(update);
        connection.commit();

    }

    public void addReturn() throws SQLException {
        String update = "INSERT INTO Returns VALUES (GETDATE())";
        System.out.println(update);
        statement.executeUpdate(update);
        connection.commit();
    }

//    public void addUpdate() throws SQLException {
//        String update = "INSERT INTO Returns VALUES (GETDATE())";
//        System.out.println(update);
//        statement.executeUpdate(update);
//        connection.commit();
//    }

    public long getReturnID() throws SQLException {
        String query = "SELECT MAX(Return_ID) FROM Returns";
        ResultSet transactionid = statement.executeQuery(query);
        transactionid.next();

        return Long.parseLong(transactionid.getString(1));
    }

    public long getTransactionID() throws SQLException{
        String query = "SELECT MAX(transaction_ID) FROM Transactions";
        ResultSet transactionid = statement.executeQuery(query);
        transactionid.next();

        return Long.parseLong(transactionid.getString(1));
    }

    public String getBarcode(String productName) throws SQLException {
        String query = String.format("SELECT barcode FROM Product_Table WHERE CAST(product_name as varchar) = '%s'",
                productName);
        ResultSet barcodeSet = statement.executeQuery(query);
        barcodeSet.next();
        return barcodeSet.getString(1);
    }

    public void addTransactionDetails(long transaction_id, String barcode, int quantity) throws SQLException {
        String query = String.format("INSERT INTO Transaction_Details VALUES " +
                "(%d,'%s',%d)",transaction_id,barcode,quantity);
        statement.executeUpdate(query);
        System.out.println(query);
        connection.commit();


    }

    public void addReturnDetails(long return_id, String barcode, int quantity) throws SQLException {
        String query = String.format("INSERT INTO Return_Details VALUES " +
                "(%d,'%s',%d)",return_id,barcode,quantity);
        statement.executeUpdate(query);
        System.out.println(query);
        connection.commit();
    }

    public void updateInventory(String product,String barcode,int price,int quantity) throws SQLException {
        String query = String.format("BEGIN\n" +
                "\tDECLARE @count INT;\n" +
                "\n" +
                "\tSELECT \n" +
                "\t\t@count = COUNT(barcode)\n" +
                "\tFROM\n" +
                "\t\tProduct_Table\n" +
                "\tWHERE barcode = '%s';\n" + // BARCODE
                "\n" +
                "IF @count = 0\n" +
                "BEGIN\n" +
                "\tINSERT INTO Product_Table VALUES ('%s','%s',%d);\n" + // BARCODE , PRODUCT NAME , PRICE
                "\tINSERT INTO Inventory VALUES ('%s', %d);\n" + // BARCODE , QUANTITY
                "END\n" +
                "ELSE\n" +
                "\tUPDATE Inventory\n" +
                "\tSET quantity = (SELECT quantity from Inventory where barcode = '%s') + %d\n" + // barcode, quantity
                "\tWHERE barcode = '%s'\n" +
                "END\n",
                barcode,
                barcode,product,price,
                barcode,quantity,
                barcode, quantity,
                barcode);

        statement.executeUpdate(query);
        System.out.println(query);
        connection.commit();
    }
}
