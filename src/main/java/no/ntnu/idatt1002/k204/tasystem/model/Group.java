package no.ntnu.idatt1002.k204.tasystem.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that defines a group.
 */
public class Group {
    private final String groupName;
    private ArrayList<Team> teams;

    /**
     * Instantiates a new Group.
     *
     * @param groupName the group name
     */
    public Group(String groupName) {
        this.groupName = groupName;
        this.teams = new ArrayList<>();
    }

    public boolean addTeam(String teamName) {
        return this.teams.add(new Team(teamName));
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Check equality of a group object.
     * Groups are equal if they have the same group name and contains same teams.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((!(o instanceof Group))) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(teams, group.teams);
    }

    @Override
    public String toString() {
        return "" + groupName;
    }
}
