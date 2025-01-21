package me.ivanmart.plaiaundi.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    public static Connection con;

    public static void start() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestor", "root", "password");
        System.out.println("[INFO] Connectado a la base de datos.");
    }

    public static void close() throws SQLException{
        con.close();
    }
}
