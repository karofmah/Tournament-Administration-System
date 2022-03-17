package tests;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class TournamentRegisterTest {
    //TODO Fix maven tests, currently not running properly
    // 1. Make class for testing public and test methods public otherwise maven lifecycle "test" will fail including
    // some other maven build lifecycles e.g. package
    TournamentRegister register = new TournamentRegister();
    void TestData(){
        Player player1 = new Player("Sjokoladepudden", "Silver");
        Player player2 = new Player("Phase", "Iron");
        ArrayList<Player> playerlist = new ArrayList<>();
        playerlist.add(player1);
        playerlist.add(player2);
        Team team1 = new Team(playerlist,"GeirSittLag",0);
    }


    @Test
    public void addTournament(){
        TestData();
        Tournament tournament = new Tournament("SummonersRift","Silver",false, LocalDateTime.of(2022, Month.APRIL,28,14,00,00));
        Assertions.assertTrue(register.addTournament("SummonersRift","Silver",false, LocalDateTime.of(2022, Month.APRIL,28,14,00,00)));
    }
}
