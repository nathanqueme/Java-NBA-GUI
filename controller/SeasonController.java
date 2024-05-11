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
import model.Season;

public class SeasonController extends Controller<Season> {

    @FXML private Button roundButton;
    @FXML private Button currentButton;
    @FXML private Button gameButton;
    @FXML private Button resultsButton;
    @FXML private Button closeButton;

    public Season getSeason(){
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
    public void seeRound() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getSeason().getCurrentSchedule(), "/view/SeasonRoundView.fxml", "View Round", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seeCurrent() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getSeason().getCurrentTeams(), "/view/CurrentRoundTeams.fxml", "Current Teams", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seeGame() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(null, "/view/PlayersView.fxml", "Game Details", stage); // Modify as needed to fit actual data usage
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seeResults() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getSeason().record(), "/view/RecordView.fxml", "Game Results", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void close() {
        stage.close();
    }
}
