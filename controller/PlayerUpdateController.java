package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.edu.uts.ap.javafx.*;


public class PlayerUpdateController extends Controller<Player> {

    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField noTf;
    
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button closeButton;

    public final Player getPlayer() { return model; }
	private String getName() { return nameTf.getText(); }
    private Double getCredit() { return Double.parseDouble(creditTf.getText()); }
    private int getAge() { return Integer.parseInt(ageTf.getText()); }
    private int getNo() { return Integer.parseInt(noTf.getText()); }

    // UTILITY METHOD   --------------------------------------
    public static Stage newStage(String imageName) {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/view/" + imageName));
        return stage;
    }
    // -------------------------------------------------------

    @FXML public void initialize() {
        Boolean isPlayerSelected = model != null;
        updateButton.setDisable(!isPlayerSelected);
        addButton.setDisable(isPlayerSelected);
        if (isPlayerSelected) {
            nameTf.setText(getPlayer().getName());
            creditTf.setText(Double.toString(getPlayer().getCredit()));
            ageTf.setText(Integer.toString(getPlayer().getAge()));
            noTf.setText(Integer.toString(getPlayer().getNo()));
        } else {
            nameTf.setText("");
            creditTf.setText("-1.0");
            ageTf.setText("-1");
            noTf.setText("-1");
        }
    }

    private String validatePlayer(Player player) {
        Validator v = new Validator();
        v.clear();
        v.generateErrors(getName(), Double.toString(getCredit()), Integer.toString(getAge()), Integer.toString(getNo()));
        String errorMsg = "";
        if (v.errors().size() > 0) {
            for (String error : v.errors()) {
                errorMsg += error + "\n";
            }
        }
        return errorMsg;
    }

    private void openErrorStage(String errorMsg, String title) {
        try {
            Stage stage = newStage("error.png");
            ViewLoader.showStage(errorMsg, "/view/error.fxml", title, stage);
        } catch (IOException ex) {
            Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML private void addPlayer() {
        Player newPlayer = new Player(getName(), getCredit(), getAge(), getNo());
        String errorMsg = validatePlayer(newPlayer);
   
        if (!errorMsg.isEmpty()) {
            openErrorStage(errorMsg, "Input Errors!");
            return;
        }
        
        // FIXME
        model = newPlayer;
        stage.close();
    }

    @FXML private void updatePlayer() {
        Player newPlayer = new Player(getName(), getCredit(), getAge(), getNo());
        String errorMsg = validatePlayer(newPlayer);
   
        if (!errorMsg.isEmpty()) {
            openErrorStage(errorMsg, "Input Errors!");
            return;
        }
        
        getPlayer().update(getName(), getCredit(), getAge(), getNo());
        // FIXME
        stage.close();
    }

    @FXML private void close() {
        stage.close();
    }
}
