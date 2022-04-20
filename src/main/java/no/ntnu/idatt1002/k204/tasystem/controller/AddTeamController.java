package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for adding a team
 */
public class AddTeamController implements Initializable {

    @FXML
    private Button AddTeamBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField p1NameTextfield;

    @FXML
    private ComboBox p1RankComboBox;

    @FXML
    private TextField p2NameTextfield;

    @FXML
    private ComboBox p2RankComboBox;

    @FXML
    private TextField p3NameTextfield;

    @FXML
    private ComboBox p3RankComboBox;

    @FXML
    private TextField p4NameTextfield;

    @FXML
    private ComboBox p4RankComboBox;

    @FXML
    private TextField p5NameTextfield;

    @FXML
    private ComboBox p5RankComboBox;

    @FXML
    private Label txtAddedTeam;

    /**
     * Handle add team events
     *
     * Add team to database
     */
    @FXML
    void addTeamBtnClicked() throws IOException {

        try {
            TeamDAO teamDAO = new TeamDAO();

            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player(p1NameTextfield.getText(), p1RankComboBox.getValue().toString()));
            players.add(new Player(p2NameTextfield.getText(), p2RankComboBox.getValue().toString()));
            players.add(new Player(p3NameTextfield.getText(), p3RankComboBox.getValue().toString()));
            players.add(new Player(p4NameTextfield.getText(), p4RankComboBox.getValue().toString()));
            players.add(new Player(p5NameTextfield.getText(), p5RankComboBox.getValue().toString()));

            Team team1 = new Team(players, nameTextField.getText());

            teamDAO.addTeam(team1.getPlayers(), team1.getTeamName());

            showInformationDialog(nameTextField.getText() + " has been added to the system!");

            Application.changeScene("addTeamView.fxml");
        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            Application.changeScene("teamsView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRanksToComboBox() {
        ObservableList<String> rankList=FXCollections.observableArrayList("Unranked","Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");

        p1RankComboBox.setItems(rankList);
        p2RankComboBox.setItems(rankList);
        p3RankComboBox.setItems(rankList);
        p4RankComboBox.setItems(rankList);
        p5RankComboBox.setItems(rankList);

    }


    /**
     * Handle logout event.
     *
     * Logout and send back to log in screen
     */
    @FXML
    void logoutBtnClicked() {
        try {
            Application.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addRanksToComboBox();
    }
}
