package no.ntnu.idatt1002.k204.tasystem.model;

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

    /**
     * Creates constructor for Tournament
     * @param name String that represents the name of the tournament, can not be blank
     * @param rankRequirement String that represents the rank requirement for the tournament, Unranked if blank
     * @param hasGroupStage Boolean that represents wheteher group stage will be included or not
     * @param dateTime LocalDateTime that represents the local date and time of the tournament
     * @throws IllegalArgumentException
     */
    public Tournament(String name, String rankRequirement,boolean hasGroupStage,LocalDateTime dateTime) throws IllegalArgumentException {
        this.name = name;
        if(name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank");
        }
        this.rankRequirement=rankRequirement;
        if(rankRequirement.isBlank()) {
            rankRequirement="Unranked";
        }
        this.hasGroupStage=hasGroupStage;
        this.dateTime=dateTime;
        this.teams = new ArrayList<Team>();
        this.isActive = false;
        this.winner = null;
    }

    /**
     * Returns name of the tournament
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns teams in the tournament
     * @return teams as an ArrayList
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     *Returns whether or not the
     * @return isActive as a boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     *
     * @return
     */
    public Team getWinner() {
        return winner;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     *
     * @param winner
     */
    public void setWinner(Team winner){this.winner=winner;}

    public boolean addTeam(Team team){
        if(!teams.contains(team)){
            return teams.add(team);
        }
        else{
            return false;
        }
    }

    /**
     *
     */
    public void startTournament() {
        setActive(true);
    }

    /**
     *
     * @param winner
     */
    public void finishTournament(Team winner){
        setActive(false);
        setWinner(winner);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", rankRequirement='" + rankRequirement + '\'' +
                ", hasGroupStage=" + hasGroupStage +
                ", dateTime=" + dateTime +
                ", teams=" + teams +
                ", isActive=" + isActive +
                ", winner=" + winner +
                '}';
    }
}
