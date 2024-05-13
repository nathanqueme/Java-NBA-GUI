package controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Season;
import model.Players;
import model.Team;
import model.Game;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.edu.uts.ap.javafx.*;

public class TeamsRoundController extends Controller<Season> {

    @FXML private ListView<Team> teamsList;
    @FXML private TableView<Game> groupsTv;
    @FXML private TableColumn<Game, String> gameColumn;
    @FXML private TableColumn<Game, String> teamOneColumn;
    @FXML private TableColumn<Game, String> teamTwoColumn;
    @FXML private Button addButton;
    @FXML private Button arrangeSeasonButton;

    public final Season getSeason() {
        return model;
    }

    @FXML public void initialize() {
        teamsList.setItems(getSeason().getCurrentTeams());
        groupsTv.setItems(getSeason().getCurrentSchedule());

        addButton.disableProperty().bind(Bindings.size(teamsList.getSelectionModel().getSelectedItems()).isNotEqualTo(2));
        arrangeSeasonButton.disableProperty().bind(Bindings.size(groupsTv.getItems()).isEqualTo(0));

        
    }

    @FXML public void addTeams() {
        //ObservableList<Team> selectedTeams = teamsList.getSelectionModel().getSelectedItems();
        //if (selectedTeams.size() >= 2) {
        //    getSeason().addTeams(FXCollections.observableArrayList(selectedTeams.subList(0, 2)));
        //} else {
        //    // Handle the case where not enough teams are selected
        //    Alert alert = new Alert(Alert.AlertType.ERROR);
        //    alert.setContentText("Please select at least two teams to add to the game.");
        //    alert.show();
        //}
    }

    @FXML public void arrangeSeason() {
        String result = getSeason().playGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(result);
        alert.show();
        if (result.contains("Champion")) {
            stage.close();
        }
    }
}
