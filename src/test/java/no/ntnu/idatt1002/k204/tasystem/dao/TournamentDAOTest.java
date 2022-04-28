package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TournamentDAOTest {
    private Database database;
    private Connection connection;
    private TournamentDAO tournamentDAO;
    private TournamentRegister tournamentRegister;

    @BeforeAll
    public void setup() {
        database = Database.getInstance();
        connection = Database.getConnection();
        tournamentDAO = new TournamentDAO();
        tournamentRegister = new TournamentRegister();
    }

    @AfterAll
    public void teardown() {
        try {
            tournamentDAO = null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Add tournament, expected ok")
    public void addTournamentExpectedOk() {
        tournamentDAO.addTournament(1, "LCK for noobs 2022", "Inactive", "Bronze 2", "PC", "2022-07-21", "13:37");
    }

    @Test
    @DisplayName("Add tournament with same id, expected ok")
    public void addTournamentWithSameIdExpectedOk() {
        tournamentDAO.addTournament(2, "LCK for noobs 2022", "Inactive", "Bronze 2", "PC", "2022-07-21", "13:37");
        tournamentDAO.addTournament(2, "LCK for noobs 2022", "Inactive", "Bronze 2", "PC", "2022-07-21", "13:39");
    }

    @Test
    @DisplayName("Get existing tournament, expected ok")
    public void getExistingTournamentExpectedOk() {
        Tournament tournament = new Tournament("LCK for noobs 2022", "Bronze 2", "Bronze 2", "2022-07-21", "13:37");

        tournamentDAO.getTournament(tournamentRegister);

        assertEquals(tournamentRegister.getTournamentByName("LCK for noobs 2022").toString(), tournament.toString());
    }

    @Test
    @DisplayName("Add tournament and team, expected ok")
    public void addTournamentAndTeam() {
        tournamentDAO.addTournamentAndTeam(2, "Rip SKT");
    }

    @Test
    @DisplayName("Add duplicate tournament and team, expected ok")
    public void addDuplicateTournamentAndTeamExpectedOk() {
        tournamentDAO.addTournamentAndTeam(2, "Rip SKT");
    }

    @Test
    @DisplayName("Delete tournament, expected ok")
    public void deleteTournamentExpectedOk() {
        tournamentDAO.deleteTournament(3);
    }

    @Test
    @DisplayName("Get teams given tournament id, expected ok")
    public void getTeamsGivenTournamentIdExpectedOk() {
        TeamRegister teamRegister = tournamentDAO.getTeamsGivenTournamentId(1);
        assertNotNull(teamRegister);
    }

    @Test
    @DisplayName("Get tournament by id, expected ok")
    public void getTournamentByIdExpectedOk() {
        Tournament tournament = tournamentDAO.getTournamentById(2);
        assertNotNull(tournament);
    }

    @Test
    @DisplayName("Get tournament by id not existing")
    public void getTournamentByIdNotExisting() {
        Tournament tournament = tournamentDAO.getTournamentById(99);
        assertNull(tournament);
    }

    @Test
    @DisplayName("Update tournament, expected ok")
    public void updateTournamentExpectedOk() {
        tournamentDAO.updateTournament(1, "LCK for lolz", "Bronze 2", "PC", "2022-07-21", "13:39");
    }

    @Test
    @DisplayName("Update tournament status")
    public void updateTournamentStatus() {
        tournamentDAO.updateTournamentStatus(5, "Group Stage");
    }

    @Test
    @DisplayName("Update Knockout Match")
    void updateKnockoutMatch() {
        Team[][] matches = new Team[7][3];
        tournamentDAO.updateKnockoutMatches(1, matches);
    }

    @Test
    @DisplayName("Get knockout match")
    void getKnockoutMatch() {
        Team[][] matches = new Team[7][3];
        tournamentDAO.getKnockoutMatches(1, matches);
    }
}
