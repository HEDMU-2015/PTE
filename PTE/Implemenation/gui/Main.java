package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application{
	private Stage stage;
	private BorderPane mainWindow;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setTitle("PTE Calculator");
		
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("PaneUC1_UC2.fxml"));
		try {
			AnchorPane paneUC1_UC2 = (AnchorPane)loader.load();
			VBox vboxICenter = new VBox(); 
			TitledPane tpUC1_UC2 = new TitledPane("Normalkraft / Forskydningskraft",paneUC1_UC2);
			
			vboxICenter.getChildren().add(tpUC1_UC2);
			
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



}
