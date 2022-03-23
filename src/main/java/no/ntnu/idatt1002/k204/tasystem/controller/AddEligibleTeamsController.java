package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEligibleTeamsController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn <?,?> teamNameCol;

    @FXML
    private TableColumn<?, ?> lowRankCol;

    @FXML
    private TableColumn<?, ?> playersCol;

    @FXML
    private TableView<Team> teamTableView;

    private TeamRegister teamRegister;

    private ObservableList<Team> teamObservableList;

    //private TeamDAO teamDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teamNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.playersCol.setCellValueFactory(new PropertyValueFactory<>("players"));
        this.lowRankCol.setCellValueFactory(new PropertyValueFactory<>("lowRank"));

        this.teamRegister = new TeamRegister();
        //this.teamDAO = new TeamDAO();

        //this.teamDAO.getTeam(this.teamRegister);
        this.teamObservableList = FXCollections.observableArrayList(this.teamRegister.getTeams());
        this.teamTableView.setItems(this.teamObservableList);
    }

    private void handleTournamentSelection() {
        teamTableView.setRowFactory(table -> {
            TableRow<Team> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Team team = row.getItem();
                if (row.isHover() && team != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        teamTableView.setCursor(Cursor.HAND);//Change courser
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event
                            /*Add team to tournament */
                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        teamTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });
    }
    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked(ActionEvent Event) {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
