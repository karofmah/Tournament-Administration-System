package no.ntnu.idatt1002.k204.tasystem.controller;

/**
 * Sample Skeleton for 'selectedTeamView.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectedTeamController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="logOutBtn"
    private Button logOutBtn; // Value injected by FXMLLoader

    @FXML
    private Label selectedText;

    @FXML
    private TableColumn<Player, String> playerNameCol;

    @FXML
    private TableColumn<Player, String> playerRankCol;

    @FXML
    private TableView<Player> playersTableView;

    private Team selectedTeam;

    private TeamDAO teamDAO = new TeamDAO();

    @FXML
    void backBtnClicked() {
        try {
            Application.changeScene("teamsView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOutBtnClicked() {
        try {
            Application.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteTeam() {
        System.out.println(selectedTeam.getTeamName());
        teamDAO.deleteTeam(selectedTeam.getTeamName());
        try {
            Application.changeScene("teamsView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds data to the table.
     * @param team the selected team
     */
    public void initData(Team team){
        selectedTeam = team;
        selectedText.setText(team.getTeamName());

        playerNameCol.setCellValueFactory(new PropertyValueFactory<>("gamertag"));
        playerRankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));

        for (Player player : team.getPlayers()) {
            playersTableView.getItems().add(player);
        }
    }
}
