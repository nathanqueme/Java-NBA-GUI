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
import model.Teams;

public class TeamsController extends Controller<Teams> {

    @FXML private Button addTeamButton;
    @FXML private Button manageButton;
    @FXML private Button deleButton;
    @FXML private Button closeButton;


    public Teams getTeams(){
        return model;
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
            ViewLoader.showStage(null, "/view/AddTeam.fxml", "Adding New Team", stage);
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
