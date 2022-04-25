package no.ntnu.idatt1002.k204.tasystem.dao;

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
        tournamentDAO.addTournament(1,"LCK for noobs 2022", "Inactive", "Bronze 2","", "2022-07-21", "13:37");
    }

    @Test
    @DisplayName("Get existing tournament, expected ok")
    void getExistingTournamentExpectedOk() {
        Tournament tournament = new Tournament("LCK for noobs 2022", "Bronze 2", "Bronze 2", "2022-07-21", "13:37");

        tournamentDAO.getTournament(tournamentRegister);

        assertEquals(tournamentRegister.getTournamentByName("LCK for noobs 2022").toString(), tournament.toString());
    }
}