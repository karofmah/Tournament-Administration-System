package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import no.ntnu.idatt1002.k204.tasystem.Application;

import java.io.IOException;

public class KnockoutStageController {

    @FXML
    private TextFlow team1;

    @FXML
    private TextFlow team2;

    @FXML
    private TextFlow team3;

    @FXML
    private TextFlow team4;

    @FXML
    private TextFlow team5;

    @FXML
    private TextFlow team6;

    @FXML
    private TextFlow team7;

    @FXML
    private TextFlow team8;

    @FXML
    private TextFlow quarterFinalTeam1;

    @FXML
    private TextFlow quarterFinalTeam2;

    @FXML
    private TextFlow quarterFinalTeam3;

    @FXML
    private TextFlow quarterFinalTeam4;

    @FXML
    private TextFlow semiFinalTeam1;

    @FXML
    private TextFlow semiFinalTeam2;

    @FXML
    private TextFlow winnerTeam;


    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            // TODO: Change scene to the front page instead?
            Application.changeScene("groupStageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle events when knockout stage button is clicked
     */
    @FXML
    void knockoutStageBtnClicked() {
        try {
            Application.changeScene("knockOutStageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change to group stage scene
     */
    @FXML
    void groupStageBtnClicked() {
        try {
            Application.changeScene("groupStageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void teamsBtnClicked() {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void finishTournamentBtnClicked() {
        try {
            Application.changeScene("frontPageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

