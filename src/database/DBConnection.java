package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/XEPDB1";

    private static final String USER = "library";

    private static final String PASSWORD = "library123";

    public static Connection getConnection() {

        try {

            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Database Connection Failed!");

            e.printStackTrace();

            return null;
        }
    }
}