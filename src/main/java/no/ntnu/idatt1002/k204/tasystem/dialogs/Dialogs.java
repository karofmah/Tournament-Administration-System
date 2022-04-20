package no.ntnu.idatt1002.k204.tasystem.dialogs;

import javafx.scene.control.Alert;

public class Dialogs {
    /**
     * Displays an information dialog
     * @param text the text to be displayed
     */
    public static void showInformationDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tournament Administration System");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Displays an alert dialog
     * @param text the text to be displayed
     */
    public static void showAlertDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tournament Administration System");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    /**
     * Displays an error message for when an exception occurs
     * @param e the exception that is thrown
     */
    public static void showAlertDialog(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tournament Administration System");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
