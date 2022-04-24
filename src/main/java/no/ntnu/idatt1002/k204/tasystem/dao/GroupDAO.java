package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * DAO for group
 */

public class GroupDAO {
    private static boolean isTest = Database.isJunitTest();

    /**
     * Add group.
     *
     * @param groupName    the group name
     */
    public void addGroup(String groupName, String team1, String team2, String team3, int tournamentId) {

        String sql;

        if (isTest) {
            sql = "INSERT INTO TESTgrp VALUES(?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO grp VALUES(?, ?, ?, ?, ?, ?)";
        }

        if (!groupExists(groupName, tournamentId)) {

            PreparedStatement statement = null;

            try {
                statement = Database.getConnection().prepareStatement(sql);
                statement.setNull(1, Types.NULL);
                statement.setString(2, groupName);
                statement.setString(3, team1);
                statement.setString(4, team2);
                statement.setString(5, team3);
                statement.setInt(6, tournamentId);
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
        } else {
            updateGroup(groupName, team1, team2, team3, tournamentId);
        }
    }

    private void updateGroup(String groupName, String team1, String team2, String team3, int tournamnetId) {
        String sql;

        if (isTest) {
            sql = "UPDATE TESTgrp SET team1= ?, team2= ?, team3= ? " +
                    "WHERE name= ? AND tournament_id = ?";
        } else {
            sql = "UPDATE grp SET team1= ?, team2= ?, team3= ?" +
                    "WHERE name= ? AND tournament_id = ?";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, team1);
            statement.setString(2, team2);
            statement.setString(3, team3);
            statement.setString(4, groupName);
            statement.setInt(5, tournamnetId);

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

    private boolean groupExists(String groupName, int tournamentID) {
        String sql;

        if (isTest) {
            sql = "SELECT name from grpTEST WHERE name = ? AND tournament_id = ?";
        } else {
            sql = "SELECT name from grp WHERE name = ? AND tournament_id = ?";
        }

        PreparedStatement statement = null;
        ResultSet result = null;

        Group group = null;
        boolean exists = true;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, groupName);
            statement.setInt(2, tournamentID);
            result = statement.executeQuery();

            while (result.next()) {
                group = new Group(result.getString("name"));
            }
            if (group == null) {
                exists = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }

}
