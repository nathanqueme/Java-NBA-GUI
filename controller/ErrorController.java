package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import au.edu.uts.ap.javafx.Controller;

public class ErrorController extends Controller<String> {
    public final String getErrorMsg() { 
        return model;
    }

    @FXML private Text errorMessage;

    public void initialize() {
        errorMessage.setText(getErrorMsg());
    }

    @FXML private Button closeButton;

    @FXML
    public void close() {
        stage.close();
    }
}

