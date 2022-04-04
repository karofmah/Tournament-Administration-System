package no.ntnu.idatt1002.k204.tasystem.controller.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import no.ntnu.idatt1002.k204.tasystem.model.Team;

import java.util.ArrayList;

public class GroupStageUtils {

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
     * @param teams observable list containing teams
     * @param root  tree item root node
     */
    public static void randomize(ObservableList<Team> teams, TreeItem<Team> root) {
        FXCollections.shuffle(teams);

        ArrayList<TreeItem<Team>> teamItem = new ArrayList();
        for (int i = 0; i < 3; i++) {//3 teams per group
            Team team = teams.get(i);
            TreeItem<Team> team1 = new TreeItem<>(team);
            teamItem.add(team1);
        }

        root.getChildren().setAll(teamItem);
    }
}
