package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import model.Season;
import model.Record;
import au.edu.uts.ap.javafx.*;

public class RecordController extends Controller<Season> {

    @FXML private TableView<Record> groupsTv;
    @FXML private TableColumn<Record, String> roundColumn;
    @FXML private TableColumn<Record, String> gameColumn;
    @FXML private TableColumn<Record, String> winColumn;
    @FXML private TableColumn<Record, String> loserColumn;
    @FXML private Button closeButton;

    public final Season getSeason() {
        return model;
    }

    @FXML public void initialize() {
        groupsTv.setItems(getSeason().record());
    }


    @FXML public void close() {
        stage.close();
    }
}
