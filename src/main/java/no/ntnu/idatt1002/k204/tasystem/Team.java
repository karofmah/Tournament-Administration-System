package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> players = new ArrayList<>();
    private String teamName;
    private int wins;

    public Team(ArrayList<Player> players, String teamName, int wins) {
        this.players = players;
        this.teamName = teamName;
        this.wins = wins;
    }

    public Team(String teamName){
        this.teamName = teamName;
        this.wins = 0;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWins() {
        return wins;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

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
