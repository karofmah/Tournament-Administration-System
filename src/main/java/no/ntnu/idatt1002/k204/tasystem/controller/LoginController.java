package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Controller for handling login.
 *
 * (to be added, database connection when login success)
 *
 */
public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;


    /**
     * Handle login button event
     *
     * Start database connection and change scene when authenticated user logs in.
     *
     */
    @FXML
    void loginButtonClicked() {
        //TODO

        //FIXME Add database connection:
        // - 1. Start connection when authenticated user logs in
        // - 2. After successful authentication, change scene

        System.out.println("Login clicked\n");
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());//
    }

}