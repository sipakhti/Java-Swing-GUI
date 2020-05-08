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
            username = "dbuser";
            password = "cherry476";
            connection = DriverManager.getConnection(connURL,username,password);
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

        String query = "SELECT first_name, last_name, id, email" +
                " " +
                "FROM students ";
        System.out.println(query);

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String[] rowData() throws SQLException {
        String[] strArr = new String[4];
        resultSet.next();
        System.out.println(resultSet.getRow());
        for (int i = 1; i < 5; i++) {
            strArr[i-1] = resultSet.getString(i);
        }

        return strArr;
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
        String query = "SELECT first_name, last_name, id, email" +
                " " +
                "FROM students " +
                "WHERE first_name LIKE '%" + str + "%'";
        System.out.println(query);
        resultSet = statement.executeQuery(query);

    }

    public String[][] completeData() throws SQLException {
        // returns the complete resultSet rows data in a 2D String Array
        resultSet.last();
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        resultSet.beforeFirst();
        String[][] Data = new String[rowCount][4];

        for (int i = 0; i < rowCount; i++) {
            Data[i] = rowData();
        }

        return Data;

    }
}
