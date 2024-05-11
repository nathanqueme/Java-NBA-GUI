package controller;

import javafx.fxml.*;
import javafx.scene.control.*;
import model.Teams;
import model.Team;
import au.edu.uts.ap.javafx.*;


public class AddTeamController extends Controller<Teams> {
	public final Teams getTeams() { return model; }

	@FXML private TextField nameTf;

	private String getName() { return nameTf.getText(); }
	private void setName(String name) { nameTf.setText(name); }

	@FXML private void addTeam() {
        Team team = new Team(getName());
		getTeams().addTeam(team);
		stage.close();
	}
}