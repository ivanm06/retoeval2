package me.ivanmart.plaiaundi.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection con;

    public static void start() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://159.223.201.211:3306/jaiskibel", "jaiskibel", "jaiskibeladmin");
        System.out.println("[INFO] Connectado a la base de datos.");
    }

    public static void close() throws SQLException{
        con.close();
    }
}
