package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

public class AddEligibleTeamsController implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn <?,?> teamNameCol;

    @FXML
    private TableColumn<?, ?> lowRankCol;

    @FXML
    private TableView<Team> teamsTableView;

    private TeamRegister teamRegister;

    private TeamDAO teamDAO;

    private ObservableList<Team> teamObservableList;

    private Tournament selectedTournament;

    private TournamentDAO tournamentDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teamNameCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        this.lowRankCol.setCellValueFactory(new PropertyValueFactory<>("lowestRank"));
        this.tournamentDAO = new TournamentDAO();
        this.teamRegister = new TeamRegister();
        this.teamDAO = new TeamDAO();
        this.teamDAO.getTeam(this.teamRegister);

        ArrayList<Team> eligibleTeams = new ArrayList<>();
        String[] ranks = {"Unranked", "Iron", "Bronze", "Silver", "Gold", "Platinum", "Diamond", "Master", "Grandmaster", "Challenger"};
        ArrayList<String> ranksArraylist = new ArrayList();
        for (String rank:ranks) {
            ranksArraylist.add(rank);
        }
        for (Team team : teamRegister.getTeams()) {
                if(ranksArraylist.indexOf(team.getLowestRank()) >= ranksArraylist.indexOf(tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getRankRequirement())){
                    eligibleTeams.add(team);
                }
            }

            this.teamObservableList = FXCollections.observableArrayList(eligibleTeams);
            this.teamsTableView.setItems(this.teamObservableList);
            handleTeamSelection();
    }
    private void handleTeamSelection() {
        try{
        teamsTableView.setRowFactory(table -> {
            TableRow<Team> row = new TableRow<>();

            row.hoverProperty().addListener(observable -> {//Listen for hover on row
                Team team = row.getItem();
                if (row.isHover() && team != null) {
                    row.setOnMouseEntered(mouseEvent1 -> {//Listen when mouse is hovered over a row
                        teamsTableView.setCursor(Cursor.HAND);//Change courser
                        row.setOnMouseClicked(mouseEvent2 -> { //Listen for click event
                            selectedTournament.addTeam(team);
                            showInformationDialog(team.getTeamName() + " has been added to " + selectedTournament.getName());
                        });
                    });
                } else {
                    row.setOnMouseEntered(mouseEvent -> { //Default cursor when row is empty
                        teamsTableView.setCursor(Cursor.DEFAULT);
                    });
                }
            });

            return row;
        });}catch (IllegalArgumentException e){
            showAlertDialog(e);
        }
    }
    /**
     * Navigate back to previous scene
     */
    public void initData(Tournament tournament){
        selectedTournament = tournament;

    }
    @FXML
    void backBtnClicked(ActionEvent Event) {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/selectedTournamentView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SelectedTournamentController controller = loader.getController();
            controller.initData(selectedTournament);
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
