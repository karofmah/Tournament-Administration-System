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
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;

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
    private Button teamsBtn;

    @FXML
    private TableColumn<?, ?> teamsCol;

    @FXML
    private TableView<Team> teamsTableView;

    @FXML
    private Button tournamentsBtn;

    private TeamRegister teamRegister;

    private TeamDAO teamDAO;

    private ObservableList<Team> teamObservableList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teamsCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));

        this.teamRegister = new TeamRegister();
        this.teamDAO = new TeamDAO();

        this.teamDAO.getTeam(this.teamRegister);
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamsTableView.setItems(this.teamObservableList);

    }

    /**
     * Handle add team event.
     *
     * Add new team
     */
    @FXML
    void addTeamBtnClicked() {
        try {
            Application.changeScene("addTeamView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
