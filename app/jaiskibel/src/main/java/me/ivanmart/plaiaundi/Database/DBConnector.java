package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Interfaces.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector implements Database {
    public static Connection con;

    @Override
    public void start() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://159.223.201.211:3306/jaiskibel", "jaiskibel", "ivyJpqMAB(OF@nUu");
        System.out.println("[INFO] Connectado a la base de datos.");
    }

    @Override
    public void close() throws SQLException{
        con.close();
    }
}
