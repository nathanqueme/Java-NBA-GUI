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

public class ExploreTeamsController extends Controller<Teams> {

    @FXML private Button teamsMenuButton;
    @FXML private Button viewPlayersButton;
    @FXML private Button closeButton;

    public Teams getTeams(){
        return model;
    }

    // UTILITY METHOD   --------------------------------------
    public static Stage newStage() {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/view/nba.png"));
        return stage;
    }
    // -------------------------------------------------------

    @FXML
    public void seeTeamsMenu() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getTeams().currentTeams(), "/view/TeamsTable.fxml", "Teams Menu", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seePlayers() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getTeams().allPlayersList(), "/view/PlayersView.fxml", "Players", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void close() {
        stage.close();
    }
}
