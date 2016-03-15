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
	private boolean flydespaendingErAEndret = false;
	private boolean sigmaRefErAEndret = false;
	private boolean sikkerhedsFaktorErAEndret = false;
	@FXML
	private TextField tekstFlydeSpaending;
	
	@FXML
	private TextField tekstFeltSigmaRef;
	
	@FXML
	private TextField tekstFeltSikkerhedsFaktor;
	
	@FXML
	public void haandterUdregnKnap() {
		pteController.setFlydespaending(tekstfeltFormat.formaterStringTilDouble(tekstFlydeSpaending.getText()));
		pteController.setSigmaRef(tekstfeltFormat.formaterStringTilDouble(tekstFeltSigmaRef.getText()));
		pteController.getSikkerhedsfaktor();
	}
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.SIGMA_REF)) {
			tekstFeltSigmaRef.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()));
		}
		if(tilstande.contains(Tilstand.SIKKERHEDSFAKTOR)){
			tekstFeltSikkerhedsFaktor.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSikkerhedsfaktor()));
		}
			}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFlydeSpaending);
		formaterTekstfelt(tekstFeltSikkerhedsFaktor);
		formaterTekstfelt(tekstFeltSigmaRef);
		
		tekstFeltSigmaRef.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				sigmaRefErAEndret = true;
			}
		});
		
		tekstFlydeSpaending.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				flydespaendingErAEndret = true;
			}
		});
		
		tekstFeltSikkerhedsFaktor.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				sikkerhedsFaktorErAEndret = true;
			}
		});
	}

}
