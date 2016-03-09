package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.LaengdeRetning;
import Logic.Profil;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PaneUC3Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	
	private boolean laengdeErAEndret = false;
	private boolean f;
	
	@FXML
	private TextField tekstFeltLaengde;
	
	@FXML
	private TextField tekstFeltBoejningsmoment;
	
	@FXML
	private Label labelKraft;
	
	@FXML
	private RadioButton vinkelretPåFt;
	
	@FXML
	private RadioButton vinkelretPåFdim;
	
	public void haandterUdregnKnap () {
		if (laengdeErAEndret) {
			laengdeErAEndret = false;
			pteController.setLaengde(tekstfeltFormat.formaterStringTilDouble(tekstFeltLaengde.getText()));
		}
		
		if ((vinkelretPåFdim.isSelected() && pteController.getLaengdeRetning() != LaengdeRetning.VINKELRET_TIL_FT)
				|| (vinkelretPåFt.isSelected() && pteController.getLaengdeRetning() != LaengdeRetning.VINKELRET_TIL_FDIM)) {
			setLaengdeRetning();
			if (pteController.getLaengdeRetning() == LaengdeRetning.VINKELRET_TIL_FT) {
				labelKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
				
			}
			
			if (pteController.getLaengdeRetning() == LaengdeRetning.VINKELRET_TIL_FDIM) {
				labelKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
			}
		}
	}
	
	public void haandterResetKnap () {
		pteController.nulstil();
	}
	
	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.LAENGDE)) {
			tekstFeltLaengde.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getLaengde()));

			tekstFeltBoejningsmoment.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment()));	
		}		
	}
	
	private void setLaengdeRetning() {
		if (vinkelretPåFt.isSelected()) {
			pteController.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		} else {
			pteController.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FDIM);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tekstFeltLaengde.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				laengdeErAEndret = true;
			}
		});
	}

	
	
}