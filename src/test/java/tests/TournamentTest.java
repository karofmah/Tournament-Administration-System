package tests;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import static org.junit.jupiter.api.Assertions.*;

import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class TournamentTest {
    Tournament tournament1;
    Tournament tournament2;
    private Team team;
    private Player player1;
    private Player player2;
    @BeforeEach
    @DisplayName("Fills a team with two players")
    public void testData(){
        this.tournament1 =new Tournament("SummonersRift", "Silver","", false, LocalDateTime.of(2022, Month.APRIL, 28, 14, 00, 00));
        this.tournament2=new Tournament("SummonersRift", "","", false, LocalDateTime.of(2022, Month.APRIL, 28, 14, 00, 00));
        this.player1 = new Player("Sjokoladepudden", "Silver");
        this.player2 = new Player("Phase", "Iron");
        ArrayList<Player> playerlist = new ArrayList<>();
        playerlist.add(player1);
        playerlist.add(player2);
        this.team = new Team(playerlist, "GeirSittLag");
    }
    @Nested
    @DisplayName("Performs positive tests")
    public class inputIsSupported {

        @Test
        @DisplayName("Tests if it is possible to add a team to a tournament ")
        public void addTeam() {
            tournament1.addTeam(team);
            assertEquals("[GeirSittLag]", tournament1.getTeams().toString());
        }

        @Test
        @DisplayName("Tests if tournament has begun")
        public void startTournament() {
            tournament1.startTournament();
            assertTrue(tournament1.isActive());
        }

        @Test
        @DisplayName("Tests if tournament has finished")
        public void finishTournament() {
            tournament1.finishTournament(team);
            assertFalse(tournament1.isActive());
        }
        @Test
        @DisplayName("Tests if rank requirement can be blank")
        public void rankRequirementIsBlank(){
            assertEquals("Unranked",tournament2.getRankRequirement());
        }
        @Nested
        @DisplayName("Performs negative tests")
        public class InputIsNotSupported {
            @Test
            @DisplayName("Tests if an exception is thrown when a tournament with blank name is created")
            public void tournamentNameIsBlank() {
                assertThrows(IllegalArgumentException.class, () -> new Tournament("", "Silver","", false, LocalDateTime.of(2022, Month.APRIL, 28, 14, 00, 00)));
            }
        }
    }
}
