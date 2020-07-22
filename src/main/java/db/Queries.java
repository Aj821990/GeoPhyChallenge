package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static framework.Utilities.DBConnection.getConnection;

public class Queries {
    /**
     * This function is to create and work on queries.
     * Below given is just an example of select statement
     * @return
     */
    public static String getInfoFromDB(){
        ResultSet rs;
        Statement statement = getConnection();

        String userName=null;
        String id=null;
        try{
            rs=statement.executeQuery("select * from tableName where condition='true'");
            rs.next();
            userName = rs.getString("username");
            id = rs.getString("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
