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

	@FXML
	private TextField tekstFeltAreal;

	@FXML
	private TextField tekstFeltForskydningsspændingen;

	@FXML
	private Label labelKraft;

	public void haandterUdregnKnap() {
		if (arealErAEndret) {
			arealErAEndret = false;
			pteController.setAreal(tekstfeltFormat.formaterStringTilDouble(tekstFeltAreal.getText()));
		}
	}

	public void haandterResetKnap() {
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.AREAL)) {
			tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getAreal()));

			tekstFeltForskydningsspændingen
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()));
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tekstFeltAreal.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				arealErAEndret = true;
			}
		});
	}

}
