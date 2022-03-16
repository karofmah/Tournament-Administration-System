package no.ntnu.idatt1002.k204.tasystem.model;

import java.util.HashSet;

/**
 * The register that holds all teams in the system.
 * Utilizes HashSet to ensure that each team is only added once.
 */
public class TeamRegister {
    private HashSet<Team> teams = new HashSet<>();

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
