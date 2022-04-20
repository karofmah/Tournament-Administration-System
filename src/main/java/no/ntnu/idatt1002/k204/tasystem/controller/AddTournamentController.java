package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for adding tournaments
 */
public class AddTournamentController implements Initializable {

    @FXML
    private Label addNewTournamentTitle;

    @FXML
    private Button addTournamentBtn;

    @FXML
    private Button backBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button groupKnockoutBtn;

    @FXML
    private Button knockoutBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField requirementsTextfield;

    @FXML
    private TextField otherRequirementsTextfield;

    @FXML
    private TextField timeTextField;

    @FXML
    private ComboBox rankRequirementComboBox = new ComboBox();

    private boolean editTournament = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addRanksToComboBox();
    }

    /**
     * Initializes the page with data from the selected tournament when an "edit tournament" button was pressed.
     * @param tournament the selected tournament
     */
    public void initData(Tournament tournament){
        editTournament = true;
        addNewTournamentTitle.setText("Edit " + tournament.getName());
        nameTextField.setText(tournament.getName());
        rankRequirementComboBox.setValue(tournament.getRankRequirement());
        otherRequirementsTextfield.setText(tournament.getOtherRequirement());
        datePicker.setValue(tournament.getDate());
        timeTextField.setText(tournament.getTime().toString());
        addTournamentBtn.setText("Save");
    }

    /**
     * Adds all possible ranks to the combo box.
     */
    private void addRanksToComboBox(){
        ObservableList<String> rankList= FXCollections.observableArrayList("Unranked","Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");
        rankRequirementComboBox.setItems(rankList);
    }

    /**
     * Handles add tournament events, and adds them to the database via TournamentDAO.
     * If an "edit tournament" button was clicked, the method will instead just update the selected tournament.
     */
    @FXML
    void addTournamentBtnClicked() {
        if (editTournament) editTournament();
        else addTournament();
    }

    /**
     * Method used to add new tournaments.
     */
    private void addTournament() {
        try {
            TournamentDAO tournamentDAO = new TournamentDAO();

            if (rankRequirementComboBox.getValue() == null) {
                throw new IllegalArgumentException("Please choose the rank requirement of this tournament");
            }

            Tournament tournament1 = new Tournament(
                    nameTextField.getText(),
                    rankRequirementComboBox.getValue().toString(),
                    otherRequirementsTextfield.getText(),
                    String.valueOf(datePicker.getValue()),
                    timeTextField.getText());

            String status = "Inactive";

            tournamentDAO.addTournament(tournament1.getTournamentId(),tournament1.getName(), status,
                    tournament1.getRankRequirement(),tournament1.getOtherRequirement(),
                    String.valueOf(tournament1.getDate()), String.valueOf(tournament1.getTime()));

            showInformationDialog(nameTextField.getText() + " has been added to the tournament list!");

            backBtnClicked();
        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    /**
     * Method used to edit existing tournaments by updating the database.
     */
    @FXML
    private void editTournament(){
        try {
            TournamentDAO tournamentDAO = new TournamentDAO();

            tournamentDAO.updateTournament(Tournament.getSelectedTournamentID(), nameTextField.getText(),
                    rankRequirementComboBox.getValue().toString(), otherRequirementsTextfield.getText(),
                    String.valueOf(datePicker.getValue()), timeTextField.getText());

            showInformationDialog(nameTextField.getText()+ " has been updated.");

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
            Application.changeScene("frontPageView.fxml");
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
}
