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
     * <p>
     * If group does not exist, add the group. Otherwise update current group.
     *
     * @param groupName    the group name
     * @param team1        team 1
     * @param team2        team 2
     * @param team3        team 3
     * @param tournamentId the tournament id
     */
    public void addGroup(String groupName, String team1, String team2, String team3, int team1Point, int team2Point, int team3Point, int tournamentId) {

        String sql1;
        String sql2;

        if (isTest) {
            sql1 = "INSERT INTO TESTgrp VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            sql2 = "UPDATE tournament_team SET points = ? WHERE tournament_id = ? AND teamName = ?";
        } else {
            sql1 = "INSERT INTO grp VALUES(?, ?, ?, ?, ?, ?, ?, ? , ?)";
            sql2 = "UPDATE tournament_team SET points = ? WHERE tournament_id = ? AND teamName = ?";
        }

        if (getGroup(groupName, tournamentId) == null) {

            PreparedStatement statement = null;

            try {
                statement = Database.getConnection().prepareStatement(sql1);
                statement.setNull(1, Types.NULL);
                statement.setString(2, groupName);
                statement.setString(3, team1);
                statement.setString(4, team2);
                statement.setString(5, team3);
                statement.setString(6, String.valueOf(team1Point));
                statement.setString(7, String.valueOf(team2Point));
                statement.setString(8, String.valueOf(team3Point));
                statement.setString(9, String.valueOf(tournamentId));
                statement.executeUpdate();

                //UPDATE tournament_team
                statement = Database.getConnection().prepareStatement(sql2);
                statement.setString(1, String.valueOf(team1Point));
                statement.setInt(2, tournamentId);
                statement.setString(3, team1);
                statement.executeUpdate();


                statement = Database.getConnection().prepareStatement(sql2);
                statement.setString(1, String.valueOf(team2Point));
                statement.setInt(2, tournamentId);
                statement.setString(3, team2);
                statement.executeUpdate();

                statement = Database.getConnection().prepareStatement(sql2);
                statement.setString(1, String.valueOf(team3Point));
                statement.setInt(2, tournamentId);
                statement.setString(3, team3);
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
            updateGroup(groupName, team1, team2, team3, team1Point, team2Point, team3Point, tournamentId);
        }
    }

    /**
     * Update group with new teams.
     *
     * @param groupName
     * @param team1
     * @param team2
     * @param team3
     * @param tournamentId
     */
    private void updateGroup(String groupName, String team1, String team2, String team3, int team1Point, int team2Point, int team3Point, int tournamentId) {
        String sql1;
        String sql2;

        if (isTest) {
            sql1 = "UPDATE TESTgrp SET team1= ?, team2= ?, team3= ?, team1Point = ?, team2Point =?, team3Point= ?" +
                    "WHERE name= ? AND tournament_id = ?";
            sql2 = "UPDATE TESTtournament_team SET points = ? WHERE tournament_id = ? AND teamName = ?";
        } else {
            sql1 = "UPDATE grp SET team1= ?, team2= ?, team3= ?, team1Point = ?, team2Point =?, team3Point= ?" +
                    "WHERE name= ? AND tournament_id = ?";
            sql2 = "UPDATE tournament_team SET points = ? WHERE tournament_id = ? AND teamName = ?";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql1);

            statement.setString(1, team1);
            statement.setString(2, team2);
            statement.setString(3, team3);
            statement.setString(4, String.valueOf(team1Point));
            statement.setString(5, String.valueOf(team2Point));
            statement.setString(6, String.valueOf(team3Point));
            statement.setString(7, groupName);
            statement.setInt(8, tournamentId);

            statement.executeUpdate();

            statement = Database.getConnection().prepareStatement(sql2);
            statement.setString(1, String.valueOf(team1Point));
            statement.setInt(2, tournamentId);
            statement.setString(3, team1);
            statement.executeUpdate();


            statement = Database.getConnection().prepareStatement(sql2);
            statement.setString(1, String.valueOf(team2Point));
            statement.setInt(2, tournamentId);
            statement.setString(3, team2);
            statement.executeUpdate();

            statement = Database.getConnection().prepareStatement(sql2);
            statement.setString(1, String.valueOf(team3Point));
            statement.setInt(2, tournamentId);
            statement.setString(3, team3);
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
     * Get group given group name and tournament id.
     *
     * @param groupName    the group name
     * @param tournamentId the tournament id
     * @return the group
     */
    public Group getGroup(String groupName, int tournamentId) {
        String sql;

        if (isTest) {
            sql = "SELECT * from TESTgrp WHERE name = ? AND tournament_id = ?";
        } else {
            sql = "SELECT * from grp WHERE name = ? AND tournament_id = ?";
        }

        PreparedStatement statement;
        ResultSet result = null;

        Group group = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, groupName);
            statement.setInt(2, tournamentId);
            result = statement.executeQuery();

            while (result.next()) {
                group = new Group(result.getString("name"));
                group.addTeam(result.getString("team1"), result.getString("team1Point"));
                group.addTeam(result.getString("team2"), result.getString("team2Point"));
                group.addTeam(result.getString("team3"), result.getString("team3Point"));
            }
            if (group == null){
                return null;
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
        return group;
    }
}
