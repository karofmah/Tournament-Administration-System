package no.ntnu.idatt1002.k204.tasystem.model;

import java.util.Objects;

public class Group {
    private final String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((!(o instanceof Group))) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName);
    }

    @Override
    public String toString() {
        return "" + groupName;
    }
}
