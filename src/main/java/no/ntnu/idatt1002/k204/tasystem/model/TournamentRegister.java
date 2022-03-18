package no.ntnu.idatt1002.k204.tasystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Tournament register that holds all tournaments.
 */
public class TournamentRegister {
    private ArrayList<Tournament> tournaments = new ArrayList<Tournament>();

    /**
     * The constructor for the TournamentRegister class.
     */
    public TournamentRegister() {
    }

    /**
     * Adds a new tournament to the register.
     * @param name the name of the tournament
     * @param dateTime the date and time of the tournament
     * @param rankRequirement the minimum rank required to take part in the tournament
     * @param hasGroupStage if the tournament has a group stage or not.
     *                      If there are 8 teams competing, there will only be a knockout stage.
     *                      If there are 12 teams competing, there will be a group stage as well.
     */
    public boolean addTournament(String name, String rankRequirement, boolean hasGroupStage, LocalDateTime dateTime) {
        return tournaments.add(new Tournament(name, rankRequirement, hasGroupStage, dateTime));
    }

    /**
     * Gets a list of all tournaments in the register.
     * @return a list of tournaments
     */
    public ArrayList<Tournament> getTournaments() {
        return tournaments;
    }

    /**
     * Searches for a specific tournament in the register.
     * @param tournamentName the name of the tournament, as a String
     * @return the corresponding Tournament object if a matching tournament is found. If not, null is returned.
     */
    public Tournament getTournamentByName(String tournamentName) {
        for (Tournament tournament: tournaments) {
            if (tournament.getName().equals(tournamentName)) {
                return tournament;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TournamentRegister{" +
                "tournaments=" + tournaments +
                '}';
    }
}
