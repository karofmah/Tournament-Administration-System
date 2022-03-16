package no.ntnu.idatt1002.k204.tasystem;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tournament {
    private String name;
    private String rankRequirement;
    private boolean hasGroupStage;
    private LocalDateTime dateTime;
    private ArrayList<Team> teams;
    private boolean isActive;
    private Team winner;

    public Tournament(String name, String rankRequirement,boolean hasGroupStage,LocalDateTime dateTime) {
        this.name = name;
        this.rankRequirement=rankRequirement;
        this.hasGroupStage=hasGroupStage;
        this.dateTime=dateTime;
        this.teams = new ArrayList<Team>();
        this.isActive = false;
        this.winner = null;
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

    public void setActive(boolean active) {
        isActive = active;
    }
    public void setWinner(Team winner){
        this.winner=winner;
    }

    public boolean addTeam(Team team){
        if(!teams.contains(team)){
            return teams.add(team);
        }
        else{
            return false;
        }
    }
    public void startTournament() {
        setActive(true);
    }

    public void finishTournament(Team winner){
        setActive(false);
        setWinner(winner);
    }
}
