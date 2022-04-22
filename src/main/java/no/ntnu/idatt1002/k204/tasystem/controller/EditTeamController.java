package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TeamDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Player;
import no.ntnu.idatt1002.k204.tasystem.model.Team;
import no.ntnu.idatt1002.k204.tasystem.model.TeamRegister;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for editing a team
 */
public class EditTeamController implements Initializable {

    @FXML
    private Button AddTeamBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField p1NameTextfield;

    @FXML
    private ComboBox p1RankComboBox;

    @FXML
    private TextField p2NameTextfield;

    @FXML
    private ComboBox p2RankComboBox;

    @FXML
    private TextField p3NameTextfield;

    @FXML
    private ComboBox p3RankComboBox;

    @FXML
    private TextField p4NameTextfield;

    @FXML
    private ComboBox p4RankComboBox;

    @FXML
    private TextField p5NameTextfield;

    @FXML
    private ComboBox p5RankComboBox;

    private TeamDAO teamDAO;

    private TeamRegister teamRegister;

    private TextField[] names;

    private ComboBox[] ranks;
    @FXML
    private Label editTeamTxt;

    private Team team;

    /**
     * Initializes the scene
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.teamRegister = new TeamRegister();
        this.teamDAO = new TeamDAO();

        names = new TextField[]{p1NameTextfield, p2NameTextfield, p3NameTextfield, p4NameTextfield, p5NameTextfield};
        ranks = new ComboBox[]{p1RankComboBox, p2RankComboBox, p3RankComboBox, p4RankComboBox, p5RankComboBox};

        addRanksToComboBox();
    }

    /**
     * Fills in current values in the editable fields
     * @param team the selected team
     */
    public void initData(Team team){
        this.team = team;

        editTeamTxt.setText("Edit " + team.getTeamName());

        for (int i = 0; i < 5; i++) {
            Player player = team.getPlayers().get(i);

            names[i].setText(player.getGamertag());
            ranks[i].setValue(player.getRank());
        }

    }

    /**
     * Updates the team in the database
     */
    @FXML
    void updateTeamBtnClicked() {
        try {
            ArrayList<Player> players = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                if (ranks[i].getValue() != null) {
                    players.add(new Player(names[i].getText(), ranks[i].getValue().toString()));
                } else {
                    throw new IllegalArgumentException("Please select a rank for each player");
                }
            }

            teamDAO.updateTeam(team.getTeamName(), players);

            showInformationDialog(team.getTeamName() + " has been updated.");

            teamDAO.getTeam(teamRegister);
            team = teamRegister.getTeamByName(team.getTeamName());

            backBtnClicked();
        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
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
     * Adds all the available ranks to the combo boxes
     */
    private void addRanksToComboBox() {
        ObservableList<String> rankList=FXCollections.observableArrayList("Unranked","Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");

        for (ComboBox comboBox : ranks) {
            comboBox.setItems(rankList);
        }

    }
}
