package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.ntnu.idatt1002.k204.tasystem.Application;

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
    private TableColumn<?, ?> t1RankCol;

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
    private TableView<?> tableView1;

    @FXML
    private TableView<?> tableView2;

    @FXML
    private TableView<?> tableView3;

    @FXML
    private TableView<?> tableView4;

    @FXML
    private Button teamsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
        // ADD ME
        // - 1. Connect table team cell to fields
        // - 2. Get teams from database to display in team col
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

}
