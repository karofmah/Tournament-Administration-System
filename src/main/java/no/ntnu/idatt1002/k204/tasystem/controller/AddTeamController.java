package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for adding a team
 */
public class AddTeamController implements Initializable {


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

    private TeamDAO teamDAO;

    private TeamRegister teamRegister;

    private TextField[] names;

    private ComboBox[] ranks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teamDAO = new TeamDAO();
        this.teamRegister = new TeamRegister();
        teamDAO.getTeam(teamRegister);

        names = new TextField[]{p1NameTextfield, p2NameTextfield, p3NameTextfield, p4NameTextfield, p5NameTextfield};
        ranks = new ComboBox[]{p1RankComboBox, p2RankComboBox, p3RankComboBox, p4RankComboBox, p5RankComboBox};

        addRanksToComboBox();
    }

    /**
     * Handle add team events
     *
     * Add team to database
     */
    @FXML
    void addTeamBtnClicked() throws IOException {

        try {
            ArrayList<Player> players = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                if (ranks[i].getValue() != null) {
                    players.add(new Player(names[i].getText(), ranks[i].getValue().toString()));
                } else {
                    throw new IllegalArgumentException("Please select a rank for each player");
                }
            }

            for (Team team : teamRegister.getTeams()) {
                if (team.getTeamName().equals(nameTextField.getText())) {
                    throw new IllegalArgumentException("The team name already exists");
                }
            }

            Team team = new Team(players, nameTextField.getText());

            teamDAO.addTeam(team.getPlayers(), team.getTeamName());

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

        for (ComboBox comboBox : ranks) {
            comboBox.setItems(rankList);
        }

    }




}
