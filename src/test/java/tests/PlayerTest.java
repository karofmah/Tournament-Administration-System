package tests;


import no.ntnu.idatt1002.k204.tasystem.Player;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void setGamerTagToBlank() {
    Player player1 = new Player("Sjokoladepudden", "Silver");
    try {
        player1.setGamertag("");
    }catch (IllegalArgumentException e){
        System.out.println(e.getMessage());
    }
        System.out.println(player1.toString());
    }

    @Test
    void setRankToBlank() {
        Player player1 = new Player("Sjokoladepudden", "Silver");
        try {
            player1.setRank("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println(player1.toString());
    }
}
