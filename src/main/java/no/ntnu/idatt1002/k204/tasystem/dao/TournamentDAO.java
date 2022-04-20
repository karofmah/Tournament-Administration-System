package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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
     * @param rankRequirement the rankRequirement
     * @param otherRequirement the other requirement
     * @param date        the date
     * @param time        the time
     */
    public void addTournament(int id, String name, String status, String rankRequirement,String otherRequirement, String date, String time) {
        String sql;
       if (isTest) {
            sql = "INSERT INTO TESTtournament VALUES(? , ? , ?, ?, ?, ?, ?,null)";
        } else {
            sql = "INSERT INTO tournament VALUES(? , ? , ?, ?, ?, ?, ?,null)";
        }

       if (id <= getMaxTournamentID()) {
           id = getMaxTournamentID()+ 1;
       }

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, status);
            statement.setString(4, rankRequirement);
            statement.setString(5,otherRequirement);
            statement.setString(6, date);
            statement.setString(7, time);
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

    private int getMaxTournamentID () {
        String sql = "SELECT MAX(tournament_id) AS MaxID FROM tournament";

        int maxID = 0;

        ResultSet result;
        try {
            result = Database.getConnection().prepareStatement(sql).executeQuery();
            while (result.next()){
                maxID = result.getInt("MaxID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxID;
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
            sql = "SELECT * FROM TESTtournament";
        } else {
            sql = "SELECT * FROM tournament";
        }

        ResultSet result = null;

        try {
            result = Database.getConnection().prepareStatement(sql).executeQuery();
            while (result.next()) {
                Tournament tournament = new Tournament(result.getString("tournament_id"), result.getString("name"), result.getString("status"),result.getString("rankRequirement"),
                        result.getString("otherRequirement"),result.getString("start_date"), result.getString("start_time"));
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
        String sql = "INSERT INTO tournament_team VALUES(? , ?, ?)";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, tournamentID);
            statement.setString(2, teamName);
            statement.setInt(3, 0);
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

    public void deleteTournament(int tournamentID){
        String sql1 = "DELETE FROM tournament_team WHERE tournament_id = ?";
        String sql2 = "DELETE FROM tournament WHERE tournament_id = ?";
        String sql3 = "DELETE FROM knockout_match WHERE tournament_id = ?";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql1);
            statement.setInt(1, tournamentID);
            statement.executeUpdate();
            statement = Database.getConnection().prepareStatement(sql2);
            statement.setInt(1, tournamentID);
            statement.executeUpdate();
            statement = Database.getConnection().prepareStatement(sql3);
            statement.setInt(1, tournamentID);
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
        String sql = "SELECT * from tournament_team WHERE tournament_id = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        TeamRegister teamRegister = new TeamRegister();
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1,tournamentID);

            result = statement.executeQuery();
            while (result.next()) {
                Team team = new Team(result.getString("teamName"), result.getString("points"));

                 teamRegister.addTeam(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(statement, result);
        }
        return teamRegister;
    }

    /**
     * Gets a tournament from the database
     * @param tournamentID the ID of the tournament
     * @return the tournament object
     */
    public Tournament getTournamentById(int tournamentID) {
        String sql = "SELECT * from tournament WHERE tournament_id = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        Tournament tournament = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1,tournamentID);

            result = statement.executeQuery();
            while (result.next()) {
                tournament = new Tournament(result.getString("tournament_id"), result.getString("name"),
                        result.getString("status"),result.getString("rankRequirement"),
                        result.getString("otherRequirement"),result.getString("start_date"),
                        result.getString("start_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(statement, result);
        }
        return tournament;
    }

    /**
     * Method used to update a tournament in the database.
     * @param id the selected tournament ID
     * @param name the new name of the tournament
     * @param rankRequirement the new rank requirement
     * @param otherRequirement the new general requirement
     * @param date the new date
     * @param time the new time
     */
    public void updateTournament(int id, String name, String rankRequirement, String otherRequirement, String date, String time) {
        String sql;
        if (isTest) {
            sql = "UPDATE TESTtournament SET name = ?, rankRequirement = ?, otherRequirement = ?, start_date = ?, start_time = ? WHERE tournament_id = ?";
        } else {
            sql = "UPDATE tournament SET name = ?, rankRequirement = ?, otherRequirement = ?, start_date = ?, start_time = ? WHERE tournament_id = ?";
        }

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, rankRequirement);
            statement.setString(3, otherRequirement);
            statement.setString(4, date);
            statement.setString(5, time);
            statement.setInt(6, id);
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

    public void updateTournamentStatus(int tournamentId, String status) {
        String sql;
        if (isTest) {
            sql = "UPDATE TESTtournament SET name = ?, rankRequirement = ?, otherRequirement = ?, start_date = ?, start_time = ? WHERE tournament_id = ?";
        } else {
            sql = "UPDATE tournament SET status = ? WHERE tournament_id = ?";
        }

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, status);
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

    public void getKnockoutMatches(int tournamentID, Team[][] matches) {
        String sql = "SELECT * from knockout_match WHERE tournament_id = ?";

        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1,tournamentID);

            result = statement.executeQuery();

            int i = 0;
            while (result.next()) {
                Team team1 = null;
                Team team2 = null;
                Team winner = null;

                if (!result.getString("team1").equals("")) {
                    team1 = new Team(result.getString("team1"));
                }

                if (!result.getString("team2").equals("")) {
                    team2 = new Team(result.getString("team2"));
                }

                if (!result.getString("winner").equals("")) {
                    winner = new Team(result.getString("winner"));
                }

                matches[i] = (new Team[]{team1, team2, winner});

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.close(statement, result);
        }
    }

    public void updateKnockoutMatches(int tournamentID, Team[][] matches){
        String sql1 = "DELETE FROM knockout_match WHERE tournament_id = ?";
        String sql2 = "INSERT INTO knockout_match VALUES(?, ?, ?, ?, ?)";

        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement(sql1);
            statement.setInt(1, tournamentID);
            statement.executeUpdate();

            for (int i = 0; i < matches.length; i++) {

                statement = Database.getConnection().prepareStatement(sql2);
                statement.setInt(1, tournamentID);
                statement.setInt(2, i+1);

                if (matches[i][0] == null) {
                    //statement.setNull(2, Types.VARCHAR);
                    statement.setString(3, "");
                } else {
                    statement.setString(3, matches[i][0].getTeamName());
                }

                if (matches[i][1] == null) {
                    //statement.setNull(3, Types.VARCHAR);
                    statement.setString(4, "");
                } else {
                    statement.setString(4, matches[i][1].getTeamName());
                }

                if (matches[i][2] == null) {
                    //statement.setNull(4, Types.VARCHAR);
                    statement.setString(5, "");
                } else {
                    statement.setString(5, matches[i][2].getTeamName());
                }

                statement.executeUpdate();
            }

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
