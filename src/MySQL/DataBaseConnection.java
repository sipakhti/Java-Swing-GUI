package MySQL;

import java.sql.*;

/**
 * The class that handles all the communication to and from the Database.
 */
public class DataBaseConnection {

    public final String TRANSACTION = "Transaction";
    public final String RETURN = "Return";
    public final String UPDATE = "Update";

    private  String username, password;
    private  String connURL = "jdbc:sqlserver://SIPAKHTI\\POINTOFSALES;";
    private  Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Initialize the Database connection with the passed credentials
     *
     * @param username database username
     * @param password database username's password
     * @throws SQLException if either username or passwords is incorrect
     */
    public DataBaseConnection(String username,String password) throws SQLException {
        this.username = username;
        this.password = password;
        connectDatabase();
    }


    private void connectDatabase() throws SQLException {

            connection = DriverManager.getConnection(connURL,username,password);
            connection.setCatalog("POS_Data");
            if (connection != null)
                System.out.println(connection);

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

    }

    /**
     *
     * @param n row number that you want accessed
     * @return the String with format COLUMN1!!!COLUMN2 data
     *         its recommended to use <code>String.split("!!!")</code>
     * @throws SQLException is the entered Row number is out of bounds ar the requested resultSet is empty
     *                      this happens when the searched query didn't return any rows in the first place
     */
    public String rowData(int n) throws SQLException {

        resultSet.beforeFirst();
        for (int i = 0; i < n; i++) {
            resultSet.next();
            System.out.printf("RESULT SET: %d\n",resultSet.getRow());
        }
        return resultSet.getString(1) + "!!!" + resultSet.getString(2);
    }

    /**
     * NOTE: this method will return the data for the row which is after the current row at which the cursor is at
     *
     * @return the String with format COLUMN1!!!COLUMN2 data
     *          its recommended to use <code>String.split("!!!")</code>
     * @throws SQLException when the result set has no more rows
     */
    public String rowData() throws SQLException {
        resultSet.next();

        return resultSet.getString(1) + "!!!" + resultSet.getString(2) + "!!!" + resultSet.getString("barcode");
    }

    /**
     *
     * @return an Array of ProductNames that are used to update the <code>JComboBoxModel</code> for autoSuggestions
     * @throws SQLException if the resultSet doesn't have any rows
     */
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

    /**
     * Closes all open connections
     * this method internally calls three independent methods
     * 1. <code>ResultSet.close()</code>
     * 2. <code>Statement.close()</code>
     * 3. <code>Connection.close()</code>
     *
     * @throws SQLException is the connection is already closed
     */
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

//    public void previousRow() throws SQLException {
//        // call ResultSet.previous() Method to move cursor to previous row
//        resultSet.previous();
//
//    }

    /**
     *
     * @param str the productName or barcode that is to searched in the database
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public void searchQuery(String str) throws SQLException {
        // runs a search query and saves the output in resultSet
        String query = "SELECT product_name, price, barcode as barcode" +
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

    /**
     * Runs the SQL command:
     * <pre>
     *     INSERT INTO Transactions VALUES (GETDATE());
     * </pre>
     * It is the first Method that should be called when saving the Transaction to the Database as all the
     * rest of the methods used during saving transaction relies on this method to add the correct Transaction_ID
     *
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public void addTransaction() throws SQLException {
        String update = "INSERT INTO Transactions VALUES (GETDATE())";
        System.out.println(update);
        statement.executeUpdate(update);
        connection.commit();

    }

    /**
     * Runs the SQL command:
     * <pre>
     *     INSERT INTO Returns VALUES (GETDATE());
     * </pre>
     * It is the first Method that should be called when Returning as all the
     * rest of the methods used during saving Returns relies on this method to add the correct Return_ID
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
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

    /**
     * Runs the following SQL command:
     * <pre>
     *     SELECT MAX(Return_ID) FROM Returns
     * </pre>
     * @return the Return_ID in the form of a <code>Long</code> datatype
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public long getReturnID() throws SQLException {
        String query = "SELECT MAX(Return_ID) FROM Returns";
        ResultSet Returnid = statement.executeQuery(query);
        Returnid.next();

        return Long.parseLong(Returnid.getString(1));
    }

    /**
     * Runs the following SQL command:
     * <pre>
     *     SELECT MAX(Transaction_ID) FROM Transactions
     * </pre>
     * @return the Transaction_ID in the form of <code>Long</code> datatype
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public long getTransactionID() throws SQLException{
        String query = "SELECT MAX(transaction_ID) FROM Transactions";
        ResultSet transactionid = statement.executeQuery(query);
        transactionid.next();

        return Long.parseLong(transactionid.getString(1));
    }

    /**
     *
     *
     * @param productName the product name whose barcode is required
     * @return the Barocde of the passed product
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public String getBarcode(String productName) throws SQLException {
        String query = String.format("SELECT barcode FROM Product_Table WHERE CAST(product_name as varchar) = '%s'",
                productName);
        ResultSet barcodeSet = statement.executeQuery(query);
        barcodeSet.next();
        return barcodeSet.getString(1);
    }

    /**
     * saves the transaction items one by one in the Transaction_Details table
     *
     * @param transaction_id the transaction_ID of this transaction
     * @param barcode the barcode of the product
     * @param quantity the quantity of the product
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public void addTransactionDetails(long transaction_id, String barcode, int quantity) throws SQLException {
        String query = String.format("INSERT INTO Transaction_Details VALUES " +
                "(%d,'%s',%d)",transaction_id,barcode,quantity);
        statement.executeUpdate(query);
        System.out.println(query);
        connection.commit();


    }

    /**
     *
     * @param return_id the Return_ID of the returns
     * @param barcode the barcode of the product
     * @param quantity quantity of the the product that has been returned
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
    public void addReturnDetails(long return_id, String barcode, int quantity) throws SQLException {
        String query = String.format("INSERT INTO Return_Details VALUES " +
                "(%d,'%s',%d)",return_id,barcode,quantity);
        statement.executeUpdate(query);
        System.out.println(query);
        connection.commit();
    }

    /**
     * the SQL query itself decides whether to add a new Entry if the product isn't found in the product table
     * if the product is already present then simply increment the quantity by the passed amount.
     *
     * @param product full name of the product
     * @param barcode barcode of the product
     * @param price price of the product
     * @param quantity how many pieces of the said product are to be added to the inventory
     * @throws SQLException if a database access error occurs, this method is called on a closed Statement
     */
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
