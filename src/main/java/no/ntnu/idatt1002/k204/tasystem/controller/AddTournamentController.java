package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @FXML TextField timePicker;

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
    private Text StageselectIndicator;

    /**
     * Handle add tournament events
     *
     * Add tournament to database
     */
    private boolean hasGroupStage = false;
    TournamentRegister register = new TournamentRegister();
    @FXML
    void addTournamentBtnClicked() {
        LocalDate date = datePicker.getValue();
        int hour = Integer.parseInt(timePicker.getText().split(":")[0]);
        int minutes =Integer.parseInt(timePicker.getText().split(":")[0]);
        LocalTime time = LocalTime.of(hour, minutes);
        LocalDateTime datetime = LocalDateTime.of(date, time);
        register.addTournament(nameTextField.getText(),requirementsTextfield.getText(), hasGroupStage,datetime);
        System.out.println(register.getTournaments());
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
        hasGroupStage =true;
    }

    /**
     * Toggle knockout option
     */
    @FXML
    void knockoutBtnClicked() {
        hasGroupStage = false;
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
