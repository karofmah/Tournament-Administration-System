package no.ntnu.idatt1002.k204.tasystem.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test
    @DisplayName("Create group, expected ok")
    public void createGroup() {
        Group group = new Group("GG WP");
        assertNotNull(group);
    }

    @Test
    @DisplayName("Create group with empty string")
    public void createGroupWithEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Group group = new Group("");
        });
    }

    @Test
    @DisplayName("Create group with null name")
    public void createGroupWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            Group group = new Group(null);
        });
    }
}