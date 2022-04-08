package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.controller.utils.GroupStageUtils;
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
    private Label selectedText;

    @FXML
    private Button finishGroupStageBtn;

    @FXML
    private TreeTableColumn<Team, String> pointsColTable1;

    @FXML
    private TreeTableColumn<Team, String> pointsColTable2;

    @FXML
    private TreeTableColumn<Team, String> pointsColTable3;

    @FXML
    private TreeTableColumn<Team, String> pointsColTable4;

    @FXML
    private Button generateGroupsBtn;

    @FXML
    private TreeTableColumn<Team, String> teamColTable1;

    @FXML
    private TreeTableColumn<Team, String> teamColTable2;

    @FXML
    private TreeTableColumn<Team, String> teamColTable3;

    @FXML
    private TreeTableColumn<Team, String> teamColTable4;

    @FXML
    private TreeTableView<Team> tableView1;

    @FXML
    private TreeTableView<Team> tableView2;

    @FXML
    private TreeTableView<Team> tableView3;

    @FXML
    private TreeTableView<Team> tableView4;


    @FXML
    private Button teamsBtn;

    private final TreeItem<Team> table1root = new TreeItem<>();
    private final TreeItem<Team> table2root = new TreeItem<>();
    private final TreeItem<Team> table3root = new TreeItem<>();
    private final TreeItem<Team> table4root = new TreeItem<>();
    private TournamentDAO tournamentDAO;
    private TeamRegister teamRegister;
    private ObservableList<String> teamNames;
    ObservableList<Team> teams1;
    ObservableList<Team> teams2;
    ObservableList<Team> teams4;
    ObservableList<Team> teams3;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tournamentDAO = new TournamentDAO();
        this.teamRegister = this.tournamentDAO.getTeamsGivenTournamentId(Tournament.getSelectedTournamentID());
        this.teamNames = FXCollections.observableArrayList();
        this.teams1 = FXCollections.observableArrayList();
        this.teams2 = FXCollections.observableArrayList();
        this.teams4 = FXCollections.observableArrayList();
        this.teams3 = FXCollections.observableArrayList();

        selectedText.setText(this.tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()).getName());
        for (Team t : this.teamRegister.getTeams()) {
            this.teams1.add(t);
            this.teams2.add(t);
            this.teams3.add(t);
            this.teams4.add(t);
        }

        initializeRowsWithDefaultText();

        initializeTeamColumns();

        initializePointsColumns();

        addTeamNamesToTeamsList();

        addComboboxToTeamCol(this.teamNames);

        setRootInTreeTables();

        handleEditingTeamCols();


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
        try {
            Application.changeScene("knockOutStageView.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle event to generate groups
     */
    @FXML
    void generateBtnClicked() {
        GroupStageUtils.randomize(this.teams1, this.table1root);
        GroupStageUtils.randomize(this.teams2, this.table2root);
        GroupStageUtils.randomize(this.teams3, this.table3root);
        GroupStageUtils.randomize(this.teams4, this.table4root);
    }

    /**
     * Start tournament
     */
    @FXML
    void startTournamentBtnClicked() {
        setNotEditableTeamCols();
        setPointsColumnsAsEditable();
        generateGroupsBtn.setDisable(true);
    }

    @FXML
    void finishGroupStageBtnClicked() {


    }

    @FXML
    void teamsBtnClicked() {
        try {
            Application.changeScene("selectedTournamentView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeRowsWithDefaultText() {
        GroupStageUtils.initDefaultText(this.table1root);
        GroupStageUtils.initDefaultText(this.table2root);
        GroupStageUtils.initDefaultText(this.table3root);
        GroupStageUtils.initDefaultText(this.table4root);
    }

    private void setRootInTreeTables() {
        setRootInTableView(this.tableView1, this.table1root);
        setRootInTableView(this.tableView2, this.table2root);
        setRootInTableView(this.tableView3, this.table3root);
        setRootInTableView(this.tableView4, this.table4root);
    }

    private void setRootInTableView(TreeTableView<Team> tableView, TreeItem<Team> root) {
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    //When start button is clicked, don't allow editing teams.
    private void setNotEditableTeamCols() {
        this.teamColTable1.setEditable(false);
        this.teamColTable2.setEditable(false);
        this.teamColTable3.setEditable(false);
        this.teamColTable4.setEditable(false);
    }

    //Allow editing points column when start is clicked.
    private void setPointsColumnsAsEditable() {
        this.pointsColTable1.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        this.pointsColTable2.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        this.pointsColTable3.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        this.pointsColTable4.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
    }

    //Get teams from the team register, add them to an observable list which is used in the combo boxes.
    private void addTeamNamesToTeamsList() {
        for (Team team : this.teamRegister.getTeams()) {
            this.teamNames.add(team.getTeamName());
        }
    }

    private void addComboboxToTeamCol(ObservableList<String> teamNames) {
        this.teamColTable1.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(teamNames));
        this.teamColTable2.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(teamNames));
        this.teamColTable3.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(teamNames));
        this.teamColTable4.setCellFactory(ChoiceBoxTreeTableCell.forTreeTableColumn(teamNames));
    }

    private void handleEditingTeamCols() {
        chooseAndSetTeam(this.teamColTable1, this.tableView1);
        chooseAndSetTeam(this.teamColTable2, this.tableView2);
        chooseAndSetTeam(this.teamColTable3, this.tableView3);
        chooseAndSetTeam(this.teamColTable4, this.tableView4);
    }

    private void chooseAndSetTeam(TreeTableColumn<Team, String> teamCol, TreeTableView<Team> tableView) {
        //Get column that is being edited
        teamCol.setOnEditCommit(event -> {
            //Get name that has been chosen from combobox
            TreeItem<Team> currentlyChosen = tableView.getTreeItem(event.getTreeTablePosition().getRow());
            //Set name that has been chosen to the cell. Or else it is lost when tournament is started.
            currentlyChosen.getValue().setTeamName(event.getNewValue());
        });
    }

    @FXML
    void logOutBtnClicked() {
        try {
            Application.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeTeamColumns() {
        this.teamColTable1.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().teamNameProperty());
        this.teamColTable2.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().teamNameProperty());
        this.teamColTable3.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().teamNameProperty());
        this.teamColTable4.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().teamNameProperty());
    }

    private void initializePointsColumns() {
        this.pointsColTable1.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().pointsProperty());
        this.pointsColTable2.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().pointsProperty());
        this.pointsColTable3.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().pointsProperty());
        this.pointsColTable4.setCellValueFactory((TreeTableColumn.CellDataFeatures<Team, String> param) -> param.getValue().getValue().pointsProperty());
    }
}
