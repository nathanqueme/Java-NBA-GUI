package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Teams;
import model.Team;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.edu.uts.ap.javafx.*;


public class AddTeamController extends Controller<Teams> {
	public final Teams getTeams() { return model; }

	@FXML private TextField nameTf;

	private String getName() { return nameTf.getText(); }

    // UTILITY METHOD   --------------------------------------
    public static Stage newStage(String imageName) {
        Stage stage = new Stage();
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/view/" + imageName));
        return stage;
    }
    // -------------------------------------------------------

	@FXML private void addTeam() {
        String errorMsg = "";
        Validator v = new Validator();
        v.clear();
        v.generateErrors(getName());
        if (v.errors().size() > 0) {
            for (String error : v.errors()) {
                errorMsg += error + "\n";
            }
        }
        if (getTeams().hasTeam(getName())) {
            errorMsg += getName() + " Team already exists\n";
        }
        if (!errorMsg.isEmpty()) {
            try {
                Stage stage = newStage("error.png");
                ViewLoader.showStage(errorMsg, "/view/error.fxml", "Error!", stage);
            } catch (IOException ex) {
                Logger.getLogger(SeasonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        Team team = new Team(getName());
		getTeams().addTeam(team);
		stage.close();
	}
}