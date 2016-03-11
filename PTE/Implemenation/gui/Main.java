package gui;

import java.io.IOException;

import Logic.LogicFactoryImpl;
import Logic.PTEController;
import Logic.PTEControllerImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stage;
	private BorderPane mainWindow;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle("PTE Calculator");
		this.stage.getIcons().add(new Image("file:PTE/Implemenation/gui/util/simple_calculator_icon.png"));

		initMainWindow();

		loadUC();
	}

	private void initMainWindow() {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(Main.class.getResource("MainWindow.fxml"));
		try {
			mainWindow = (BorderPane) loader.load();
			Scene scene = new Scene(mainWindow);
			stage.setScene(scene);
			stage.show();
		} catch (IOException exc) {
		}
	}

	private void loadUC() {
		VBox vboxICenter = new VBox();
		PTEController pteController = new PTEControllerImpl(new LogicFactoryImpl());
		try {
			// Pane UC1 og UC2
			FXMLLoader loaderUC1_UC2 = new FXMLLoader();
			loaderUC1_UC2.setLocation(Main.class.getResource("PaneUC1_UC2.fxml"));
			AnchorPane paneUC1_UC2 = (AnchorPane) loaderUC1_UC2.load();
			TitledPane tpUC1_UC2 = new TitledPane("Normalkraft / Forskydningskraft", paneUC1_UC2);
			PTEPane controllerPaneUC1_UC2 = loaderUC1_UC2.<PTEPane> getController();
			controllerPaneUC1_UC2.setPTEController(pteController);

			// Pane UC3
			FXMLLoader loaderUC3 = new FXMLLoader();
			loaderUC3.setLocation(Main.class.getResource("PaneUC3.fxml"));
			AnchorPane paneUC3 = (AnchorPane) loaderUC3.load();
			TitledPane tpUC3 = new TitledPane("Bøjningsmoment", paneUC3);
			tpUC3.setExpanded(false);
			PTEPane controllerPaneUC3 = loaderUC3.<PTEPane> getController();
			controllerPaneUC3.setPTEController(pteController);

			// Pane UC4
			FXMLLoader loaderUC4 = new FXMLLoader();
			loaderUC4.setLocation(Main.class.getResource("PaneUC4.fxml"));
			AnchorPane paneUC4 = (AnchorPane) loaderUC4.load();
			TitledPane tpUC4 = new TitledPane("Forskydningsspændingen", paneUC4);
			tpUC4.setExpanded(false);
			PTEPane controllerPaneUC4 = loaderUC4.<PTEPane> getController();
			controllerPaneUC4.setPTEController(pteController);

			// Pane UC5
			FXMLLoader loaderUC5 = new FXMLLoader();
			loaderUC5.setLocation(Main.class.getResource("PaneUC5.fxml"));
			AnchorPane paneUC5 = (AnchorPane) loaderUC5.load();
			TitledPane tpUC5 = new TitledPane("SigmaN", paneUC5);
			tpUC5.setExpanded(false);
			PTEPane controllerPaneUC5 = loaderUC5.<PTEPane> getController();
			controllerPaneUC5.setPTEController(pteController);
						
			// Pane UC7
			FXMLLoader loaderUC7 = new FXMLLoader();
			loaderUC7.setLocation(Main.class.getResource("PaneUC7.fxml"));
			AnchorPane paneUC7 = (AnchorPane) loaderUC7.load();
			TitledPane tpUC7 = new TitledPane("SigmaB", paneUC7);
			tpUC7.setExpanded(false);
			PTEPane controllerPaneUC7 = loaderUC7.<PTEPane> getController();
			controllerPaneUC7.setPTEController(pteController);
			
			vboxICenter.getChildren().addAll(tpUC1_UC2, tpUC3, tpUC4, tpUC5, tpUC7);
			mainWindow.setCenter(vboxICenter);
		} catch (IOException exc) {
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getStage() {
		return stage;
	}

}
