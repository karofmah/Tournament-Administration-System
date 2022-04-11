package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs;
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
    private Button saveBtn;

    @FXML
    private Text tournamentWinnerTxt;


    private Team quarterFinalist1;
    private Team quarterFinalist2;
    private Team quarterFinalist3;
    private Team quarterFinalist4;
    private Team quarterFinalist5;
    private Team quarterFinalist6;
    private Team quarterFinalist7;
    private Team quarterFinalist8;
    private Team semiFinalist1;
    private Team semiFinalist2;
    private Team semiFinalist3;
    private Team semiFinalist4;
    private Team finalist1;
    private Team finalist2;
    private Team tournamentWinner;

    private Team quarterFinalWinner1;
    private Team quarterFinalWinner2;
    private Team quarterFinalWinner3;
    private Team quarterFinalWinner4;
    private Team semiFinalWinner1;
    private Team semiFinalWinner2;

    /**
     * List of all teams participating in the knockout stage
     * Each row represents a match, with two teams and the winner as columns
     */
    private Team[][] matches = {
            {quarterFinalist1, quarterFinalist2, quarterFinalWinner1},
            {quarterFinalist3, quarterFinalist4, quarterFinalWinner2},
            {quarterFinalist5, quarterFinalist6, quarterFinalWinner3},
            {quarterFinalist7, quarterFinalist8, quarterFinalWinner4},
            {semiFinalist1, semiFinalist2, semiFinalWinner1},
            {semiFinalist2, semiFinalist4, semiFinalWinner2},
            {finalist1, finalist2, tournamentWinner},
    };

    private TeamRegister teamRegister;
    private TournamentDAO tournamentDAO;
    private Tournament tournament;

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
        this.tournament = tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID());

        selectedText.setText(this.tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getName());

        initializeMatches();

        initializeBrackets();

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


    private void initializeBrackets() {
        setTextToTeamName(quarterFinalTeam1Txt, quarterFinalist1);
        setTextToTeamName(quarterFinalTeam2Txt, quarterFinalist2);
        setTextToTeamName(quarterFinalTeam3Txt, quarterFinalist3);
        setTextToTeamName(quarterFinalTeam4Txt, quarterFinalist4);
        setTextToTeamName(quarterFinalTeam5Txt, quarterFinalist5);
        setTextToTeamName(quarterFinalTeam6Txt, quarterFinalist6);
        setTextToTeamName(quarterFinalTeam7Txt, quarterFinalist7);
        setTextToTeamName(quarterFinalTeam8Txt, quarterFinalist8);
        setTextToTeamName(semiFinalTeam1Txt, semiFinalist1);
        setTextToTeamName(semiFinalTeam2Txt, semiFinalist2);
        setTextToTeamName(semiFinalTeam3Txt, semiFinalist3);
        setTextToTeamName(semiFinalTeam4Txt, semiFinalist4);
        setTextToTeamName(finalTeam1Txt, finalist1);
        setTextToTeamName(finalTeam2Txt, finalist2);
        setTextToTeamName(winnerTeamTxt, tournamentWinner);

        quarterFinalTeam1Btn.setSelected(quarterFinalTeam1Txt.getText().equals(semiFinalTeam1Txt.getText()) && !semiFinalTeam1Txt.getText().equals(""));
        quarterFinalTeam2Btn.setSelected(quarterFinalTeam2Txt.getText().equals(semiFinalTeam1Txt.getText()) && !semiFinalTeam1Txt.getText().equals(""));
        quarterFinalTeam3Btn.setSelected(quarterFinalTeam3Txt.getText().equals(semiFinalTeam2Txt.getText()) && !semiFinalTeam2Txt.getText().equals(""));
        quarterFinalTeam4Btn.setSelected(quarterFinalTeam4Txt.getText().equals(semiFinalTeam2Txt.getText()) && !semiFinalTeam2Txt.getText().equals(""));
        quarterFinalTeam5Btn.setSelected(quarterFinalTeam5Txt.getText().equals(semiFinalTeam3Txt.getText()) && !semiFinalTeam3Txt.getText().equals(""));
        quarterFinalTeam6Btn.setSelected(quarterFinalTeam6Txt.getText().equals(semiFinalTeam3Txt.getText()) && !semiFinalTeam3Txt.getText().equals(""));
        quarterFinalTeam7Btn.setSelected(quarterFinalTeam7Txt.getText().equals(semiFinalTeam4Txt.getText()) && !semiFinalTeam4Txt.getText().equals(""));
        quarterFinalTeam8Btn.setSelected(quarterFinalTeam8Txt.getText().equals(semiFinalTeam4Txt.getText()) && !semiFinalTeam4Txt.getText().equals(""));

        semiFinalTeam1Btn.setSelected(semiFinalTeam1Txt.getText().equals(finalTeam1Txt.getText()) && !finalTeam1Txt.getText().equals(""));
        semiFinalTeam2Btn.setSelected(semiFinalTeam2Txt.getText().equals(finalTeam1Txt.getText()) && !finalTeam1Txt.getText().equals(""));
        semiFinalTeam3Btn.setSelected(semiFinalTeam3Txt.getText().equals(finalTeam2Txt.getText()) && !finalTeam2Txt.getText().equals(""));
        semiFinalTeam4Btn.setSelected(semiFinalTeam4Txt.getText().equals(finalTeam2Txt.getText()) && !finalTeam2Txt.getText().equals(""));

        finalTeam1Btn.setSelected(finalTeam1Txt.getText().equals(winnerTeamTxt.getText()) && !winnerTeamTxt.getText().equals(""));
        finalTeam2Btn.setSelected(finalTeam2Txt.getText().equals(winnerTeamTxt.getText()) && !winnerTeamTxt.getText().equals(""));

        if (matches[4][2] == null) {
            quarterFinalTeam1Btn.setDisable(false);
            quarterFinalTeam2Btn.setDisable(false);
            quarterFinalTeam3Btn.setDisable(false);
            quarterFinalTeam4Btn.setDisable(false);
        }
        if (matches[5][2] == null) {
            quarterFinalTeam5Btn.setDisable(false);
            quarterFinalTeam6Btn.setDisable(false);
            quarterFinalTeam7Btn.setDisable(false);
            quarterFinalTeam8Btn.setDisable(false);
        }
        if (matches[6][2] == null) {
            if (matches[0][2] != null && matches[1][2] != null) {
                semiFinalTeam1Btn.setDisable(false);
                semiFinalTeam2Btn.setDisable(false);
            }
            if (matches[2][2] != null && matches[3][2] != null) {
                semiFinalTeam3Btn.setDisable(false);
                semiFinalTeam4Btn.setDisable(false);
            }
        }
        if (matches[4][2] != null && matches[5][2] != null && !tournament.getStatus().equals("Finished")) {
                finalTeam1Btn.setDisable(false);
                finalTeam2Btn.setDisable(false);
            }

        if (tournament.getStatus().equals("Finished")) {
            tournamentWinnerTxt.setVisible(true);
            finishTournamentBtn.setDisable(true);
            saveBtn.setDisable(true);
        }

        if ((finalTeam1Btn.isSelected() || finalTeam2Btn.isSelected()) && !tournament.getStatus().equals("Finished")) {
            finishTournamentBtn.setDisable(false);
        }
    }

    private void initializeMatches() {

        tournamentDAO.getKnockoutMatches(Tournament.getSelectedTournamentID(), matches);

        if (matches[0][1] == null){
            ArrayList<Team> sortedList = new ArrayList<>(teamRegister.getTeams());

            sortedList.sort(Comparator.comparing(Team::getPointsAsInt));

            ArrayList<Team> quarterFinalists = new ArrayList<>();

            for (int i = sortedList.size()-1; quarterFinalists.size() < 8; i--) {
                if (!sortedList.isEmpty()) {
                    quarterFinalists.add(sortedList.get(i));
                } else {
                    quarterFinalists.add(null);
                }
            }

            quarterFinalist1 = quarterFinalists.get(0);
            quarterFinalist2 = quarterFinalists.get(1);
            quarterFinalist3 = quarterFinalists.get(2);
            quarterFinalist4 = quarterFinalists.get(3);
            quarterFinalist5 = quarterFinalists.get(4);
            quarterFinalist6 = quarterFinalists.get(5);
            quarterFinalist7 = quarterFinalists.get(6);
            quarterFinalist8 = quarterFinalists.get(7);

            //saveBtnClicked();
        } else {
            quarterFinalist1 = matches[0][0];
            quarterFinalist2 = matches[0][1];
            quarterFinalist3 = matches[1][0];
            quarterFinalist4 = matches[1][1];
            quarterFinalist5 = matches[2][0];
            quarterFinalist6 = matches[2][1];
            quarterFinalist7 = matches[3][0];
            quarterFinalist8 = matches[3][1];
        }


        // Sets all participating teams to local variables, from the list/database.

        semiFinalist1 = matches[4][0];
        semiFinalist2 = matches[4][1];
        semiFinalist3 = matches[5][0];
        semiFinalist4 = matches[5][1];

        finalist1 = matches[6][0];
        finalist2 = matches[6][1];

        quarterFinalWinner1 = matches[0][2];
        quarterFinalWinner2 = matches[1][2];
        quarterFinalWinner3 = matches[2][2];
        quarterFinalWinner4 = matches[3][2];
        semiFinalWinner1 = matches[4][2];
        semiFinalWinner2 = matches[5][2];
        tournamentWinner = matches[6][2];



    }


    @FXML
    void saveBtnClicked() {
        matches[0][0] = quarterFinalist1;
        matches[0][1] = quarterFinalist2;
        matches[1][0] = quarterFinalist3;
        matches[1][1] = quarterFinalist4;
        matches[2][0] = quarterFinalist5;
        matches[2][1] = quarterFinalist6;
        matches[3][0] = quarterFinalist7;
        matches[3][1] = quarterFinalist8;

        matches[4][0] = semiFinalist1;
        matches[4][1] = semiFinalist2;
        matches[5][0] = semiFinalist3;
        matches[5][1] = semiFinalist4;

        matches[6][0] = finalist1;
        matches[6][1] = finalist2;

        matches[0][2] = quarterFinalWinner1;
        matches[1][2] = quarterFinalWinner2;
        matches[2][2] = quarterFinalWinner3;
        matches[3][2] = quarterFinalWinner4;
        matches[4][2] = semiFinalWinner1;
        matches[5][2] = semiFinalWinner2;
        matches[6][2] = tournamentWinner;

        tournamentDAO.updateKnockoutMatches(Tournament.getSelectedTournamentID(), matches);

        Dialogs.showInformationDialog("The knockout stage has been saved!");
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
                quarterFinalWinner1 = quarterFinalWinner;
                break;
            case 2:
                semiFinalist2 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam2Txt, quarterFinalWinner);
                quarterFinalWinner2 = quarterFinalWinner;
                break;
            case 3:
                semiFinalist3 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam3Txt, quarterFinalWinner);
                quarterFinalWinner3 = quarterFinalWinner;
                break;
            case 4:
                semiFinalist4 = quarterFinalWinner;
                setTextToTeamName(semiFinalTeam4Txt, quarterFinalWinner);
                quarterFinalWinner4 = quarterFinalWinner;
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
            semiFinalWinner1 = semiFinalWinner;
        } else {
            finalist2 = semiFinalWinner;
            setTextToTeamName(finalTeam2Txt, finalist2);
            quarterFinalTeam5Btn.setDisable(true);
            quarterFinalTeam6Btn.setDisable(true);
            quarterFinalTeam7Btn.setDisable(true);
            quarterFinalTeam8Btn.setDisable(true);
            semiFinalWinner2 = semiFinalWinner;
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
        tournamentWinner = finalWinner;
        setTextToTeamName(winnerTeamTxt, tournamentWinner);
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
        if (team != null) text.setText(team.getTeamName());
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
        saveBtn.setDisable(true);


        tournament.setStatus("Finished");
        tournamentDAO.updateTournamentStatus(tournament.getTournamentId(), tournament.getStatus());

        saveBtnClicked();
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
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/selectedTournamentView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SelectedTournamentController controller = loader.getController();
            controller.initData(tournament);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


