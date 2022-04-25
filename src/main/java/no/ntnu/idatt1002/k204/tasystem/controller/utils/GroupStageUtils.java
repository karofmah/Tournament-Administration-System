package no.ntnu.idatt1002.k204.tasystem.controller.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import no.ntnu.idatt1002.k204.tasystem.dao.GroupDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Group;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * The type Group stage utils.
 */
public class GroupStageUtils {

    private TeamRegister teamRegister;
    private ObservableList<Team> teamsObservableList;


    public GroupStageUtils(TeamRegister teamRegister) {
        this.teamRegister = teamRegister;
        this.teamsObservableList = FXCollections.observableArrayList(teamRegister.getTeams());
    }

    /**
     * Initialize tree items with teams from database.
     *
     * @param groupDAO     the group dao
     * @param groupName    the group name
     * @param tournamentId the tournament id
     * @param root         the root
     */
    public static void initWithTeamsFromDatabase(GroupDAO groupDAO, String groupName, int tournamentId, TreeItem<Team> root) {
        ArrayList<TreeItem<Team>> teamItems = new ArrayList();
        Group group = groupDAO.getGroup(groupName, tournamentId);

        if (group == null) {
            initWithDefaultText(root);
        } else {
            for (int i = 0; i < 3; i++) {//3 teams per group
                TreeItem<Team> teamItem = new TreeItem<>(group.getTeams().get(i));
                teamItems.add(teamItem);
            }
            root.getChildren().setAll(teamItems);
        }
    }

    /**
     * Initialize tree items with default text.
     *
     * @param root tree item root node
     */
    private static void initWithDefaultText(TreeItem<Team> root) {
        ArrayList<TreeItem<Team>> teamItems = new ArrayList();

        for (int i = 0; i < 3; i++) {//3 teams per group
            TreeItem<Team> teamItem = new TreeItem<>(new Team("<Double click to choose team>"));
            teamItems.add(teamItem);
        }

        root.getChildren().setAll(teamItems);
    }

    /**
     * Randomize teams inside a tree tableview
     *
     * @param root tree item root node
     */
    public void randomize(TreeItem<Team> root) {
        FXCollections.shuffle(this.teamsObservableList);

        ArrayList<TreeItem<Team>> teamItems = new ArrayList();

        //This loop is for filling last tree item root.
        // Otherwise index out of bounds error
        if (this.teamsObservableList.size() == 3) {
            for (Team t : this.teamsObservableList) {
                Team team = new Team(t);
                TreeItem<Team> teamItem = new TreeItem<>(team);
                teamItems.add(teamItem);
                root.getChildren().setAll(teamItems);
            }
            return;
        }

        for (int i = 0; i <= 2; i++) {//3 teams per group
            Team team = new Team(this.teamsObservableList.get(i));
            TreeItem<Team> teamItem = new TreeItem<>(team);
            teamItems.add(teamItem);
            this.teamsObservableList.remove(this.teamRegister.getTeamByName(team.getTeamName()));
        }
        root.getChildren().setAll(teamItems);
    }


    /**
     * Save group.
     *
     * @param groupDAO     the group dao
     * @param groupName    the group name
     * @param tableView    the table view
     * @param tournamentId the tournament id
     */
    public static void saveGroup(GroupDAO groupDAO, String groupName, TreeTableView<Team> tableView, int tournamentId) {
        String onlyDigitsRegex = "[0-9]+";

        TreeItem<Team> team1 = tableView.getTreeItem(0);
        TreeItem<Team> team2 = tableView.getTreeItem(1);
        TreeItem<Team> team3 = tableView.getTreeItem(2);

        SimpleStringProperty team1PointsValue = team1.valueProperty().getValue().pointsProperty();
        SimpleStringProperty team2PointsValue = team2.valueProperty().getValue().pointsProperty();
        SimpleStringProperty team3PointsValue = team3.valueProperty().getValue().pointsProperty();

        int team1Points;
        int team2Points;
        int team3Points;

        validateBeforeSave(onlyDigitsRegex, team1, team2, team3, team1PointsValue, team2PointsValue, team3PointsValue);

        team1Points = Integer.parseInt(team1PointsValue.getValue());
        team2Points = Integer.parseInt(team2PointsValue.getValue());
        team3Points = Integer.parseInt(team3PointsValue.getValue());

        groupDAO.addGroup(
                groupName,
                team1.getValue().getTeamName(),
                team2.getValue().getTeamName(),
                team3.getValue().getTeamName(),
                team1Points,
                team2Points,
                team3Points,
                tournamentId);
    }

    private static void validateBeforeSave(String onlyDigitsRegex, TreeItem<Team> team1, TreeItem<Team> team2,
                                           TreeItem<Team> team3, SimpleStringProperty team1PointsValue,
                                           SimpleStringProperty team2PointsValue, SimpleStringProperty team3PointsValue) {
        if (!team1PointsValue.getValue().matches(onlyDigitsRegex)
                || !team2PointsValue.getValue().matches(onlyDigitsRegex)
                || !team3PointsValue.getValue().matches(onlyDigitsRegex)) {
            throw new IllegalArgumentException("Points have to be a number!");
        } else if (team1.getValue().getTeamName().equalsIgnoreCase("<Double click to choose team>")
                || team2.getValue().getTeamName().equalsIgnoreCase("<Double click to choose team>")
                || team3.getValue().getTeamName().equalsIgnoreCase("<Double click to choose team>")) {
            throw new IllegalArgumentException("Please choose team(s)!");
        }
    }
}
