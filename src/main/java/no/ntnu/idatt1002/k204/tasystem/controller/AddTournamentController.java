package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;

/**
 * Controller for adding tournaments
 */
public class AddTournamentController {

    @FXML
    private Button AddTournamentBtn;

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
    private TextField timeTextField;

    @FXML
    private Label txtAddedTournament;

    /**
     * Handle add tournament events
     *
     * Add tournament to database
     */
    @FXML
    void addTournamentBtnClicked() {
        TournamentDAO tournamentDAO = new TournamentDAO();

        Tournament tournament1 = new Tournament(nameTextField.getText(), requirementsTextfield.getText(),
                String.valueOf(datePicker.getValue()), timeTextField.getText());

        String status = "Inactive";

        //tournamentDAO.addTournament(tournament1.getTournamentId(),tournament1.getName(), status,
        //        tournament1.getRankRequirement(), String.valueOf(tournament1.getDate()), String.valueOf(tournament1.getTime()));

        showInformationDialog(nameTextField.getText()+ " has been added to the tournament list!");

        backBtnClicked();
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
     * Toggle group + knockout option
     */
    @FXML
    void groupKnockoutBtnClicked() {
        //TODO
        // ADD ME: Toggle group + knockout option (maybe use radio buttons here)
    }

    /**
     * Toggle knockout option
     */
    @FXML
    void knockoutBtnClicked() {
        //TODO
        // ADD ME: Toggle knockout option (maybe use radio buttons here)
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

    /**
     * Displays an information dialog
     * @param text the text to be displayed
     */
    private void showInformationDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tournament Administration System");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

}
