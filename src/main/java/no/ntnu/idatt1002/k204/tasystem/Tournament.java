package no.ntnu.idatt1002.k204.tasystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Tournament {
    private String name;
    private String rankRequirement;
    private boolean hasGroupStage;
    private LocalDateTime dateTime;
    private ArrayList<Team> teams;
    private boolean isActive;
    private Team winner;

    public Tournament(String name, String rankRequirement,boolean hasGroupStage,LocalDateTime dateTime, ArrayList<Team> teams) {
        this.name = name;
        this.rankRequirement=rankRequirement;
        this.hasGroupStage=hasGroupStage;
        this.dateTime=dateTime;
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
