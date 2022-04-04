package no.ntnu.idatt1002.k204.tasystem.model;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Tournament {
    private static int countTournaments = 1;//keep track of count tournaments. Also used to update an id for a tournament.
    private static int selectedTournamentID;//keep track on the id of the selected tournament
    private final int tournamentId;
    private String name;
    private String rankRequirement;
    private String otherRequirement;
    private boolean hasGroupStage;
    private LocalDateTime dateTime;
    private ArrayList<Team> teams = new ArrayList<>();
    private boolean isActive;
    private Team winner;
    private LocalDate date;
    private LocalTime time;
    private String status;

    /**
     * Creates constructor for Tournament
     *
     * @param name            String that represents the name of the tournament, can not be blank
     * @param rankRequirement String that represents the rank requirement for the tournament, no requirements if blank
     * @param otherRequirement String that represents another requirements that is created by the admin
     * @param hasGroupStage   Boolean that represents whether group stage will be included or not
     * @param dateTime        LocalDateTime that represents the local date and time of the tournament
     * @throws IllegalArgumentException
     */
    public Tournament(String name, String rankRequirement,String otherRequirement, boolean hasGroupStage, LocalDateTime dateTime) throws IllegalArgumentException {
        this.name = name;
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name can not be blank");
        }
        this.rankRequirement = rankRequirement;
        if (rankRequirement.isBlank()) {
            this.rankRequirement = "No requirements";
        }
        this.otherRequirement=otherRequirement;
        this.hasGroupStage = hasGroupStage;
        this.dateTime = dateTime;
        this.teams = new ArrayList<>();
        this.isActive = false;
        this.winner = null;

        countTournaments++;
        tournamentId = countTournaments;
    }

    /**
     * Instantiates a new tournament. Used when adding tournament to database
     *
     * @param name
     * @param rankRequirement
     * @param date
     * @param time
     */
    public Tournament(String name, String rankRequirement,String otherRequirement, String date, String time) throws IllegalArgumentException {
        this.name = name;
        if (name.isBlank()) throw new IllegalArgumentException("Name can not be blank");
        this.rankRequirement = rankRequirement;
        if (rankRequirement.isBlank()) this.rankRequirement = "No requirements";
        this.otherRequirement=otherRequirement;
        this.isActive = false;

        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please select a valid date");
        }
        try {
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please choose a valid time");
        }

        countTournaments++;
        tournamentId = countTournaments;
    }

    /**
     * Instantiates a new tournament. Used when adding fetching tournament from database
     *
     * @param name
     * @param status
     * @param rankRequirement
     * @param date
     * @param time
     */
    public Tournament(String name, String status, String rankRequirement,String otherRequirement, String date, String time) {
        this.name = name;
        this.rankRequirement = rankRequirement;
        this.otherRequirement=otherRequirement;
        this.status = status;
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);

        countTournaments++;
        tournamentId = countTournaments;
    }

    public static void setCountTournaments(int countTournaments) {
        Tournament.countTournaments = countTournaments;
    }

    public static void setSelectedTournamentID(int selectedTournamentID) {
        Tournament.selectedTournamentID = selectedTournamentID;
    }

    public static int getSelectedTournamentID() {
        return selectedTournamentID;
    }


    /**
     * Gets tournament id.
     *
     * @return the tournament id
     */
    public int getTournamentId() {
        return tournamentId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns name of the tournament
     *
     * @return name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns rand requirement to enter the tournament
     *
     * @return rankRequirement as a String
     */
    public String getRankRequirement() {
        return rankRequirement;
    }
    public String getOtherRequirement(){
        return otherRequirement;
    }

    /**
     * Returns teams in the tournament
     *
     * @return teams as an ArrayList
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Returns whether or not the tournament is active
     *
     * @return isActive as a boolean
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the winner of the tournament
     *
     * @return winner as the type Team
     */
    public Team getWinner() {
        return winner;
    }

    /**
     * Changes whether or not the tournament is active
     *
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Sets the winner
     *
     * @param winner
     */
    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public boolean addTeam(Team team) {
        if (!teams.contains(team)) {
            return teams.add(team);
        } else {
            return false;
        }
    }

    /**
     * Starts the tournament by setting active to be true
     */
    public void startTournament() {
        setActive(true);
    }

    /**
     * Finishes tournament by selecting a winner and setting active to be false
     *
     * @param winner
     */
    public void finishTournament(Team winner) {
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
