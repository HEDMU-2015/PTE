package gui;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.SigmaBException;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class PaneUC7Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	private boolean forskydningspunktErAEndret = false;
	private boolean intertimomentErAEndret= false;
	private boolean sigmaBErAEndret = false;
	private static final String ERROR = "-fx-background-color: red;";	
	private static final String CSS = "@util/gui.css";

	
	@FXML
	private TextField tekstFeltForskydningspunkt;
	
	@FXML
	private TextField tekstFeltIntertimoment;
	
	@FXML
	private TextField tekstFeltSigmaB;
	
	@FXML
	public void haandterUdregnKnap() {
		
		tekstFeltSigmaB.setStyle(CSS);

		
		if(forskydningspunktErAEndret) {			
			forskydningspunktErAEndret = false;
			pteController.setForskydningspunkt(tekstfeltFormat.formaterStringTilDouble(tekstFeltForskydningspunkt.getText()));
		}
		
		if (intertimomentErAEndret) {
			intertimomentErAEndret = false;
			pteController.setInertimoment(tekstfeltFormat.formaterStringTilDouble(tekstFeltIntertimoment.getText()));
		}
	
		if (sigmaBErAEndret) {
			try{
			sigmaBErAEndret = false;
			pteController.setSigmaB(tekstfeltFormat.formaterStringTilDouble(tekstFeltSigmaB.getText()));
			} catch(SigmaBException e){
				tekstFeltSigmaB.setStyle(ERROR);
			}
		}
		
	}
	@FXML
	public void haandterResetKnap() { 
		tekstFeltSigmaB.setStyle(CSS);

		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.FORSKYDNINGSPUNKT)) {
			tekstFeltForskydningspunkt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningspunkt()));
		}
		
		if (tilstande.contains(Tilstand.INERTIMOMENT)) {
			tekstFeltIntertimoment.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getInertimoment()));
		}
		
		if (tilstande.contains(Tilstand.SIGMAB)) {
			tekstFeltSigmaB.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()));
		}
			}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltForskydningspunkt);
		formaterTekstfelt(tekstFeltIntertimoment);
		formaterTekstfelt(tekstFeltSigmaB);
		
		tekstFeltForskydningspunkt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				forskydningspunktErAEndret = true;
			}
		});
		
		tekstFeltIntertimoment.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				intertimomentErAEndret = true;
			}
		});
		
		tekstFeltSigmaB.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				sigmaBErAEndret = true;
			}
		});
	}

}
