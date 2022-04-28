package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.AdministratorDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.Database;
import no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Controller for handling login.
 */
public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;
    Database database;

    /**
     * Handle login button event
     * <p>
     * Start database connection and change scene when authenticated user logs in.
     */
    @FXML
    void loginButtonClicked() {
        try {
            System.out.println("Connecting...");
            database = Database.getInstance();
            AdministratorDAO adminDAO = new AdministratorDAO();

            if (adminDAO.getAdmin(usernameTextField.getText(), passwordTextField.getText())) {
                System.out.println("Successfully logged in!");
                Application.changeScene("frontPageView.fxml");
            } else {
                Dialogs.showAlertDialog("You don't have access to this system!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}