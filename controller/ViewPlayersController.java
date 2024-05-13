package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import model.Player;
import model.Teams;

public class ViewPlayersController extends Controller<Teams> {

    @FXML private TableView<Player> groupsTv;

    @FXML private TableColumn<Player, String> teamColumn;
    @FXML private TableColumn<Player, String> nameColumn;
    @FXML private TableColumn<Player, Integer> creditColumn;
    @FXML private TableColumn<Player, Integer> ageColumn;
    @FXML private TableColumn<Player, Integer> noColumn;
    @FXML private TableColumn<Player, Integer> levelColumn;

    @FXML private TextField levelTf;
    @FXML private TextField nameTf;
    @FXML private TextField fromTf;
    @FXML private TextField toTf;

    @FXML private Button closeButton;

    public Teams getTeams(){return model;}


    @FXML
    private void initialize() {

        // UI: 1/4 width for each col.
        Double w = 0.16;
        teamColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        nameColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        creditColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        ageColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        noColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        levelColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));

        groupsTv.setItems(getTeams().allPlayersList());
    }

    @FXML
    private void close() {
        stage.close();
    }

}
