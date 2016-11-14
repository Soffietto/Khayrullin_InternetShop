package ru.kpfu.itis.azat_khayrullin.database;

import ru.kpfu.itis.azat_khayrullin.exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by soffietto on 12.11.16.
 */
public class SQLDatabase {
    private final static String DRIVER = "org.postgresql.Driver";
    private final static String CONNECTION_URI = "jdbc:postgresql://localhost:5432/shop";
    private final static String LOGIN = "soffietto";
    private final static String PASSWORD = "6833";
    private static Connection connection;

    public static Connection getConnection() throws DBException {
        if(connection == null){
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(CONNECTION_URI, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new DBException("Can't find Database driver.");
            } catch (SQLException ex) {
                throw new DBException("Can't connect to Database (" + ex.getErrorCode() + ": " + ex.getMessage() + ").");
            }
        }
        return connection;

    }
}
