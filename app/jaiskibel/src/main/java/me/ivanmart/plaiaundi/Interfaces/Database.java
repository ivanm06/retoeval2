package me.ivanmart.plaiaundi.Interfaces;

import java.sql.SQLException;

public interface Database {
    void start() throws SQLException;
    void close() throws SQLException;
}
