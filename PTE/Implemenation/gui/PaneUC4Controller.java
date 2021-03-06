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

public class PaneUC4Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	private boolean arealErAEndret = false;
	
	private boolean forskydningsspaendingenErAEndret = false;
	
	private static final String ERROR = "-fx-background-color: red;";
	
	private static final String CSS = "@util/gui.css";


	@FXML
	private TextField tekstFeltAreal;

	@FXML
	private TextField tekstFeltForskydningsspændingen;

	@FXML
	private TextField tekstFeltKraft;

	@FXML
	public void haandterUdregnKnap() {
		
		tekstFeltAreal.setStyle(CSS);
		tekstFeltKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
		if (arealErAEndret) {
			try{
			arealErAEndret = false;
			pteController.setIndtastAreal(tekstfeltFormat.formaterStringTilDouble(tekstFeltAreal.getText()));
			} catch(ArealException e) {
				
				tekstFeltAreal.setStyle(ERROR);				
			}
		}

		if(forskydningsspaendingenErAEndret) {
			
			forskydningsspaendingenErAEndret = false;
			pteController.setTau_ForskydningsSpaending(tekstfeltFormat.formaterStringTilDouble(tekstFeltForskydningsspændingen.getText()));
			
		}
	}
	
	@FXML
	public void haandterResetKnap() { 
		tekstFeltAreal.setStyle(CSS);

		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.INDTAST_AREAL)) {
			tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()));

			tekstFeltForskydningsspændingen
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()));
		}
	}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltAreal);
		formaterTekstfelt(tekstFeltForskydningsspændingen);
		
		
		tekstFeltAreal.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				arealErAEndret = true;
			}
		});
		
		tekstFeltForskydningsspændingen.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				forskydningsspaendingenErAEndret = true;
			}
		});
	}

}
