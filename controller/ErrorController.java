package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import au.edu.uts.ap.javafx.Controller;

public class ErrorController extends Controller<Object> {
    
    @FXML private Button closeButton;

    @FXML
    public void close() {
        stage.close();
    }
}

