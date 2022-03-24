package no.ntnu.idatt1002.k204.tasystem.model;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.util.HashSet;

/**
 * The register that holds all teams in the system.
 * Utilizes HashSet to ensure that each team is only added once.
 */
public class TeamRegister {
    private HashSet<Team> teams = new HashSet<>();
    private ComboBox<Team> teamComboBox;
    private ObservableList<Team> teamObservableList;//Need this in order to display teams in combo box.
    //Combo box need an observable list in order to work.

    /**
     * The constructor for the TeamRegister.
     */
    public TeamRegister() {
    }

    /**
     * Instantiates a new Team register using an observable list
     *
     * @param teamObservableList observable list of teams
     */
    public TeamRegister(ObservableList<Team> teamObservableList) {
        this.teamObservableList = teamObservableList;
        this.teamComboBox = new ComboBox<>(teamObservableList);
    }

    /**
     * Adds a new team to the system.
     * @param team the new team that will be added
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addTeamToObservableList(Team team) {
        this.teamObservableList.add(team);
    }

    public ObservableList<Team> getTeamObservableList() {
        return teamObservableList;
    }


    /**
     * Gets a list of all the teams in the register.
     * @return a list of teams
     */
    public HashSet<Team> getTeams() {
        return teams;
    }

    public ComboBox<Team> getTeamComboBox() {
        return teamComboBox;
    }

    public void setTeamComboBox(ComboBox<Team> teamComboBox) {
        this.teamComboBox = teamComboBox;
    }

    /**
     * Searches for a team in the TeamRegister by its name.
     * @param teamName the name of the team, as a String
     * @return the corresponding team in the TeamRegister. If it does not exist, null will be returned.
     */
    public Team getTeamByName(String teamName) {
        for (Team team: teams) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
}
