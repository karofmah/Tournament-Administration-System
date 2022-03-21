package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import no.ntnu.idatt1002.k204.tasystem.Application;

import java.io.IOException;

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

    /**
     * Handle add team events
     *
     * Add team to database
     */
    @FXML
    void addTeamBtnClicked() {
        //TODO
        // ADD ME: Add team to database
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
