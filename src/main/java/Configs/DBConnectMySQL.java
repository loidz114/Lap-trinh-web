package Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {

    private static String USERNAME = "root";
    private static String PASSWORD = "loidz11042004";
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/bt1";

    public static Connection getDatabaseConnection() throws Exception {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main (String[] args) {

        try {
            new DBConnectMySQL();
            System.out.print(DBConnectMySQL.getDatabaseConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

