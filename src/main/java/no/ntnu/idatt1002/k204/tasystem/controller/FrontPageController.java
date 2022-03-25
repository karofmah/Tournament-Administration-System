package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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

    //public static int selectedTournamentID;

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

        handleTournamentSelection();
    }

    /**
     * Change scene when a tournament is selected.
     *
     * Listen on hovered row in table and check if there is a tournament
     * If there is a tournament allow clicking and change scene.
     *
     * Also changes cursor type to try and communicate with user that current row
     * can be clicked.
     *
     */

    private void handleTournamentSelection() {
        tournamentsTableView.setRowFactory(table -> {
            TableRow<Tournament> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                 Tournament tournament = row.getItem();
                if (row.isHover() && tournament != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        tournamentsTableView.setCursor(Cursor.HAND);//Change courser
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event
                            try {
                                Tournament.setSelectedTournamentID(tournament.getTournamentId());

                                //TODO: remove this:
                                changeToSelectedTournamentView(tournament);//Change scene

                                // TODO: implement the new way of changing to the SelectedTournament scene
                                //Application.changeScene("selectedTournamentView.fxml");//Change scene
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        tournamentsTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });
    }

    private void changeToSelectedTournamentView(Tournament tournament) throws IOException {
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