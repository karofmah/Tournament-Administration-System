package no.ntnu.idatt1002.k204.tasystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for administrator user
 *
 * Currently, no feature to register an administrator
 * Provides the possibility to get an existing admin.
 */
public class AdministratorDAO {
    private static boolean isTest = Database.isJunitTest();
    /**
     * Try to get administrator with given name and password
     *
     * @param username the username
     * @param password the password
     * @return {@false if administrator doesnt exist true otherwise}
     */
    public boolean getAdmin(String username, String password) {
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exist;
        try {
            connection = Database.getConnection();
            if (isTest) {
                preparedStatement = connection.prepareStatement(
                        "SELECT * FROM admininistratorTEST WHERE username = ? AND paswd = MD5(?)");
            } else {
                preparedStatement = connection.prepareStatement(
                        "SELECT * FROM admininistrator WHERE username = ? AND paswd = MD5(?)");
            }
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            exist = resultSet.next();

            if (!exist) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(preparedStatement, resultSet);
        }
        return true;
    }
}
