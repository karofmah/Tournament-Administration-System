package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamDAOTest {
    private Database database;
    private Connection connection;
    private TeamDAO teamDAO;

    @BeforeAll
    public void setup() {
        database = Database.getInstance();
        connection = Database.getConnection();
        teamDAO = new TeamDAO();
    }

    @AfterAll
    public void teardown() {
        try {
            teamDAO = null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Add new team, expected ok")
    public void addNewTeamExpectedOk() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Faker", "Challenger"));
        players.add(new Player("Khan", "Challenger"));
        players.add(new Player("Clid", "Challenger"));
        players.add(new Player("Teddy", "Challenger"));
        players.add(new Player("Effort", "Challenger"));
        teamDAO.addTeam(players, "Rip SKT");
    }

    @Test
    @DisplayName("Get teams, expected ok")
    public void getExistingTeam() {
        TeamRegister teams = new TeamRegister();
        teamDAO.getTeam(teams);
    }

    @Test
    @DisplayName("Delete team, expected ok")
    public void deleteTeamExpectedOk() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Faker", "Challenger"));
        players.add(new Player("Khan", "Challenger"));
        players.add(new Player("Clid", "Challenger"));
        players.add(new Player("Teddy", "Challenger"));
        players.add(new Player("Effort", "Challenger"));
        teamDAO.addTeam(players, "Rip SKT2");

        teamDAO.deleteTeam("Rip SKT2");
    }

    @Test
    @DisplayName("Update team, expected ok")
    public void updateTeamExpectedOk() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Mari", "Challenger"));
        players.add(new Player("Neptun", "Challenger"));
        players.add(new Player("Konrad", "Challenger"));
        players.add(new Player("Tu Morrow", "Challenger"));
        players.add(new Player("XÆ0-2K-L Musk", "Challenger"));

        teamDAO.updateTeam("Rip SKT", players);
    }

}