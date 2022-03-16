package no.ntnu.idatt1002.k204.tasystem.dao;

import java.sql.*;

/**
 * Class used to create database connection
 */
public class Database{

    private static final String DB_NAME = "g_idatt1002_x";
    private static final String DB_USERNAME = "g_idatt1002_x";
    private static final String DB_PW = "ApQiiNHj";
    private static final String DB_URL = "jdbc:mysql://mysql-ait.stud.idi.ntnu.no/"
            + DB_NAME + "?user=" + DB_USERNAME + "&password=" + DB_PW;

    private static Connection connection;

    public Database() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
    }

    /**
     * Get connection from the database instance.
     *
     * Same connection for database objects
     *
     * @return a connection of the database instance
     */
    public static Connection getConnection() {
        return connection;
    }

}