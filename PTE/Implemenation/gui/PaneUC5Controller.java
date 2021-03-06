package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.ArealException;
import Exceptions.ForskydningskraftException;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaneUC5Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	private boolean fnErAEndret = false;
	private boolean sigmaNErAEndret = false;
	private boolean arealErAEndret = false;
	
	private static final String ERROR = "-fx-background-color: red;";
	
	private static final String CSS = "@util/gui.css";


	@FXML
	private TextField tekstFeltFn;
	
	@FXML
	private TextField tekstFeltAreal;
	
	@FXML
	private TextField tekstFeltSigmaN;
	
	@FXML
	public void haandterUdregnKnap() {
		
		tekstFeltAreal.setStyle(CSS);
		tekstFeltFn.setStyle(CSS);


		if (arealErAEndret) {
			try{
			arealErAEndret = false;
			pteController.setIndtastAreal(tekstfeltFormat.formaterStringTilDouble(tekstFeltAreal.getText()));
			} catch (ArealException e){
				tekstFeltAreal.setStyle(ERROR);
			}
		}
		if (fnErAEndret) {
			try{
			fnErAEndret = false;
			pteController.setForskydningskraft(tekstfeltFormat.formaterStringTilDouble(tekstFeltFn.getText()));
			} catch (ForskydningskraftException e){
				tekstFeltFn.setStyle(ERROR);
			}
		}
		
		if (sigmaNErAEndret) {
			
			sigmaNErAEndret = false;
			pteController.setSigmaN(tekstfeltFormat.formaterStringTilDouble(tekstFeltSigmaN.getText()));
		}		
	}
	
	@FXML
	public void haandterResetKnap() { 
		tekstFeltAreal.setStyle(CSS);
		tekstFeltFn.setStyle(CSS);
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.INDTAST_AREAL)) {
			tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()));
		}
		
		if(tilstande.contains(Tilstand.NORMALKRAFT)){
			tekstFeltFn.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
		}
		
		if (tilstande.contains(Tilstand.SIGMAN)) {
			tekstFeltSigmaN.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN()));
		}
	}

	
	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltFn);
		formaterTekstfelt(tekstFeltAreal);
		formaterTekstfelt(tekstFeltSigmaN);
		
		tekstFeltFn.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				fnErAEndret = true;
			}
		});
		
		tekstFeltAreal.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				arealErAEndret = true;
			}
		});
		
		tekstFeltSigmaN.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				sigmaNErAEndret = true;
			}
		});
	}

}
