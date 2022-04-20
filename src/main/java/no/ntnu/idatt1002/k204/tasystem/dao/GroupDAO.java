package no.ntnu.idatt1002.k204.tasystem.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO for group
 */
public class GroupDAO {
    private static boolean isTest = Database.isJunitTest();

    /**
     * Add group.
     *
     * @param groupName    the group name
     * @param tournamentId the tournament id
     */
    public void addGroup(String groupName, int tournamentId) {
        //TODO

        //FIXME Fix duplicate primary key error when adding existing group
        // - Check if group exists
        // - If group exists, update current, otherwise create new
        String sql;

        if (isTest) {
            sql = "INSERT INTO grpTEST VALUES(?, ?)";
        } else {
            sql = "INSERT INTO grp VALUES(?, ?)";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, groupName);
            statement.setInt(2, tournamentId);

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

    /**
     * Add team to group.
     *
     * @param groupName the group name
     * @param teamName  the team name
     */
    public void addTeamToGroup(String groupName, String teamName) {
        String sql;

        if (isTest) {
            sql = "INSERT INTO team_grpTEST VALUES(?, ?)";
        } else {
            sql = "INSERT INTO team_grp VALUES(?, ?)";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, groupName);
            statement.setString(2, teamName);

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
