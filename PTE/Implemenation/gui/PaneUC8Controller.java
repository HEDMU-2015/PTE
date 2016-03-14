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

public class PaneUC8Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	private boolean arealErAEndret = false;

	@FXML
	private TextField tekstFeltSigmaB;
	
	@FXML
	private TextField tekstFeltSigmaN;
	
	@FXML
	private TextField tekstFeltSigmaRef;
	
	@FXML
	public void haandterUdregnKnap() {
		tekstFeltSigmaRef.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()));
	}
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.SIGMAB)) {
			tekstFeltSigmaB.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()));
		}
		if (tilstande.contains(Tilstand.SIGMAN)){
			tekstFeltSigmaN.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN()));
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}