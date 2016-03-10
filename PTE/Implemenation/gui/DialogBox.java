package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DialogBox {
	private Stage window;

	public DialogBox(Stage window){
		this.window = window;
	}
	
	public void visAdvarselDialog(){
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(window);
		alert.setTitle("ADVARSEL");
		alert.setHeaderText("Mangler nogle oplysninger");
		alert.setContentText("Tjek venligst tekstfelterne");
		alert.showAndWait();
	}

}
