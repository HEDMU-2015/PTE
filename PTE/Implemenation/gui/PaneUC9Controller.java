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
import javafx.scene.layout.BackgroundFill;

public class PaneUC9Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	private boolean flydespaendingErAEndret = false;
	private boolean sigmaRefErAEndret = false;
	private boolean sikkerhedsFaktorErAEndret = false;
	private static final String ADVARSEL = "-fx-background-color: pink;";	
	private static final String CSS = "@util/gui.css";
	
	
	@FXML
	private TextField tekstFlydeSpaending;
	
	@FXML
	private TextField tekstFeltSigmaRef;
	
	@FXML
	private TextField tekstFeltSikkerhedsFaktor;
	
	@FXML
	public void haandterUdregnKnap() {
		tekstFeltSikkerhedsFaktor.setStyle(CSS);

		if(flydespaendingErAEndret){
			flydespaendingErAEndret = false;
		pteController.setFlydespaending(tekstfeltFormat.formaterStringTilDouble(tekstFlydeSpaending.getText()));
		
		}		
	}
	@FXML
	public void haandterResetKnap() { 
		tekstFeltSikkerhedsFaktor.setStyle(CSS);
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.SIGMA_REF)) {
			tekstFeltSigmaRef.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()));
		}
		
		if(tilstande.contains(Tilstand.FLYDESPAENDING)){
			tekstFlydeSpaending.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getFlydespaending()));
		}
		
		if(tilstande.contains(Tilstand.SIKKERHEDSFAKTOR)){
			tekstFeltSikkerhedsFaktor.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getSikkerhedsfaktor()));

			if(tekstFeltSikkerhedsFaktor.getText()!= "" && pteController.erSikkerhedsfaktorForLavt()){
				tekstFeltSikkerhedsFaktor.setStyle(ADVARSEL);
			}
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
