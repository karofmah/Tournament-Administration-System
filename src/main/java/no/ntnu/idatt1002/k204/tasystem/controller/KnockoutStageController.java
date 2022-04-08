package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class KnockoutStageController implements Initializable {

    @FXML
    private Label selectedText;

    @FXML
    private Text quarterFinalTeam1Txt;

    @FXML
    private Text quarterFinalTeam2Txt;

    @FXML
    private Text quarterFinalTeam3Txt;

    @FXML
    private Text quarterFinalTeam4Txt;

    @FXML
    private Text quarterFinalTeam5Txt;

    @FXML
    private Text quarterFinalTeam6Txt;

    @FXML
    private Text quarterFinalTeam7Txt;

    @FXML
    private Text quarterFinalTeam8Txt;

    @FXML
    private Text semiFinalTeam1Txt;

    @FXML
    private Text semiFinalTeam2Txt;

    @FXML
    private Text semiFinalTeam3Txt;

    @FXML
    private Text semiFinalTeam4Txt;

    @FXML
    private Text finalTeam1Txt;

    @FXML
    private Text finalTeam2Txt;

    @FXML
    private Text winnerTeamTxt;

    @FXML
    private RadioButton quarterFinalTeam1Btn;

    @FXML
    private RadioButton quarterFinalTeam2Btn;

    @FXML
    private RadioButton quarterFinalTeam3Btn;

    @FXML
    private RadioButton quarterFinalTeam4Btn;

    @FXML
    private RadioButton quarterFinalTeam5Btn;

    @FXML
    private RadioButton quarterFinalTeam6Btn;

    @FXML
    private RadioButton quarterFinalTeam7Btn;

    @FXML
    private RadioButton quarterFinalTeam8Btn;

    @FXML
    private RadioButton semiFinalTeam1Btn;

    @FXML
    private RadioButton semiFinalTeam2Btn;

    @FXML
    private RadioButton semiFinalTeam3Btn;

    @FXML
    private RadioButton semiFinalTeam4Btn;

    @FXML
    private RadioButton finalTeam1Btn;

    @FXML
    private RadioButton finalTeam2Btn;

    @FXML
    private Button finishTournamentBtn;

    @FXML
    private Text tournamentWinnerTxt;

    private Team quarterFinalist1 = new Team("Fnatic");
    private Team quarterFinalist2 = new Team("Ninjas in Pyjamas");
    private Team quarterFinalist3 = new Team("NAVI");
    private Team quarterFinalist4 = new Team("G2");
    private Team quarterFinalist5 = new Team("Team Liquid");
    private Team quarterFinalist6 = new Team("Cloud9");
    private Team quarterFinalist7 = new Team("Astralis");
    private Team quarterFinalist8 = new Team("Faze");
    private Team semiFinalist1;
    private Team semiFinalist2;
    private Team semiFinalist3;
    private Team semiFinalist4;
    private Team finalist1;
    private Team finalist2;
    private Team winner;

    private ArrayList<Team> quarterFinalists = new ArrayList<>();

    private TeamRegister teamRegister;
    private TournamentDAO tournamentDAO;

    /**
     * Initializes the knockout stage
     * Sets the correct text in the scene and listens for radio button events to progress.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.teamRegister = new TeamRegister();
        this.tournamentDAO = new TournamentDAO();

        this.teamRegister = tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID());

        selectedText.setText(this.tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getName());

        ArrayList<Team> sortedList = new ArrayList<>(teamRegister.getTeams());

        sortedList.sort(Comparator.comparing(Team::getPointsAsInt));


        for (int i = sortedList.size()-1; i > sortedList.size()-9; i--) {
            quarterFinalists.add(sortedList.get(i));
        }

        quarterFinalist1 = quarterFinalists.get(0);
        quarterFinalist2 = quarterFinalists.get(1);
        quarterFinalist3 = quarterFinalists.get(2);
        quarterFinalist4 = quarterFinalists.get(3);
        quarterFinalist5 = quarterFinalists.get(4);
        quarterFinalist6 = quarterFinalists.get(5);
        quarterFinalist7 = quarterFinalists.get(6);
        quarterFinalist8 = quarterFinalists.get(7);



        System.out.println(quarterFinalist1.getPoints());
        System.out.println(quarterFinalist1.getTeamName());


        setTextToTeamName(quarterFinalTeam1Txt, quarterFinalist1);
        setTextToTeamName(quarterFinalTeam2Txt, quarterFinalist2);
        setTextToTeamName(quarterFinalTeam3Txt, quarterFinalist3);
        setTextToTeamName(quarterFinalTeam4Txt, quarterFinalist4);
        setTextToTeamName(quarterFinalTeam5Txt, quarterFinalist5);
        setTextToTeamName(quarterFinalTeam6Txt, quarterFinalist6);
        setTextToTeamName(quarterFinalTeam7Txt, quarterFinalist7);
        setTextToTeamName(quarterFinalTeam8Txt, quarterFinalist8);

        quarterFinalTeam1Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist1,1));
        quarterFinalTeam2Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist2,1));
        quarterFinalTeam3Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist3,2));
        quarterFinalTeam4Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist4,2));
        quarterFinalTeam5Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist5,3));
        quarterFinalTeam6Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist6,3));
        quarterFinalTeam7Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist7,4));
        quarterFinalTeam8Btn.setOnAction((ActionEvent event) -> quarterFinalBtnClicked(quarterFinalist8,4));

        semiFinalTeam1Btn.setOnAction((ActionEvent event) -> semiFinalBtnClicked(semiFinalist1,1));
        semiFinalTeam2Btn.setOnAction((ActionEvent event) -> semiFinalBtnClicked(semiFinalist2,1));
        semiFinalTeam3Btn.setOnAction((ActionEvent event) -> semiFinalBtnClicked(semiFinalist3,2));
        semiFinalTeam4Btn.setOnAction((ActionEvent event) -> semiFinalBtnClicked(semiFinalist4,2));

        finalTeam1Btn.setOnAction((ActionEvent event) -> finalBtnClicked(finalist1));
        finalTeam2Btn.setOnAction((ActionEvent event) -> finalBtnClicked(finalist2));
    }

    /**
     * Decides what happens when either of the quarter-final buttons are clicked
     * @param quarterFinalWinner the winner of the quarter-final
     * @param semiFinalistNr the semi-finalist "destination" (same as quarter-final match number)
     */
    public void quarterFinalBtnClicked(Team quarterFinalWinner, int semiFinalistNr) {
        switch (semiFinalistNr) {
            case 1:
                semiFinalist1 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam1Txt, quarterFinalWinner);
                break;
            case 2:
                semiFinalist2 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam2Txt, quarterFinalWinner);
                break;
            case 3:
                semiFinalist3 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam3Txt, quarterFinalWinner);
                break;
            case 4:
                semiFinalist4 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam4Txt, quarterFinalWinner);
                break;
        }

        if (semiFinalist1 != null && semiFinalist2 != null) {
            semiFinalTeam1Btn.setDisable(false);
            semiFinalTeam2Btn.setDisable(false);
        }
        if (semiFinalist3 != null && semiFinalist4 != null) {
            semiFinalTeam3Btn.setDisable(false);
            semiFinalTeam4Btn.setDisable(false);
        }
    }

    /**
     * Decides what happens when either of the semi-final buttons are clicked
     * @param semiFinalWinner the winner of the semi-final
     * @param finalistNr the finalist "destination" (same as semi-final match)
     */
    public void semiFinalBtnClicked(Team semiFinalWinner, int finalistNr) {
        if (finalistNr == 1) {
            finalist1 = semiFinalWinner;
            setTextToTeamName(finalTeam1Txt, finalist1);
            quarterFinalTeam1Btn.setDisable(true);
            quarterFinalTeam2Btn.setDisable(true);
            quarterFinalTeam3Btn.setDisable(true);
            quarterFinalTeam4Btn.setDisable(true);
        } else {
            finalist2 = semiFinalWinner;
            setTextToTeamName(finalTeam2Txt, finalist2);
            quarterFinalTeam5Btn.setDisable(true);
            quarterFinalTeam6Btn.setDisable(true);
            quarterFinalTeam7Btn.setDisable(true);
            quarterFinalTeam8Btn.setDisable(true);
        }

        if (finalist1 != null && finalist2 != null) {
            finalTeam1Btn.setDisable(false);
            finalTeam2Btn.setDisable(false);
        }

    }

    /**
     * Sets a winner of the tournament
     * @param finalWinner the winner of the final
     */
    public void finalBtnClicked(Team finalWinner) {
        winner = finalWinner;
        setTextToTeamName(winnerTeamTxt, winner);
        semiFinalTeam1Btn.setDisable(true);
        semiFinalTeam2Btn.setDisable(true);
        semiFinalTeam3Btn.setDisable(true);
        semiFinalTeam4Btn.setDisable(true);
        finishTournamentBtn.setDisable(false);
    }

    /**
     * Method used to quickly set a team's name to a Text field in the application view.
     * @param text the text field where the team name will be displayed
     * @param team the team to be displayed
     */
    private void setTextToTeamName(Text text, Team team) {
        text.setText(team.getTeamName());
    }

    @FXML
    void logOutBtnClicked() {
        try {
            Application.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finishes the tournament
     */
    @FXML
    void finishTournamentBtnClicked() {
        tournamentWinnerTxt.setVisible(true);
        finalTeam1Btn.setDisable(true);
        finalTeam2Btn.setDisable(true);
        finishTournamentBtn.setDisable(true);
    }

    /**
     * Navigate back to the front page
     */
    @FXML
    void backBtnClicked() {
        try {
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
}


