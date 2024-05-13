package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import java.util.ArrayList;
import model.Players;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import au.edu.uts.ap.javafx.*;


public class PlayerUpdateController extends Controller<List<Object>> {

    @FXML private TextField nameTf;
    @FXML private TextField creditTf;
    @FXML private TextField ageTf;
    @FXML private TextField noTf;
    
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button closeButton;

    public Players getPlayers() {
        Players players = (Players) model.get(0);
        return players; 
    }
    public Player getPlayer() { 
        Player player = (Player) model.get(1);
        return player; 
    }
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
        Boolean isPlayerSelected = !getPlayer().getName().isEmpty();
        updateButton.setDisable(!isPlayerSelected);
        addButton.setDisable(isPlayerSelected);
      
        nameTf.setText(getPlayer().getName());
        creditTf.setText(Double.toString(getPlayer().getCredit()));
        ageTf.setText(Integer.toString(getPlayer().getAge()));
        noTf.setText(Integer.toString(getPlayer().getNo()));
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

    public Boolean playerIsUnique(Player newPlayer) {
        List<String> playerNames = new ArrayList<>();
        List<Integer> playerNos = new ArrayList<>();
        for (Player player : getPlayers().getPlayersList()) {
            playerNames.add(player.getName());
            playerNos.add(player.getNo());
        }
        String errorMsg = "";
        if (playerNames.contains(newPlayer.getName()) && !newPlayer.getName().equals(getPlayer().getName())) {
            errorMsg += "Player name already exists\n";
        }
        if (newPlayer.getNo() != getPlayer().getNo()){
            if (playerNos.contains(newPlayer.getNo())) {
                errorMsg += "Player number already exists\n";
            }
        }
        if (!errorMsg.isEmpty()) {
            openErrorStage(errorMsg, "Input Errors!");
            return false;
        }
        return true;
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
        if (!playerIsUnique(newPlayer)) {
            return;
        }
        getPlayers().addPlayer(newPlayer);
        stage.close();
    }

    @FXML private void updatePlayer() {
        Player newPlayer = new Player(getName(), getCredit(), getAge(), getNo());
        String errorMsg = validatePlayer(newPlayer);
        if (!errorMsg.isEmpty()) {
            openErrorStage(errorMsg, "Input Errors!");
            return;
        }
        if (!playerIsUnique(newPlayer)) {
            return;
        }
        getPlayers().removePlayer(getPlayer());
        getPlayers().addPlayer(newPlayer);
        stage.close();
    }

    @FXML private void close() {
        stage.close();
    }
}
