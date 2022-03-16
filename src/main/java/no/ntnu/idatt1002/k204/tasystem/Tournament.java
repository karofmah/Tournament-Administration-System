package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;

public class Tournament {
    private String name;
    private ArrayList<Team> teams;
    private boolean isActive;
    private Team winner;

    public Tournament(String name, ArrayList<Team> teams) {
        this.name = name;
        this.teams = teams;
        this.isActive = isActive;
        this.winner = winner;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public boolean isActive() {
        return isActive;
    }

    public Team getWinner() {
        return winner;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
