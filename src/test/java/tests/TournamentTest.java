package tests;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TournamentTest {
    private Team team1;
    private Player player1;
    private Player player2;
    @BeforeEach
    @DisplayName("Fills a team with two players")
    public void testData(){
        this.player1 = new Player("Sjokoladepudden", "Silver");
        this.player2 = new Player("Phase", "Iron");
        ArrayList<Player> playerlist = new ArrayList<>();
        playerlist.add(player1);
        playerlist.add(player2);
        this.team1 = new Team(playerlist, "GeirSittLag");
    }


}
