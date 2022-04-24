package no.ntnu.idatt1002.k204.tasystem.controller.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import no.ntnu.idatt1002.k204.tasystem.dao.GroupDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;

import java.util.ArrayList;

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
     * Initialize tree items with default text.
     *
     * @param root tree item root node
     */
    public static void initDefaultText(TreeItem<Team> root) {
        ArrayList<TreeItem<Team>> teamItem1 = new ArrayList();

        for (int i = 0; i < 3; i++) {//3 teams per group
            TreeItem<Team> team1 = new TreeItem<>(new Team("<Double click to choose team>"));
            teamItem1.add(team1);
        }

        root.getChildren().setAll(teamItem1);
    }


    /**
     * Randomize teams inside a tree tableview
     *
     * @param root  tree item root node
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
            TreeItem<Team> team1 = new TreeItem<>(team);
            teamItems.add(team1);
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
    public static void saveGroup(GroupDAO groupDAO, String groupName,TreeTableView<Team> tableView, int tournamentId) {
        groupDAO.addGroup(groupName,
                tableView.getTreeItem(0).getValue().getTeamName(),
                tableView.getTreeItem(1).getValue().getTeamName(),
                tableView.getTreeItem(2).getValue().getTeamName(),
                tournamentId);
    }
}
