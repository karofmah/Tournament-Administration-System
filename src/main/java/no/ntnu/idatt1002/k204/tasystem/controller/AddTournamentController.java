package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.model.TournamentRegister;

import java.io.IOException;
import java.time.LocalDateTime;

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
    private Text StageselectIndicator;

    /**
     * Handle add tournament events
     *
     * Add tournament to database
     */
    private boolean hasGroupStage = false;

    @FXML
    void addTournamentBtnClicked() {
     //    String[] timesplitt = datePicker.getText().split("-");
      //      private LocalDateTime time = LocalDateTime.of(timesplitt[0],timesplitt[1].timesplitt[2])
       //     TournamentRegister register = new TournamentRegister();
         //   register.addTournament(nameTextField.getText(),requirementsTextfield.getText(), hasGroupStage,datePicker.getValue())
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
