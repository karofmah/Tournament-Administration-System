package no.ntnu.idatt1002.k204.tasystem.dao;

import java.sql.*;

/**
 * Class used to create database connection
 */
public class Database {

    private static final String DB_NAME = "g_idatt1002_x";
    private static final String DB_USERNAME = "g_idatt1002_x";
    private static final String DB_PW = "ApQiiNHj";
    private static final String DB_URL = "jdbc:mysql://mysql-ait.stud.idi.ntnu.no/"
            + DB_NAME + "?user=" + DB_USERNAME + "&password=" + DB_PW;

    private static Database instance;
    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e ) {
            e.printStackTrace();
        }

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            return instance;
        } else {
            return instance;
        }
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

    /**
     * Close statements and result sets
     *
     * @param preparedStatement the prepared statement to be closed
     * @param resultSet         the result set to be closed
     */
    static void close(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check if incoming connection is JUnit test
     *
     * @return {@true if it's a JUnit test}
     */
    public static boolean isJunitTest() {
        StackTraceElement[] stackTraces = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTraces) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
}