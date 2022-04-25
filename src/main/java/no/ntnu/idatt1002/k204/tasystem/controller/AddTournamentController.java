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
    private DatePicker datePicker;


    @FXML
    private TextField nameTextField;


    @FXML
    private TextField otherRequirementsTextfield;

    @FXML
    private TextField timeTextField;

    @FXML
    private ComboBox rankRequirementComboBox = new ComboBox();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addRanksToComboBox();
    }

    /**
     * Handles add tournament events, and adds them to the database via TournamentDAO.
     */
    @FXML
    private void addTournamentBtnClicked() {
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

            tournamentDAO.addTournament(tournament1.getTournamentID(),tournament1.getName(), status,
                    tournament1.getRankRequirement(),tournament1.getOtherRequirement(),
                    String.valueOf(tournament1.getDate()), String.valueOf(tournament1.getTime()));

            showInformationDialog(nameTextField.getText() + " has been added to the tournament list!");

            backBtnClicked();
        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    /**
     * Adds all possible ranks to the combo box.
     */
    private void addRanksToComboBox(){
        ObservableList<String> rankList= FXCollections.observableArrayList("Unranked","Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");
        rankRequirementComboBox.setItems(rankList);
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
}
