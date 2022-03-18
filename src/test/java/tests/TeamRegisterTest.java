package tests;

import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class TeamRegisterTest {
    private TeamRegister teamRegister;
    private Team team1;
    private Team team2;

    @BeforeEach
    public void TestData(){
        this.teamRegister = new TeamRegister();

        Player player1 = new Player("Sjokoladepudden", "Silver");
        Player player2 = new Player("Phase", "Iron");
        ArrayList<Player> playerlist1 = new ArrayList<>();
        playerlist1.add(player1);
        playerlist1.add(player2);
        this.team1 = new Team(playerlist1,"GeirSittLag");

        Player player3 = new Player("Rain", "Gold");
        Player player4 = new Player("Olofmeister", "Iron");
        ArrayList<Player> playerlist2 = new ArrayList<>();
        playerlist2.add(player3);
        playerlist2.add(player4);
        this.team2 = new Team(playerlist2,"Ninjas in Pyjamas");
    }

    @Test
    public void addTeam(){
        teamRegister.addTeam(team1);
        Assertions.assertTrue(teamRegister.getTeams().contains(teamRegister.getTeamByName("GeirSittLag")));
    }

    @Test
    public void addTwoTeams(){
        teamRegister.addTeam(team2);
        teamRegister.addTeam(team1);
        Assertions.assertEquals("Ninjas in Pyjamas", teamRegister.getTeamByName("Ninjas in Pyjamas").toString());
    }
}
