package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data access object for a player
 */
public class PlayerDAO {
    private static boolean isTest = Database.isJunitTest();

    /**
     * Add player to database
     *
     * @param player player to be added
     */
    public void addPlayer(Player player) {
        String sql;

        if (isTest) {
            sql = "INSERT INTO playerTEST VALUES(?, ?)";
        } else {
            sql = "INSERT INTO player VALUES(?, ?)";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, player.getGamertag());
            statement.setString(2, player.getRank());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
