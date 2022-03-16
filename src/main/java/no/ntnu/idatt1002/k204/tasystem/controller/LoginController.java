package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import no.ntnu.idatt1002.k204.tasystem.dao.AdministratorDAO;
import no.ntnu.idatt1002.k204.tasystem.dao.Database;

import java.sql.SQLException;


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
            database = new Database();
            AdministratorDAO adminDAO = new AdministratorDAO();

            if (adminDAO.getAdmin(usernameTextField.getText(), passwordTextField.getText())) {
                System.out.println("Succesfully logged in!");
                //TODO

                //FIXME Add database connection:
                // - 1. After successful authentication, change scene
            } else {
                System.out.println("You don't have access to this system!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}