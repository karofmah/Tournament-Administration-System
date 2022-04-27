package no.ntnu.idatt1002.k204.tasystem.dao;

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
        groupDAO.addGroup("Kjeltringane", "A-laget", "B-laget", "C-laget", 0, 0, 0, 2);
    }

    @Test
    @DisplayName("Add already existing group, expected ok")
    public void addAlreadyExistingGroupExpectedOk() {
        groupDAO.addGroup("Kjeltringane", "A-laget", "B-laget", "C-laget", 0, 0, 0, 2);
    }

    @Test
    @DisplayName("Get existing group, expected ok")
    public void getExistingGroupExpectedOk() {
        groupDAO.getGroup("Kjeltringane", 2);
    }

    @Test
    @DisplayName("Get non existing group")
    public void getNonExistingGroup() {
        assertNull(groupDAO.getGroup("Gratis pølser i kantina", 3));
    }
}