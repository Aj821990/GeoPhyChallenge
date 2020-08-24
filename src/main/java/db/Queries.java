package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static framework.utilities.DBConnection.getConnection;

public class Queries {

    private Queries() {
    }

    /**
     * This function is to create and work on queries.
     * Below given is just an example of select statement
     * @return
     */
    public static String getInfoFromDB(){
        Statement statement = getConnection();

        String userName=null;
        try(ResultSet rs=statement.executeQuery("select * from tableName where condition='true'");)
        {
            rs.next();
            userName = rs.getString("username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
