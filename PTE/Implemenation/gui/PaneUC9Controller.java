package gui;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaneUC9Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	@FXML
	private TextField tekstFlydeSpænding;
	
	@FXML
	private TextField tekstFeltSigmaRef;
	
	@FXML
	private TextField tekstFeltSikkerhedsFaktor;
	
	@FXML
	public void haandterUdregnKnap() {
		pteController.setF
	}
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
			}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
