package no.ntnu.idatt1002.k204.tasystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Application for Tournament Administration System
 *
 */
public class Application extends javafx.application.Application {

    public static Stage stage;

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void start(Stage stage) {
        Application.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginView.fxml"));
        Scene scene = null;

        try {
            scene = new Scene(fxmlLoader.load(), 861, 672);
        } catch (IOException e) {
            logger.severe("ERROR: IOException occurred. Cause: " + e.getCause());
        }

        stage.setTitle("TA-System");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change scene.
     *
     * Change to given fxml view
     * @param fxml the fxml
     * @throws IOException the io exception
     */
    public static void changeScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(Application.class.getResource(fxml));
        Stage stage = Application.stage;
        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch();
    }
}

//Fanuel var her
//Emil var her

//Karo var her v10