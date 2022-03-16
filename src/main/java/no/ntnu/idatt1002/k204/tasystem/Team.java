package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;

/**
 * Team class. Creates a team with a list of players that will participate in tournament
 */
public class Team {
    private ArrayList<Player> players = new ArrayList<>();
    private String teamName;
    private int wins;

    /**
     * Team-Constructors, the second one only has @param teamName
     * @param players
     * @param teamName
     * @param wins
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
     * @param player
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Method for changing team name
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Method for modifying team wins
     * @param wins
     */
    public void setWins(int wins) {
        this.wins = wins;
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
