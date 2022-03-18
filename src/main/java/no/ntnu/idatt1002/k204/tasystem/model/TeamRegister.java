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
