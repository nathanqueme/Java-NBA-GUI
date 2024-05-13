package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ListChangeListener;
import javafx.fxml.*;
import javafx.scene.control.*;
import model.Season;
import model.Team;
import model.Game;
import java.util.Arrays;
import java.util.List;
import au.edu.uts.ap.javafx.*;

public class TeamsRoundController extends Controller<Season> {

    @FXML private ListView<Team> teamsList;
    @FXML private TableView<Game> groupsTv;
    @FXML private TableColumn<Game, String> gameColumn;
    @FXML private TableColumn<Game, String> teamOneColumn;
    @FXML private TableColumn<Game, String> teamTwoColumn;
    @FXML private Label roundLabel;
    @FXML private Button addButton;
    @FXML private Button arrangeSeasonButton;

    public final Season getSeason() {
        return model;
    }

    @FXML public void initialize() {

        teamsList.setItems(getSeason().getCurrentTeams());
        groupsTv.setItems(getSeason().getCurrentSchedule());
        roundLabel.textProperty().bind(Bindings.concat("Round: ", Integer.toString(getSeason().round() + 1)));

        teamsList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        teamsList.getSelectionModel().getSelectedItems().addListener((ListChangeListener.Change<? extends Team> change) -> {
            if (teamsList.getSelectionModel().getSelectedItems().size() > 2) {
                int last = teamsList.getSelectionModel().getSelectedIndices().get(teamsList.getSelectionModel().getSelectedIndices().size() - 1);
                teamsList.getSelectionModel().clearSelection(last);
            }
        });

        addButton.disableProperty().bind(Bindings.size(teamsList.getSelectionModel().getSelectedItems()).isNotEqualTo(2));
        arrangeSeasonButton.disableProperty().bind(Bindings.size(getSeason().getCurrentTeams()).isNotEqualTo(0));

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

    @FXML public void addTeams() {
        getSeason().addTeams(teamsList.getSelectionModel().getSelectedItems());
    }

    @FXML public void arrangeSeason() {
        stage.close();
    }
}
