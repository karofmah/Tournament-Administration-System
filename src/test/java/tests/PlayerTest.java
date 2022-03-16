package tests;


import no.ntnu.idatt1002.k204.tasystem.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    void setGamerTagToBlankException() {
        Player player1 = new Player("Sjokoladepudden", "Silver");
        try {
            player1.setGamertag("");
        }catch (IllegalArgumentException e){
        }
        Assertions.assertThrows(IllegalArgumentException.class,()->new Player("","Silver"));
    }
    @Test
    void setGamerTag(){
        Player player1 = new Player("Sjokoladepudden", "Silver");
        try{
            player1.setGamertag("Phase");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(player1.getGamertag(),"Phase");
    }


    @Test
    void setRankToBlank() {
        Player player1 = new Player("Sjokoladepudden", "Silver");
        try {
            player1.setRank("");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(player1.getRank(),"Unranked");
    }

    @Test
    void setRank(){
        Player player1 = new Player("Sjokoladepudden", "Silver");
        try {
            player1.setRank("Challenger");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        Assertions.assertEquals(player1.getRank(),"Challenger");
    }
}
