package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;

public class Tournament {
    private String name;
    private ArrayList<Team> teams;
    private boolean isActive;
    private Team team;

    public Tournament(String name, ArrayList<Team> teams, boolean isActive, Team team) {
        this.name = name;
        this.teams = teams;
        this.isActive = isActive;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
