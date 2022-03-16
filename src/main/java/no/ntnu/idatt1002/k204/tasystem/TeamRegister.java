package no.ntnu.idatt1002.k204.tasystem;

import java.util.ArrayList;

public class TeamRegister {
    private ArrayList<Team> teams = new ArrayList<>();

    /**
     * The constructor for the TeamRegister.
     */
    public TeamRegister() {
    }

    /**
     * Adds a new team to the system.
     * @param team the new team that will be added
     */
    public void addTeam(Team team) {
        teams.add(team);
    }
}
