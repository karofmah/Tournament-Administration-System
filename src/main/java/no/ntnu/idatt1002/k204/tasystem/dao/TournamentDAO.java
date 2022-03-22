package no.ntnu.idatt1002.k204.tasystem.dao;

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
    public void addTournament(String name, String status, String requirement, String date, String time) {
        String sql;
        if (isTest) {
            sql = "INSERT INTO tournamentTEST VALUES(null , ? , ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO tournament VALUES(null , ? , ?, ?, ?, ?)";
        }

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, status);
            statement.setString(3, requirement);
            statement.setString(4, date);
            statement.setString(5, time);
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
        String sql;

        if (isTest) {
            sql = "SELECT * FROM tournamentTEST";
        } else {
            sql = "SELECT * FROM tournament";
        }

        ResultSet res = null;
        try {
            res = Database.getConnection().prepareStatement(sql).executeQuery();
            while (res.next()) {
                Tournament tournament = new Tournament(res.getString("name"), res.getString("status"),res.getString("requirement"),
                        res.getString("start_date"), res.getString("start_time"));
                register.addTournament(tournament);
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
}
