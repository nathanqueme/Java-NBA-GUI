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
            ViewLoader.showStage(getSeason(), "/view/SeasonRoundView.fxml", "Season Rounds", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seeCurrent() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getSeason(), "/view/CurrentRoundTeams.fxml", "Tournament", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void startGame() {
        Stage stage = newStage();
        String message = getSeason().playGame();
        String title = "All Games Played!";
        try {
            ViewLoader.showStage(message, "/view/error.fxml", title, stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void seeResults() {
        try {
            Stage stage = newStage();
            ViewLoader.showStage(getSeason(), "/view/RecordView.fxml", "Season Record", stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void close() {
        stage.close();
    }
}
