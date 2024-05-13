package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Teams;

public class ViewPlayersController extends Controller<Teams> {

    @FXML private TableView<Player> groupsTv;
    @FXML private TableColumn<Player, String> teamColumn;
    @FXML private TableColumn<Player, String> nameColumn;
    @FXML private TableColumn<Player, Integer> creditColumn;
    @FXML private TableColumn<Player, Integer> ageColumn;
    @FXML private TableColumn<Player, Integer> noColumn;
    @FXML private TableColumn<Player, String> levelColumn;
    @FXML private TextField levelTf;
    @FXML private TextField nameTf;
    @FXML private TextField fromTf;
    @FXML private TextField toTf;
    @FXML private Button closeButton;

    private FilteredList<Player> filteredPlayers;

    public Teams getTeams() { return model; }

    @FXML
    private void initialize() {

        // Assuming all checks passed, continue initialization
        Double w = 0.16;
        teamColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        nameColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        creditColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        ageColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        noColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        levelColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
    
        filteredPlayers = new FilteredList<>(getTeams().allPlayersList(), p -> true);
        groupsTv.setItems(filteredPlayers);

        fromTf.setText("0");
        toTf.setText("0");
    
        levelTf.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        nameTf.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        fromTf.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
        toTf.textProperty().addListener((observable, oldValue, newValue) -> filterPlayers());
    }

    private void filterPlayers() {
        filteredPlayers.setPredicate(player -> {
            String levelFilter = levelTf.getText().toLowerCase();
            String nameFilter = nameTf.getText().toLowerCase();
            String fromFilter = fromTf.getText();
            String toFilter = toTf.getText();

            boolean matchesLevel = levelFilter.isEmpty() || player.getLevel().toLowerCase().startsWith(levelFilter);
            boolean matchesName = nameFilter.isEmpty() || player.getName().toLowerCase().contains(nameFilter);

            boolean matchesAge = true;
            try {
                int ageFrom = fromFilter.isEmpty() || fromFilter.equals("0") ? 0 : Integer.parseInt(fromFilter);
                int ageTo = toFilter.isEmpty() || toFilter.equals("0") ? Integer.MAX_VALUE : Integer.parseInt(toFilter);
                matchesAge = (player.getAge() >= ageFrom && player.getAge() <= ageTo);
            } catch (NumberFormatException e) {
                // handle case where from or to fields are not valid integers
            }

            return matchesLevel && matchesName && matchesAge;
        });
    }

    @FXML
    private void close() {
        stage.close();
    }
}
