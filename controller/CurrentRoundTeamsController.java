package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Season;
import model.Game;
import java.util.Arrays;
import java.util.List;
import au.edu.uts.ap.javafx.*;

public class CurrentRoundTeamsController extends Controller<Season> {

    @FXML private TableView<Game> groupsTv;
    @FXML private TableColumn<Game, String> separationColumn;
    @FXML private TableColumn<Game, String> teamOneColumn;
    @FXML private TableColumn<Game, String> teamTwoColumn;
    @FXML private Label roundLabel;
    @FXML private Button closeButton;

    public final Season getSeason() {
        return model;
    }

    @FXML public void initialize() {
        groupsTv.setItems(getSeason().getCurrentSchedule());
        roundLabel.textProperty().bind(Bindings.concat("Round: ", Integer.toString(getSeason().round() + 1)));
        separationColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("VS"));

        List<TableColumn<Game, String>> teamColumns = Arrays.asList(teamOneColumn, teamTwoColumn);
        for (int i = 0; i < teamColumns.size(); i++) {
            final int teamIndex = i;  // Capture index for use in lambda
            teamColumns.get(i).setCellValueFactory(cellData -> {
                Game game = cellData.getValue();
                if (game.getCurrentTeams().size() > teamIndex) {
                    return new ReadOnlyStringWrapper(game.getCurrentTeams().get(teamIndex).getName());
                } else {
                    return new ReadOnlyStringWrapper("");
                }
            });
        }
    }


    @FXML public void close() {
        stage.close();
    }
}