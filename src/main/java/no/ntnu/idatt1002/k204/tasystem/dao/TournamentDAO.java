package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for tournament
 */
public class TournamentDAO {
    private static boolean isTest = Database.isJunitTest();

    /**
     * Add tournament to database.
     *
     * @param name        the name
     * @param status      the status
     * @param requirement the requirement
     * @param date        the date
     * @param time        the time
     */
    public void addTournament(int id, String name, String status, String requirement, String date, String time) {
        String sql;
        if (isTest) {
            sql = "INSERT INTO tournamentTEST VALUES(? , ? , ?, ?, ?, ?, null)";
        } else {
            sql = "INSERT INTO tournament VALUES(? , ? , ?, ?, ?, ?, null)";
        }

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, status);
            statement.setString(4, requirement);
            statement.setString(5, date);
            statement.setString(6, time);
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
     * Get tournament from database
     *
     * @param register the register
     */
    public void getTournament(TournamentRegister register) {
        //Reset count tournaments since we update each time new tournament is added.
        Tournament.setCountTournaments(0);
        String sql;

        if (isTest) {
            sql = "SELECT * FROM tournamentTEST";
        } else {
            sql = "SELECT * FROM tournament";
        }

        ResultSet result = null;

        try {
            result = Database.getConnection().prepareStatement(sql).executeQuery();
            while (result.next()) {
                Tournament tournament = new Tournament(result.getString("name"), result.getString("status"),result.getString("requirement"),
                        result.getString("start_date"), result.getString("start_time"));
                register.addTournament(tournament);
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
    }

    /**
     * Add tournament with participating teams to database
     *
     *
     * @param tournamentID
     * @param teamName
     */
    public void addTournamentAndTeams(int tournamentID, String teamName){
        String sql = "INSERT INTO tournament_team VALUES(? , ?)";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, tournamentID);
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

    /**
     * Get teams that are currently participating in a tournament given the tournament id from database
     *
     * @param tournamentID
     * @return
     */
    public TeamRegister getTeamsGivenTournamentId(int tournamentID) {
        String sql = "SELECT teamName from tournament_team WHERE tournament_id = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        TeamRegister teamRegister = new TeamRegister();
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1,tournamentID);

            result = statement.executeQuery();
            while (result.next()) {
                 Team team = new Team(result.getString("teamName"));
                 teamRegister.addTeam(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(statement, result);
        }
        return teamRegister;
    }
}
