import java.sql.*;

public class MysqlCon {
    private static Connection con;


    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + constants.databaseName, "root", "");

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
}