package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private Button startTournamentBtn;

    @FXML
    private TableColumn<?, ?> teamNameCol;

    @FXML
    private Button teamsBtn;

    @FXML
    private TableView<Team> teamsTableView;

    @FXML
    private Text selectedTText;

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

    }

    /**
     * Change to add eligible team scene and start adding teams
     */

    public void initData(Tournament tournament) {
        selectedTournament = tournament;
        //System.out.println(selectedTournament);
        selectedTText.setText(tournament.getName());
        if (selectedTournament != null) {
            for (Team team : selectedTournament.getTeams()) {
                //System.out.println("KOKO");
                this.teamRegister.addTeam(team);
            }
        }
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamsTableView.setItems(this.teamObservableList);
    }

    @FXML
    void addEligibleTeamBtnClicked() {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/addEligibleTeams.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            AddEligibleTeamsController controller = loader.getController();
            //System.out.println(selectedTournament);
            controller.initData(selectedTournament);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
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
        //TODO
        // ADD ME Change to group stage scene
    }

    /**
     * Change to knockout stage scene
     */
    @FXML
    void knockoutStageBtnClicked() {
        //TODO
        // ADD ME Change to knockout stage scene
    }

    /**
     * Currently added this so teams that are participating in a tournament can be added to database.
     */
    @FXML
    void startTournament() {
        for (Team team : selectedTournament.getTeams()) {
            tournamentDAO.addTournamentAndTeams(selectedTournament.getTournamentId(), team.getTeamName());
        }
    }
}
