package framework.Utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    public static Statement getConnection() {
        String databaseURL = "jdbc:mysql://"; //databse url with host and port
        String user = "abcd"; //database valid username
        String password = "abcd"; //database valid password

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database....");
            //connecting to database
            connection = java.sql.DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database....");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
