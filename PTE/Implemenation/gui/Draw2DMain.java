package gui;

import Logic.Profil;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Draw2DMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 400, 515);
		KranTegner tegner = new KranTegner(400, 515);
		root.getChildren().add(tegner.getNode());
		stage.setScene(scene);
		stage.show();
	}

}
