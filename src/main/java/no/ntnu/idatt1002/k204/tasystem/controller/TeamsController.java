package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

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

        handleTeamSelection();
    }

    /**
     * Change scene when a team is selected.
     *
     * Listen on hovered row in table and check if there is a team
     * If there is a team allow clicking and change scene.
     *
     * Also changes cursor type to try and communicate with user that current row
     * can be clicked.
     *
     */

    private void handleTeamSelection() {
        teamsTableView.setRowFactory(table -> {
            TableRow<Team> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Team team = row.getItem();
                if (row.isHover() && team != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        teamsTableView.setCursor(Cursor.HAND);//Change courser
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event
                            try {
                                changeToSelectedTeamView(team); //Change scene
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        teamsTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });
    }

    private void changeToSelectedTeamView(Team team) throws IOException {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/selectedTeamView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SelectedTeamController controller = loader.getController();
            controller.initData(team);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
    void logOutBtnClicked() {
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
