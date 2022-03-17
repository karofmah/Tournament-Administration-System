package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

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
    public void initialize(URL url, ResourceBundle resourceBundle){
        //TODO
        // ADD ME
        // - 1. Connect table cells/columns to tournament fields
        // - 2. Get tournaments from database to display
        //      Needs:
        //          - Tournaments register
        //          - Observable list
    }

    @FXML
    void addTournamentClicked(ActionEvent event) {
        //TODO
        // ADD ME: Start stage or change scene for adding new tournament
    }

    @FXML
    void logOutBtnClicked(ActionEvent event) {
        //TODO
        // ADD ME: Send back to login screen
    }

    @FXML
    void teamsBtnClicked(ActionEvent event) {
        //TODO
        // ADD ME: Start stage or change scene for teams
    }

}