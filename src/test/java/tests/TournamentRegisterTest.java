package tests;
import no.ntnu.idatt1002.k204.tasystem.TournamentRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class TournamentRegisterTest {

    @Test
    void addTournament(){
        TournamentRegister register = new TournamentRegister();
        register.addTournament("SummonersRift","Silver",false, LocalDateTime.of(2022, Month.APRIL,28,14,00,00));
        Assertions.assertEquals(register.getTournaments().get(0).getName(),"SummonersRift");
        register.addTournament("Tirsdagsturnering","Unranked",false, LocalDateTime.of(2022, Month.APRIL,18,14,00,00));
        register.addTournament("Onsdagsturnering","Silver",true, LocalDateTime.of(2022, Month.MARCH,28,14,00,00));
        System.out.println(register.getTournaments());

    }
}
