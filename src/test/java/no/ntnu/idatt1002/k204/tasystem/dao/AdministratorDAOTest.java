package no.ntnu.idatt1002.k204.tasystem.dao;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdministratorDAOTest {
    private Database database;
    private Connection connection;
    private AdministratorDAO administratorDAO;

    @BeforeAll
    public void setup() {
        database = Database.getInstance();
        connection = Database.getConnection();
        administratorDAO = new AdministratorDAO();
    }

    @AfterAll
    public void teardown() {
        try {
            administratorDAO = null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Get existing administrator, expected ok")
    public void getExistingAdministratorExpectedOk() {
        assertTrue(administratorDAO.getAdmin("benmu", "12345"));
    }

    @Test
    @DisplayName("Get non existing administrator, expected ok")
    public void getNonExistingAdministratorExpectedOk() {
        assertFalse(administratorDAO.getAdmin("karl", "marx"));
    }
}