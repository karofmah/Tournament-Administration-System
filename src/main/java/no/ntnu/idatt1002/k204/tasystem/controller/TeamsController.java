package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.ntnu.idatt1002.k204.tasystem.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for handling teams view
 */
public class TeamsController implements Initializable {

    @FXML
    private Button addTeamBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TableColumn<?, ?> playersCol;

    @FXML
    private Button teamsBtn;

    @FXML
    private TableColumn<?, ?> teamsCol;

    @FXML
    private TableView<?> teamsTableView;

    @FXML
    private Button tournamentsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        // ADD ME
        // - 1. Connect table cells/columns to team fields
        // - 2. Get teams from database to display
        //      Needs:
        //          - Teams register
        //          - Observable list
    }

    /**
     * Handle add team event.
     *
     * Add new team
     */
    @FXML
    void addTeamBtnClicked() {
        //TODO
        // ADD ME
        // - 1. Change scene to Add new team view

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

    /**
     * Handle tournaments button clicked event.
     *
     * Change scene back to front page
     */
    @FXML
    void tournamentsBtnClicked() {
        try {
            Application.changeScene("frontPageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
