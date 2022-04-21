package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Group;

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
    public void addGroup(String groupName, int tournamentId, String teamName) {
        //TODO

        //FIXME Add groups for different tournaments

        String sql;

        if (!groupExists(groupName)) {
            if (isTest) {
                sql = "INSERT INTO TESTgrp VALUES(?)";
            } else {
                sql = "INSERT INTO grp VALUES(?)";
            }

            PreparedStatement statement = null;

            try {
                statement = Database.getConnection().prepareStatement(sql);
                statement.setString(1, groupName);
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
            addTeamToGroup(groupName, tournamentId, teamName);
        } else {
            updateGroup(groupName, tournamentId,teamName);
        }
    }

    /**
     * Add team to group.
     *
     * @param groupName the group name
     * @param teamName  the team name
     */
    private void addTeamToGroup(String groupName, int tournamentId, String teamName) {
        String sql;

        if (isTest) {
            sql = "INSERT INTO TESTtournament_grp VALUES(?, ?, ?);";
        } else {
            sql = "INSERT INTO tournament_grp VALUES(?, ?, ?)";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, groupName);
            statement.setInt(2, tournamentId);
            statement.setString(3, teamName);

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

    private void updateGroup(String groupName, int tournamentId, String teamName) {
        String sql;

        if (isTest) {
            sql = "UPDATE TESTtournament_grp SET teamName= ? WHERE groupName= ? AND tournament_id= ?";
        } else {
            sql = "UPDATE tournament_grp SET teamName= ? WHERE groupName= ? AND tournament_id= ?";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);

            statement.setString(1, teamName);
            statement.setString(2, groupName);
            statement.setInt(3, tournamentId);

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

    private boolean groupExists(String groupName) {
        String sql;

        if (isTest) {
            sql = "SELECT name from grpTEST WHERE name = ?";
        } else {
            sql = "SELECT name from grp WHERE name = ?";
        }

        PreparedStatement statement = null;
        ResultSet result = null;

        Group group = null;
        boolean exists = true;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, groupName);
            result = statement.executeQuery();

            while (result.next()) {
                group = new Group(result.getString("name"));
            }
            if (group == null){
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
