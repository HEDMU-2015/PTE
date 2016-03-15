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

public class PaneUC7Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	@FXML
	private TextField tekstFeltForskydningspunkt;
	
	@FXML
	private TextField tekstFeltIntertimoment;
	
	@FXML
	private TextField tekstFeltSigmaB;
	
	@FXML
	public void haandterUdregnKnap() {
		pteController.setForskydningspunkt(Double.parseDouble(tekstFeltForskydningspunkt.getText()));
		pteController.setInertimoment(Double.parseDouble(tekstFeltIntertimoment.getText()));
		tekstFeltSigmaB.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()));
		pteController.setSigmaB(pteController.getSigmaB());
	}
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
			}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltForskydningspunkt);
		formaterTekstfelt(tekstFeltIntertimoment);
		formaterTekstfelt(tekstFeltSigmaB);
	}

}
