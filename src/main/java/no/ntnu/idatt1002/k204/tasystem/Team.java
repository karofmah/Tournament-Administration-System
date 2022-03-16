package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Team class. Creates a team with a list of players that will participate in tournament
 */
public class Team {
    private ArrayList<Player> players = new ArrayList<>();
    private String teamName;
    private int wins;

    /**
     * Team-Constructors, the second one only has @param teamName
     * @param players players in team
     * @param teamName name of team
     * @param wins amount of wins
     */
    public Team(ArrayList<Player> players, String teamName, int wins) {
        this.players = players;
        this.teamName = teamName;
        this.wins = wins;
    }

    public Team(String teamName){
        this.teamName = teamName;
        this.wins = 0;
    }

    /**
     * Method for getting players in team
     * @return players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Method for getting teamname
     * @return teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Method for getting amount of team wins
     * @return wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Method for adding player to team
     * @param player players in team
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Method for changing team name
     * @param teamName name of team
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Method for modifying team wins
     * @param wins amount of wins
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(players, team.players) && Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, teamName, wins);
    }

    @Override
    public String toString() {
        return "Team{" +
                "players=" + players +
                ", teamName='" + teamName + '\'' +
                ", wins=" + wins +
                '}';
    }
}
