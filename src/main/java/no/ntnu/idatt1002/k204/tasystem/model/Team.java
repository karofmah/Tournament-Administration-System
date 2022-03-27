package no.ntnu.idatt1002.k204.tasystem.model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Team class. Creates a team with a list of players that will participate in tournament
 */
public class Team {
    private ArrayList<Player> players = new ArrayList<>();
    private SimpleStringProperty teamName; //Have to use string property because of combobox and events
    private SimpleStringProperty points;

    /**
     * Team-Constructors, the second one only has @param teamName
     * @param players players in team
     * @param teamName name of team
     */
    public Team(ArrayList<Player> players, String teamName) {
        this.players = players;
        if(players.isEmpty()){
            Team team=new Team(teamName);
        }
        this.teamName = new SimpleStringProperty(teamName);
        this.points = new SimpleStringProperty();
        if(teamName.isBlank()){
            throw new IllegalArgumentException("Team name can not be blank");
        }
    }

    public Team(String teamName){
        this.teamName = new SimpleStringProperty(teamName);
        if(teamName.isBlank()){
            throw new IllegalArgumentException("Team name can not be blank");
        }
        this.points = new SimpleStringProperty();
    }

    /**
     * Constructor used for retrieving teams from the database.
     * @param name the name of the team
     * @param p1name player 1's name (gamertag)
     * @param p1rank player 1's rank
     * @param p2name player 2's name (gamertag)
     * @param p2rank player 2's rank
     * @param p3name player 3's name (gamertag)
     * @param p3rank player 3's rank
     * @param p4name player 4's name (gamertag)
     * @param p4rank player 4's rank
     * @param p5name player 5's name (gamertag)
     * @param p5rank player 5's rank
     */
    public Team(String name, String p1name, String p1rank, String p2name, String p2rank, String p3name, String p3rank, String p4name, String p4rank, String p5name, String p5rank) {
        this.teamName = new SimpleStringProperty(name);

        ArrayList<Player> newPlayers = new ArrayList<>();
        newPlayers.add(new Player(p1name,p1rank));
        newPlayers.add(new Player(p2name,p2rank));
        newPlayers.add(new Player(p3name,p3rank));
        newPlayers.add(new Player(p4name,p4rank));
        newPlayers.add(new Player(p5name,p5rank));

        this.players = newPlayers;

    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public String getPoints() {
        return points.get();
    }

    public SimpleStringProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(String.valueOf(points));
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
    /*public String getTeamName() {
        return teamName;
    }*/

    /**
     * Method for adding player to team
     * @param player players in team
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Method for changing team name
     //* @param teamName name of team
     */
    /*public void setTeamName(String teamName) {
        this.teamName = teamName;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(players, team.players) && Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, teamName);
    }

    @Override
    public String toString() {
        return  "" + teamName;
    }
}
