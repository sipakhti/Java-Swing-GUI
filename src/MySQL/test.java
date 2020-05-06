package MySQL;

import java.sql.*;

public class test {

    private static String username, password;
    private static String connURL = "jdbc:sqlserver://SIPAKHTI\\POINTOFSALES;";
    private static Connection connection;
    public static void main(String[] args) throws SQLException {

        try {
            username = "dbuser";
            password = "cherry476";
            connection = DriverManager.getConnection(connURL,username,password);
            if (connection != null)
                System.out.println(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "SELECT students.first_name as a, teacher.first_name as b " +
                "FROM students full join teacher ON CAST(students.first_name as varchar(max)) = CAST(teacher.first_name as varchar(max))";
        System.out.println(query);
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Students       Teachers");



        try {

            while (resultSet.next()) {
                System.out.print(resultSet.getString("a") + " ".repeat(8 + (7 - resultSet.getString("a").length())));
                System.out.println(resultSet.getString("b"));

            }

        }catch(SQLException | NullPointerException e){
            resultSet.beforeFirst();
        }

        System.out.println("change");
        while (resultSet.next()){
            System.out.print(resultSet.getString("a") + " ".repeat(8 + (7 - resultSet.getString("a").length())));
            System.out.println(resultSet.getString("b"));
        }

        statement.close();
        resultSet.close();
        connection.close();
    }
}
