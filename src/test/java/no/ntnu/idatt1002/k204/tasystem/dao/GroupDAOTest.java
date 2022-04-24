package no.ntnu.idatt1002.k204.tasystem.dao;

import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GroupDAOTest {
    private Database database;
    private Connection connection;
    private GroupDAO groupDAO;

    @BeforeAll
    public void setup() {
        database = Database.getInstance();
        connection = Database.getConnection();
        groupDAO = new GroupDAO();
    }

    @AfterAll
    public void teardown() {
        try {
            groupDAO = null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Add group to database, expected ok")
    public void addGroupToDatabaseExpectedOk() {

    }
}