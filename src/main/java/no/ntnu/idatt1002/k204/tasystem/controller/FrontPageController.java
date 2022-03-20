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
 * Controller for handling front page events
 *
 * Starts default by displaying tournaments
 * and handling tournaments events.
 */
public class FrontPageController implements Initializable {

    @FXML
    private Button addTournamentBtn;

    @FXML
    private TableColumn<?, ?> dateTimeCol;

    @FXML
    private TableColumn<?, ?> requirementsCol;

    @FXML
    private TableColumn<?, ?> stageCol;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private Button teamsBtn;

    @FXML
    private TableColumn<?, ?> tournamentNameCol;

    @FXML
    private Button tournamentsBtn;

    @FXML
    private TableView<?> tournamentsTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        // ADD ME
        // - 1. Connect table cells/columns to tournament fields
        // - 2. Get tournaments from database to display
        //      Needs:
        //          - Tournaments register
        //          - Observable list
    }

    /**
     * Handle adding tournament event
     */
    @FXML
    void addTournamentClicked() {
        //TODO
        // ADD ME: Start stage or change scene for adding new tournament
    }

    /**
     * Handle logout event.
     *
     * Logout and send back to log in screen
     */
    @FXML
    void logOutBtnClicked() {
        try {
            Application.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle teams button clicked event.
     *
     * Change scene to view teams
     */
    @FXML
    void teamsBtnClicked() {
        try {
            Application.changeScene("teamsView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}