package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import no.ntnu.idatt1002.k204.tasystem.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KnockoutStageController implements Initializable {

    @FXML
    private Text team1;

    @FXML
    private Text team2;

    @FXML
    private Text team3;

    @FXML
    private Text team4;

    @FXML
    private Text team5;

    @FXML
    private Text team6;

    @FXML
    private Text team7;

    @FXML
    private Text team8;

    @FXML
    private Text semiFinalTeam1;

    @FXML
    private Text semiFinalTeam2;

    @FXML
    private Text semiFinalTeam3;

    @FXML
    private Text semiFinalTeam4;

    @FXML
    private Text finalTeam1;

    @FXML
    private Text finalTeam2;

    @FXML
    private Text winnerTeam;

    @FXML
    private RadioButton team1Btn;

    @FXML
    private RadioButton team2Btn;

    @FXML
    private RadioButton team3Btn;

    @FXML
    private RadioButton team4Btn;

    @FXML
    private RadioButton team5Btn;

    @FXML
    private RadioButton team6Btn;

    @FXML
    private RadioButton team7Btn;

    @FXML
    private RadioButton team8Btn;

    @FXML
    private RadioButton semiFinalGroup1Team1Btn;

    @FXML
    private RadioButton semiFinalGroup1Team2Btn;

    @FXML
    private RadioButton semiFinalGroup2Team1Btn;

    @FXML
    private RadioButton semiFinalGroup2Team2Btn;

    @FXML
    private RadioButton finalTeam1Btn;

    @FXML
    private RadioButton finalTeam2Btn;

    private String quarterFinalist1 = "Team 1";
    private String quarterFinalist2 = "Team 2";
    private String quarterFinalist3 = "Team 3";
    private String quarterFinalist4 = "Team 4";
    private String quarterFinalist5 = "Team 5";
    private String quarterFinalist6 = "Team 6";
    private String quarterFinalist7 = "Team 7";
    private String quarterFinalist8 = "Team 8";
    private String semiFinalist1;
    private String semiFinalist2;
    private String semiFinalist3;
    private String semiFinalist4;
    private String finalist1;
    private String finalist2;
    private String winner;


    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            // TODO: Change scene to the front page instead?
            Application.changeScene("frontPageView.fxml");
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

    /**
     * Navigates back to the teams-page
     */
    @FXML
    void teamsBtnClicked() {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finishes the tournament
     */
    @FXML
    void finishTournamentBtnClicked() {
        try {
            Application.changeScene("frontPageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the knockout stage
     * Sets the correct text in the scene and listens for radio button events to progress.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        team1.setText(quarterFinalist1);
        team2.setText(quarterFinalist2);
        team3.setText(quarterFinalist3);
        team4.setText(quarterFinalist4);
        team5.setText(quarterFinalist5);
        team6.setText(quarterFinalist6);
        team7.setText(quarterFinalist7);
        team8.setText(quarterFinalist8);

        team1Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist1,1));
        team2Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist2,1));
        team3Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist3,2));
        team4Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist4,2));
        team5Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist5,3));
        team6Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist6,3));
        team7Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist7,4));
        team8Btn.setOnAction((ActionEvent event) -> setSemiFinalist(quarterFinalist8,4));

        semiFinalGroup1Team1Btn.setOnAction((ActionEvent event) -> setFinalist(semiFinalist1,1));
        semiFinalGroup1Team2Btn.setOnAction((ActionEvent event) -> setFinalist(semiFinalist2,1));
        semiFinalGroup2Team1Btn.setOnAction((ActionEvent event) -> setFinalist(semiFinalist3,2));
        semiFinalGroup2Team2Btn.setOnAction((ActionEvent event) -> setFinalist(semiFinalist4,2));

        finalTeam1Btn.setOnAction((ActionEvent event) -> setWinner(finalist1));
        finalTeam2Btn.setOnAction((ActionEvent event) -> setWinner(finalist2));

    }

    /**
     * Sets a winner of the tournament
     * @param finalWinner the winner of the final
     */
    public void setWinner(String finalWinner) {
        winner = finalWinner;
        winnerTeam.setText(winner);
    }

    /**
     * Sets a finalist
     * @param semiFinalWinner the winner of the semi-final in question
     * @param finalistNr the finalist "destination" (same as semi-final match)
     */
    public void setFinalist(String semiFinalWinner, int finalistNr) {
        if (finalistNr == 1) {
            finalist1 = semiFinalWinner;
            finalTeam1.setText(finalist1);
            finalTeam1Btn.setDisable(false);
        } else {
            finalist2 = semiFinalWinner;
            finalTeam2.setText(finalist2);
            finalTeam2Btn.setDisable(false);

        }
    }

    /**
     * Sets a semi-finalist
     * @param quarterFinalWinner the winner of the quarter-final in question
     * @param semiFinalistNr the semi-finalist "destination" (same as quarter-final match)
     */
    public void setSemiFinalist(String quarterFinalWinner, int semiFinalistNr) {
        switch (semiFinalistNr) {
            case 1:
                semiFinalist1 = quarterFinalWinner;
                semiFinalTeam1.setText(quarterFinalWinner);
                semiFinalGroup1Team1Btn.setDisable(false);
                break;
            case 2:
                semiFinalist2 = quarterFinalWinner;
                semiFinalTeam2.setText(quarterFinalWinner);
                semiFinalGroup1Team2Btn.setDisable(false);
                break;
            case 3:
                semiFinalist3 = quarterFinalWinner;
                semiFinalTeam3.setText(quarterFinalWinner);
                semiFinalGroup2Team1Btn.setDisable(false);
                break;
            case 4:
                semiFinalist4 = quarterFinalWinner;
                semiFinalTeam4.setText(quarterFinalWinner);
                semiFinalGroup2Team2Btn.setDisable(false);
                break;
        }
    }
}

