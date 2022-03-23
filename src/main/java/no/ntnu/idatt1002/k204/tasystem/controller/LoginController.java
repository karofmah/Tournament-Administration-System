package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.AdministratorDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.Database;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Controller for handling login.
 *
 *
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
     *
     * Start database connection and change scene when authenticated user logs in.
     *
     */
    @FXML
    void loginButtonClicked() {
        try {
            System.out.println("Connecting...");
            database = Database.getInstance();
            AdministratorDAO adminDAO = new AdministratorDAO();

            //if (adminDAO.getAdmin(usernameTextField.getText(), passwordTextField.getText())) {
            if (adminDAO.getAdmin("skype", "1234")) { //Only for testing, remove afterwards
                System.out.println("Succesfully logged in!");
                Application.changeScene("frontPageView.fxml");
            } else {
                System.out.println("You don't have access to this system!");
                //TODO

                //FIXME Add user feedback when not authenticated
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}