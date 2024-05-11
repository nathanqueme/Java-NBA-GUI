package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams> {

    // --------------------------------------------- //
    @FXML private TableView<Team> groupsTv;
    @FXML private TableColumn<?, ?> teamNameColumn;
    @FXML private TableColumn<?, ?> numPlayersColumn;
    @FXML private TableColumn<?, ?> avgCreditColumn;
    @FXML private TableColumn<?, ?> avgAgeColumn;
    // |                                           | //
    @FXML private Button addTeamButton;
    @FXML private Button manageButton;
    @FXML private Button deleButton;
    @FXML private Button closeButton;
    // --------------------------------------------- //


    public Teams getTeams(){
        return model;
    }
    private Team getTeam() { 
        return groupsTv.getSelectionModel().getSelectedItem();
    }


    @FXML private void initialize() {
        // UI: 1/4 width for each col.
        Double w = 0.245;
        teamNameColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        numPlayersColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        avgCreditColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        avgAgeColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        // Disable btns if no team selected
        manageButton.setDisable(true);
        deleButton.setDisable(true);
        groupsTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isTeamSelected = newSelection != null;
            manageButton.setDisable(!isTeamSelected);
            deleButton.setDisable(!isTeamSelected);
        });
        // Init
        groupsTv.setItems(model.teams);
    }

    // UTILITY METHOD   --------------------------------------
    public static Stage newStage(String imageName) {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/view/" + imageName));
        return stage;
    }
    // -------------------------------------------------------

    @FXML
    public void handleAddTeam() {
        try {
            Stage stage = newStage("edit.png");
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleManageTeam() {
        try {
            Stage stage = newStage("edit.png");
            // TODO pass name of the team to the controller
            String teamName = getTeam().getName();
            ViewLoader.showStage(getTeam(), "/view/ManageTeamView.fxml", "Managing Team: "+teamName, stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleDeleteTeam() {
        getTeams().remove(getTeam());
    }

    @FXML
    public void close() {
        stage.close();
    }
}
