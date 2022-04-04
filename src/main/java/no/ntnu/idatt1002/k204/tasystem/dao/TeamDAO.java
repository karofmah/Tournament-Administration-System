package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeamDAO {
    private static boolean isTest = Database.isJunitTest();

    /**
     * Add team to database.
     *
     * @param players  players in team
     * @param teamName name of team
     */

    public void addTeam(ArrayList<Player> players, String teamName) {
        String sql;
        if (isTest) {
            sql = "INSERT INTO teamTEST VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO team VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, teamName);

            statement.setString(2, players.get(0).getGamertag());
            statement.setString(3, players.get(0).getRank());
            statement.setString(4, players.get(1).getGamertag());
            statement.setString(5, players.get(1).getRank());
            statement.setString(6, players.get(2).getGamertag());
            statement.setString(7, players.get(2).getRank());
            statement.setString(8, players.get(3).getGamertag());
            statement.setString(9, players.get(3).getRank());
            statement.setString(10, players.get(4).getGamertag());
            statement.setString(11, players.get(4).getRank());


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
     * Get team from database
     *
     * @param register team register
     */
    public void getTeam(TeamRegister register) {
        String sql;

        if (isTest) {
            sql = "SELECT * FROM teamTest";
        } else {
            sql = "SELECT * FROM team";
        }

        ResultSet res = null;

        try {
            res = Database.getConnection().prepareStatement(sql).executeQuery();
            while (res.next()) {
                Team team = new Team(res.getString("name"), res.getString("p1name"),
                        res.getString("p1rank"), res.getString("p2name"), res.getString("p2rank"),
                        res.getString("p3name"), res.getString("p3rank"), res.getString("p4name"),
                        res.getString("p4rank"), res.getString("p5name"), res.getString("p5rank"));
                register.addTeam(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets teams.
     *
     * @return team register of teams
     */
    public TeamRegister getTeams() {
        Team team;
        TeamRegister teamRegister = new TeamRegister();
        String sql;
        ResultSet result = null;

        if (isTest) {
            sql = "SELECT name FROM teamTEST";
        } else {
            sql = "SELECT name FROM team";
        }

        try {
            result = Database.getConnection().prepareStatement(sql).executeQuery();
            while (result.next()) {
                team = new Team(result.getString("name"));
                teamRegister.addTeam(team);
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
        return teamRegister;
    }

    public void deleteTeam(String teamName) {
        String sql = "DELETE FROM tournament_team WHERE teamName = ?";
        String sql1 = "DELETE FROM team WHERE name = ?";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, teamName);
            statement.executeUpdate();
            statement = Database.getConnection().prepareStatement(sql1);
            statement.setString(1, teamName);
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

    public void addTeamAndPlayers(String teamName, String gamerTag) {
        String sql;

        if (isTest) {
            sql = "INSERT INTO team_playerTEST VALUES(?, ?)";
        } else {
            sql = "INSERT INTO team_player VALUES(?, ?)";
        }

        PreparedStatement statement = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, teamName);
            statement.setString(2, gamerTag);
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
