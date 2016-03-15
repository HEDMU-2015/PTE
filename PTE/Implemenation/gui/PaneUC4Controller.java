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

public class PaneUC4Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	private boolean arealErAEndret = false;
	
	private boolean forskydningsspaendingenErAEndret = false;

	@FXML
	private TextField tekstFeltAreal;

	@FXML
	private TextField tekstFeltForskydningsspændingen;

	@FXML
	private Label labelKraft;

	@FXML
	public void haandterUdregnKnap() {
		labelKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
		if (arealErAEndret) {
			arealErAEndret = false;
			pteController.setIndtastAreal(tekstfeltFormat.formaterStringTilDouble(tekstFeltAreal.getText()));
		}
		tekstFeltForskydningsspændingen.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()));
	}
	
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.INDTAST_AREAL)) {
			tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getAreal()));

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
