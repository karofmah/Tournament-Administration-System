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
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

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
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> requirementsCol;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private Button teamsBtn;

    @FXML
    private TableColumn<?, ?> timeCol;

    @FXML
    private TableColumn<?, ?> tournamentNameCol;

    @FXML
    private Button tournamentsBtn;

    @FXML
    private TableView<Tournament> tournamentsTableView;

    private TournamentRegister tournamentRegister;

    private TournamentDAO tournamentDAO;

    private ObservableList<Tournament> tournamentObservableList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tournamentNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        this.requirementsCol.setCellValueFactory(new PropertyValueFactory<>("rankRequirement"));
        this.dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        this.tournamentRegister = new TournamentRegister();
        this.tournamentDAO = new TournamentDAO();

        this.tournamentDAO.getTournament(this.tournamentRegister);
        this.tournamentObservableList = FXCollections.observableArrayList(this.tournamentRegister.getTournaments());
        this.tournamentsTableView.setItems(this.tournamentObservableList);

    }

    /**
     * Handle adding tournament event
     */
    @FXML
    void addTournamentClicked() {
        try {
            Application.changeScene("addTournamentView.fxml");
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