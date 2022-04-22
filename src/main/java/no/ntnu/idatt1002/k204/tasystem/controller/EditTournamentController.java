package no.ntnu.idatt1002.k204.tasystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.ntnu.idatt1002.k204.tasystem.Application;
import no.ntnu.idatt1002.k204.tasystem.dao.TournamentDAO;
import no.ntnu.idatt1002.k204.tasystem.model.Tournament;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showAlertDialog;
import static no.ntnu.idatt1002.k204.tasystem.dialogs.Dialogs.showInformationDialog;

/**
 * Controller for editing tournaments
 */
public class EditTournamentController implements Initializable {

    @FXML
    private Label editTournamentTitle;

    @FXML
    private Button addTournamentBtn;

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
    private TextField otherRequirementsTextfield;

    @FXML
    private TextField timeTextField;

    @FXML
    private ComboBox rankRequirementComboBox = new ComboBox();

    private TournamentDAO tournamentDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tournamentDAO = new TournamentDAO();

        addRanksToComboBox();
    }

    /**
     * Initializes the page with data from the selected tournament when the "edit tournament" button was pressed.
     * @param tournament the selected tournament
     */
    public void initData(Tournament tournament){
        editTournamentTitle.setText("Edit " + tournament.getName());
        nameTextField.setText(tournament.getName());
        rankRequirementComboBox.setValue(tournament.getRankRequirement());
        otherRequirementsTextfield.setText(tournament.getOtherRequirement());
        datePicker.setValue(tournament.getDate());
        timeTextField.setText(tournament.getTime().toString());
    }

    /**
     * Adds all possible ranks to the combo box.
     */
    private void addRanksToComboBox(){
        ObservableList<String> rankList= FXCollections.observableArrayList("Unranked","Iron","Bronze","Silver","Gold", "Platinum", "Diamond","Master","Grandmaster","Challenger");
        rankRequirementComboBox.setItems(rankList);
    }

    /**
     * Method used to edit existing tournaments by updating the database.
     */
    @FXML
    private void updateTournamentBtnClicked(){
        try {
            TournamentDAO tournamentDAO = new TournamentDAO();

            tournamentDAO.updateTournament(Tournament.getSelectedTournamentID(), nameTextField.getText(),
                    rankRequirementComboBox.getValue().toString(), otherRequirementsTextfield.getText(),
                    String.valueOf(datePicker.getValue()), timeTextField.getText());

            showInformationDialog(nameTextField.getText()+ " has been updated.");

            backBtnClicked();
        } catch (IllegalArgumentException e) {
            showAlertDialog(e);
        }
    }

    /**
     * Navigate back to previous scene
     */
    @FXML
    void backBtnClicked() {
        try {
            URL fxmlLocation = getClass().getResource("/no/ntnu/idatt1002/k204/tasystem/selectedTournamentView.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent FrontPageParent = loader.load();
            SelectedTournamentController controller = loader.getController();
            controller.initData(tournamentDAO.getTournamentById(Tournament.getSelectedTournamentID()));
            Stage stage = Application.stage;
            stage.getScene().setRoot(FrontPageParent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
