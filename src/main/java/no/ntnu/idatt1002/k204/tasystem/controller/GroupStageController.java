package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for group stage
 */
public class GroupStageController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button groupStageBtn;

    @FXML
    private Button knockoutStageBtn;

    @FXML
    private Button startTournamentBtn;

    @FXML
    private TableColumn<?, ?> t1PointsCol;

    @FXML
    private TableColumn t1RankCol;

    @FXML
    private TableColumn<?, ?> t1TeamCol;

    @FXML
    private TableColumn<?, ?> t2PointsCol;

    @FXML
    private TableColumn<?, ?> t2RankCol;

    @FXML
    private TableColumn<?, ?> t2TeamCol;

    @FXML
    private TableColumn<?, ?> t3PointsCol;

    @FXML
    private TableColumn<?, ?> t3RankCol;

    @FXML
    private TableColumn<?, ?> t3TeamCol;

    @FXML
    private TableColumn<?, ?> t4PointsCol;

    @FXML
    private TableColumn<?, ?> t4RankCol;

    @FXML
    private TableColumn<?, ?> t4TeamCol;

    @FXML
    private TableView<TeamRegister> tableView1;

    @FXML
    private TableView<TeamRegister> tableView2;

    @FXML
    private TableView<TeamRegister> tableView3;

    @FXML
    private TableView<TeamRegister> tableView4;

    @FXML
    private Button teamsBtn;

    private ObservableList<TeamRegister> teamObservableList1;
    private ObservableList<TeamRegister> teamObservableList2;

    private TeamRegister teamRegister1;
    private TeamRegister teamRegister2;

    TournamentDAO tournamentDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t1TeamCol.setCellValueFactory(new PropertyValueFactory<>("teamComboBox"));
        t2TeamCol.setCellValueFactory(new PropertyValueFactory<>("teamComboBox"));

        this.teamRegister1 = new TeamRegister(FXCollections.observableArrayList());
        this.teamRegister2 = new TeamRegister(FXCollections.observableArrayList());

        this.tournamentDAO = new TournamentDAO();
        for (Team team : this.tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID()).getTeams()) {
            this.teamRegister1.addTeamToObservableList(team);
            this.teamRegister2.addTeamToObservableList(team);
        }

        this.teamObservableList1 = FXCollections.observableArrayList(new TeamRegister(teamRegister1.getTeamObservableList()));
        this.teamObservableList2 = FXCollections.observableArrayList(new TeamRegister(teamRegister2.getTeamObservableList()));

        this.tableView1.setItems(this.teamObservableList1);
        this.tableView2.setItems(this.teamObservableList2);
    }

    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle events when knockout stage button is clicked
     */
    @FXML
    void knockoutStageBtnClicked() {

    }

    /**
     * Start tournament
     */
    @FXML
    void startTournamentBtnClicked() {
        //TODO
        // ADD ME:
        // -1. Add buttons to change points (if possible)
        // -2. Lock combobox with team names (if possible)

    }
    @FXML
    void teamsBtnClicked() {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
