package no.ntnu.idatt1002.k204.tasystem.model;


import no.ntnu.idatt1002.k204.tasystem.model.Player;
import org.junit.jupiter.api.*;

public class PlayerTest {
    private Player player1;

    @BeforeEach
    @DisplayName("Create a new player")
    public void testData() {
        this.player1 = new Player("Sjokoladepudden", "Silver");
    }

    @Nested
    @DisplayName("Performs positive tests")
    public class inputIsSupported {
        @Test
        @DisplayName("Tests if it is possible to change a player's gamer-tag")
        public void setGamerTag() {
            try {
                player1.setGamertag("Phase");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            Assertions.assertEquals("Phase", player1.getGamertag());
        }

        @Test
        @DisplayName("Tests if it is possible to change rank to blank ")
        public void setRankToBlank() {
            player1.setRank("");
            Assertions.assertEquals("Unranked", player1.getRank());
        }

        @Test
        @DisplayName("Tests if it is possible to change the rank of a player")
        public void setRank() {
            player1.setRank("Challenger");
            Assertions.assertEquals("Challenger", player1.getRank());
        }
    }

    @Nested
    @DisplayName("Performs negative tests")
    public class inputIsNotSupported {
        @Test
        @DisplayName("Tests if an exception is thrown when a player's gamer-tag is empty")
        public void setGamerTagToBlankException() {
            try {
                player1.setGamertag("");
            } catch (IllegalArgumentException e) {
            }
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("", "Silver"));
        }
    }
}

