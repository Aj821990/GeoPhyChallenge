package framework.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static framework.base.TestBase.log;

/**
 * This class is for connecting to DB
 */

public class DBConnection {

    private DBConnection() {
    }

    public static Statement getConnection() {
        String databaseURL = "jdbc:mysql://"; //databse url with host and port
        String user = "abcd"; //database valid username
        String password = "abcd"; //database valid password

        Statement statement = null;
        try (Connection connection = java.sql.DriverManager.getConnection(databaseURL, user, password)){
            log.info("Connected to the Database....");
            statement = connection.createStatement();
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}