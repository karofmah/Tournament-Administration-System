package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller for adding a team
 */
public class AddTeamController {

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
    private TextField p1RankTextfield;

    @FXML
    private TextField p2NameTextfield;

    @FXML
    private TextField p2RankTextfield;

    @FXML
    private TextField p3NameTextfield;

    @FXML
    private TextField p3RankTextfield;

    @FXML
    private TextField p4NameTextfield;

    @FXML
    private TextField p4RankTextfield;

    @FXML
    private TextField p5NameTextfield;

    @FXML
    private TextField p5RankTextfield;

    @FXML
    private Label txtAddedTeam;

    /**
     * Handle add team events
     *
     * Add team to database
     */
    @FXML
    void addTeamBtnClicked() {
        TeamDAO teamDAO = new TeamDAO();

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(p1NameTextfield.getText(), p1RankTextfield.getText()));
        players.add(new Player(p2NameTextfield.getText(), p2RankTextfield.getText()));
        players.add(new Player(p3NameTextfield.getText(), p3RankTextfield.getText()));
        players.add(new Player(p4NameTextfield.getText(), p4RankTextfield.getText()));
        players.add(new Player(p5NameTextfield.getText(), p5RankTextfield.getText()));

        Team team1 = new Team(players, nameTextField.getText());

        teamDAO.addTeam(team1.getPlayers(), team1.getTeamName());

        txtAddedTeam.setText(nameTextField.getText() + " has been added to the system!");
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

}
