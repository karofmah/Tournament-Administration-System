package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
import no.ntnu.idatt1002.k204.tasystem.Application;

import java.io.IOException;

public class AddEligibleTeamsController {

    @FXML
    private Button backBtn;

    @FXML
    void backBtnClicked(ActionEvent event) {

    }

    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            Application.changeScene("groupStageView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
