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
        nameTf.setText(model.getName());
        creditTf.setText(Double.toString(model.getCredit()));
        ageTf.setText(Integer.toString(model.getAge()));
        noTf.setText(Integer.toString(model.getNo()));
    }

	@FXML private void addTeam() {
        String errorMsg = "";
        Validator v = new Validator();
        v.clear();
        v.generateErrors(getName());
        //if (v.errors().size() > 0) {
        //    errorMsg = v.errors().toArray(new String[0])[v.errors().size() - 1];
        //}
        //if (getTeams().hasTeam(getName())) {
        //    errorMsg = getName() + " Team already exists";
        //}
        if (!errorMsg.isEmpty()) {
            try {
                Stage stage = newStage("error.png");
                ViewLoader.showStage(errorMsg, "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(PlayerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        model.setName(getName());
        model.setCredit(getCredit());
        model.setAge(getAge());
        model.setNo(getNo());

		stage.close();
	}
    
    @FXML private void updatePlayer() {
        model.setName(getName());
        model.setCredit(getCredit());
        model.setAge(getAge());
        model.setNo(getNo());
        stage.close();
    }

    @FXML private void addPlayer() {
    }

    @FXML private void close() {
        stage.close();
    }
}