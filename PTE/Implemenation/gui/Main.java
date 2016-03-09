package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Logic.Observer;
import Logic.PTEController;
import Logic.PTEControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application  {
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void loadUC(){
		
		VBox vboxICenter = new VBox();
		PTEController pteController = new PTEControllerImpl();
		try {
			// Pane UC1 og UC2 
			FXMLLoader loaderUC1_UC2 = new FXMLLoader();
			loaderUC1_UC2.setLocation(Main.class.getResource("PaneUC1_UC2.fxml"));
			AnchorPane paneUC1_UC2 = (AnchorPane)loaderUC1_UC2.load();
			TitledPane tpUC1_UC2 = new TitledPane("Normalkraft / Forskydningskraft",paneUC1_UC2);
			PTEPane controllerPaneUC1_UC2 = loaderUC1_UC2.<PTEPane>getController();
			controllerPaneUC1_UC2.setPTEController(pteController);
			
			// Pane UC3
			FXMLLoader loaderUC3 = new FXMLLoader();
			loaderUC3.setLocation(Main.class.getResource("PaneUC3.fxml"));
			AnchorPane paneUC3 = (AnchorPane)loaderUC3.load();
			TitledPane tpUC3 = new TitledPane("Bøjningsmoment",paneUC3);
			tpUC3.setExpanded(false);
			PTEPane controllerPaneUC3 = loaderUC3.<PTEPane>getController();
			controllerPaneUC3.setPTEController(pteController);
			
			// Pane UC4
//			FXMLLoader loaderUC4 = new FXMLLoader();
//			loaderUC4.setLocation(Main.class.getResource("PaneUC4.fxml"));
//			AnchorPane paneUC4 = (AnchorPane)loaderUC4.load();
//			TitledPane tpUC4 = new TitledPane("Forskydningsspændingen",paneUC4);
//			tpUC4.setExpanded(false);
//			PTEPane controllerPaneUC4 = loaderUC4.<PTEPane>getController();
//			controllerPaneUC4.setPTEController(pteController);
			
			vboxICenter.getChildren().addAll(tpUC1_UC2, tpUC3);
			mainWindow.setCenter(vboxICenter);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public Stage getStage(){
		return stage;	
	}


}
