package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for handling events when tournament is selected
 */
public class SelectedTournamentController implements Initializable {

    @FXML
    private Button addEligibleTeamBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> countPlayersCol;

    @FXML
    private Button groupStageBtn;

    @FXML
    private Button knockoutStageBtn;

    @FXML
    private Button saveTournamentBtn;

    @FXML
    private TableColumn<?, ?> teamNameCol;

    @FXML
    private Button teamsBtn;

    @FXML
    private Button editTournamentBtn;

    @FXML
    private Button startTournamentButton;

    @FXML
    private TableView<Team> teamsTableView;

    @FXML
    private Label selectedText;

    private TeamRegister teamRegister;

    private ObservableList<Team> teamObservableList;

    private Tournament selectedTournament;

    private TournamentDAO tournamentDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.teamNameCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        this.teamRegister = new TeamRegister();
        this.tournamentDAO = new TournamentDAO();

        this.teamRegister = tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID());
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamsTableView.setItems(this.teamObservableList);

        selectedText.setText(this.tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getName());
        /**
         * Disables buttons based on tournament status
         */
        String tournamentStatus = tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getStatus();
        knockoutStageBtn.setDisable(true);
        groupStageBtn.setDisable(true);
        if(tournamentStatus.equals("Groupstage") || tournamentStatus.equals("Knockoutstage") || tournamentStatus.equals("Group-KnockoutStage") || tournamentStatus.equals("Finished")){
            startTournamentButton.setDisable(true);
            editTournamentBtn.setDisable(true);
            saveTournamentBtn.setDisable(true);
            addEligibleTeamBtn.setDisable(true);
        }
        if(tournamentStatus.equals("Knockoutstage")){
            knockoutStageBtn.setDisable(false);
        }
        if(tournamentStatus.equals("Group-Knockoutstage") || tournamentStatus.equals("Groupstage")){
            groupStageBtn.setDisable(false);
        }
        if(tournamentStatus.equals("Finished")){
            groupStageBtn.setDisable(false);
            knockoutStageBtn.setDisable(false);
        }

    }

    public void initData(Tournament tournament) {
        selectedTournament = tournament;
        if (selectedTournament != null) {
            for (Team team : selectedTournament.getTeams()) {
                this.teamRegister.addTeam(team);
            }
        }
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamsTableView.setItems(this.teamObservableList);
    }

    @FXML
    void editTournamentBtnClicked() {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/EditTournamentView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            EditTournamentController controller = loader.getController();
            controller.initData(selectedTournament);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Change to add eligible team scene and start adding teams
     */
    @FXML
    void addEligibleTeamBtnClicked() {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/addEligibleTeamsView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            AddEligibleTeamsController controller = loader.getController();
            controller.initData(selectedTournament);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void startTournamentButtonClicked(){
        try{
            int numberOfTeams = tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID()).getTeams().size();
            if(numberOfTeams>=8 && numberOfTeams<=12) {
                editTournamentBtn.setDisable(true);
                saveTournamentBtn.setDisable(true);
                addEligibleTeamBtn.setDisable(true);
                if (numberOfTeams > 8 && numberOfTeams <= 12) {
                    tournamentDAO.updateTournamentStatus(Tournament.getSelectedTournamentID(), "Groupstage");
                    Application.changeScene("groupStageView.fxml");
                } else if (numberOfTeams == 8) {
                    tournamentDAO.updateTournamentStatus(Tournament.getSelectedTournamentID(), "Knockoutstage");
                    Application.changeScene("knockOutStageView.fxml");

                }
            }
            else {
                Dialogs.showInformationDialog("Number of teams has to be between 8 and 12. Currently: " + numberOfTeams);
            }


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Navigate back to previous scene
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
     * Change to knockout stage scene
     */
    @FXML
    void knockoutStageBtnClicked() {
        try {
            if (teamRegister.getTeams().size() >= 8) {
                Application.changeScene("knockOutStageView.fxml");
            } else {
                Dialogs.showInformationDialog("You need 8 teams to start the knockout stage.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Currently added this so teams that are participating in a tournament can be added to database.
     */
    @FXML
    void saveTournament() {
        try{
        for (Team team : selectedTournament.getTeams()) {
            tournamentDAO.addTournamentAndTeams(selectedTournament.getTournamentId(), team.getTeamName());
        }
            showInformationDialog("Changes in " + selectedTournament.getName() + " has been saved");
        }catch(IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    @FXML
    void deleteTournament() {
        tournamentDAO.deleteTournament(selectedTournament.getTournamentId());
        Dialogs.showInformationDialog(selectedTournament.getName() + " has been deleted");

        try {
            Application.changeScene("frontPageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
