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
	private void setName(String name) { nameTf.setText(name); }

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

        if (getTeams().hasTeam(getName())) {
            try {
                Stage stage = newStage("error.png");
                String errorMsg = getName() + " Team already exists";
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