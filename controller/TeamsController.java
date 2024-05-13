package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import au.edu.uts.ap.javafx.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.stage.*;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import model.Team;
import model.Teams;

public class TeamsController extends Controller<Teams> {

    // --------------------------------------------- //
    @FXML private TableView<Team> groupsTv;
    @FXML private TableColumn<?, ?> teamNameColumn;
    @FXML private TableColumn<?, ?> numPlayersColumn;
    @FXML private TableColumn<?, ?> avgCreditColumn;
    @FXML private TableColumn<?, ?> avgAgeColumn;
    // |                                           | //
    @FXML private Button addTeamButton;
    @FXML private Button manageButton;
    @FXML private Button deleButton;
    @FXML private Button closeButton;
    // --------------------------------------------- //


    public Teams getTeams(){
        return model;
    }
    private Team getTeam() { 
        return groupsTv.getSelectionModel().getSelectedItem();
    }


    @FXML private void initialize() {
        // UI: 1/4 width for each col.
        Double w = 0.245;
        teamNameColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        numPlayersColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        avgCreditColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        avgAgeColumn.prefWidthProperty().bind(groupsTv.widthProperty().multiply(w));
        
        // INIT BTNS DISABLED STATE
        addTeamButton.setDisable(false);
        manageButton.setDisable(true);
        deleButton.setDisable(true);
        // UPDATED DISABLED STATE
        groupsTv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isTeamSelected = newSelection != null;
            manageButton.setDisable(!isTeamSelected);
            deleButton.setDisable(!isTeamSelected);
            addTeamButton.setDisable(isTeamSelected);
        });

        // ADD ROWS
        groupsTv.setItems(model.teams);

        final Team[] lastClicked = {null};
        // 2nd click DESELECTION
        groupsTv.setRowFactory(tv -> {
            TableRow<Team> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    if (event.getClickCount() == 1 && row.getItem().equals(lastClicked[0])) {
                        groupsTv.getSelectionModel().clearSelection();
                        lastClicked[0] = null;
                    } else {
                        lastClicked[0] = row.getItem();
                    }
                }
            });
            return row;
        });
        
        // UNSELECT WHEN manage or delete BTN CLICKED
        manageButton.setOnMouseClicked(event -> {
            groupsTv.getSelectionModel().clearSelection();
            lastClicked[0] = null;
        });
        deleButton.setOnMouseClicked(event -> {
            groupsTv.getSelectionModel().clearSelection();
            lastClicked[0] = null;
        });
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
            String title = "Managing Team: "+getTeam().getName(); 
            // I want to pass both the teams and the team to the ManageTeamController

            List<Object> data = new ArrayList<>();
            data.add(getTeams());
            data.add(getTeam());
            ViewLoader.showStage(data, "/view/ManageTeamView.fxml", title, stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void handleDeleteTeam() {
        getTeams().remove(getTeam());
    }

    @FXML
    public void close() {
        stage.close();
    }
}
