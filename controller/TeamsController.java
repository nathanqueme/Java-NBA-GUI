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

    @FXML private TableView<Team> groupsTv;

    @FXML private Button addTeamButton;
    @FXML private Button manageButton;
    @FXML private Button deleButton;
    @FXML private Button closeButton;


    public Teams getTeams(){
        return model;
    }

    @FXML
    private TableColumn<?, ?> teamNameColumn;
    @FXML
    private TableColumn<?, ?> numPlayersColumn;
    @FXML
    private TableColumn<?, ?> avgCreditColumn;
    @FXML
    private TableColumn<?, ?> avgAgeColumn;


    @FXML private void initialize() {
        teamNameColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(0.25));
        numPlayersColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(0.25));
        avgCreditColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(0.25));
        avgAgeColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(0.25));
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
            String teamName = "Nets";
            ViewLoader.showStage(null, "/view/ManageTeamView.fxml", "Managing Team: "+teamName, stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleDeleteTeam() {
        // TODO: implement this method to delete the team.
    }

    @FXML
    public void close() {
        stage.close();
    }
}
