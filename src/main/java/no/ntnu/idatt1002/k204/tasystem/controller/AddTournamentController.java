package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private Label txtAddedTournament;

    @FXML
    private ComboBox rankRequirementComboBox = new ComboBox();

    private boolean editTournament=false;

    /**
     * Handle add tournament events
     *
     * Add tournament to database
     */
    @FXML
    void addTournamentBtnClicked() {
        if(editTournament==true){
            editTournamentClicked();
        }
        else{
            try {
                TournamentDAO tournamentDAO = new TournamentDAO();
                if(rankRequirementComboBox.getValue()==null){
                    throw new IllegalArgumentException("Please choose the rank requirement of this tournament");
                }
                Tournament tournament1 = new Tournament(nameTextField.getText(),rankRequirementComboBox.getValue().toString(),otherRequirementsTextfield.getText(),
                        String.valueOf(datePicker.getValue()), timeTextField.getText());

                String status = "Inactive";

                tournamentDAO.addTournament(tournament1.getTournamentId(),tournament1.getName(), status,
                        tournament1.getRankRequirement(),tournament1.getOtherRequirement(), String.valueOf(tournament1.getDate()), String.valueOf(tournament1.getTime()));

                showInformationDialog(nameTextField.getText()+ " has been added to the tournament list!");

                backBtnClicked();
            } catch (IllegalArgumentException e) {
                showAlertDialog(e);
        }
        }
    }

    private void addRanksToComboBox(){
        ObservableList<String> rankList= FXCollections.observableArrayList("Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");

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
    @FXML
    private void editTournamentClicked(){

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addRanksToComboBox();
    }
    public void initData(Tournament tournament){
        editTournament=true;
        addNewTournamentTitle.setText("Edit " + tournament.getName());
        nameTextField.setText(tournament.getName());
        rankRequirementComboBox.setPromptText(tournament.getRankRequirement());
        otherRequirementsTextfield.setPromptText(tournament.getOtherRequirement());
        datePicker.setPromptText(tournament.getDate().toString());
        timeTextField.setText(tournament.getTime().toString());
        addTournamentBtn.setText("Edit tournament");

    }

}
