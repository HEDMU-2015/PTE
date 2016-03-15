package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

import Exceptions.BoejningsMomentException;
import Exceptions.LaengdeException;
import Logic.LaengdeRetning;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PaneUC3Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	private String error = "-fx-background-color: pink;";
	private String css = "@util/gui.css";


	private boolean laengdeErAEndret = false;
	private boolean boejningsmomentErAEndret = false;

	@FXML
	private TextField tekstFeltLaengde;

	@FXML
	private TextField tekstFeltBoejningsmoment;

	@FXML
	private TextField tekstFeltKraft;

	@FXML
	private RadioButton vinkelretPaaFt;

	@FXML
	private RadioButton vinkelretPaaFdim;

	@FXML
	private ToggleGroup laengdeRetning;

	@FXML
	public void haandterUdregnKnap() {
		setLaengdeRetning();
		tekstFeltBoejningsmoment.setStyle(css);
		tekstFeltLaengde.setStyle(css);

		if (laengdeErAEndret) {
			try{
			laengdeErAEndret = false;
			pteController.setLaengde(tekstfeltFormat.formaterStringTilDouble(tekstFeltLaengde.getText()));
			} catch (LaengdeException e){
				tekstFeltLaengde.setStyle(error);
			}
			
		}
		if (boejningsmomentErAEndret) {
			try{
			boejningsmomentErAEndret = false;
			pteController.setBoejningsMoment(tekstfeltFormat.formaterStringTilDouble(tekstFeltBoejningsmoment.getText()));
			} catch (BoejningsMomentException e){
				tekstFeltBoejningsmoment.setStyle(error);
			}
		}		
	}

	@FXML
	public void haandterResetKnap() {
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.LAENGDE)) {
			tekstFeltLaengde.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getLaengde()));

			tekstFeltBoejningsmoment
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment()));
		}
		
		if(tilstande.contains(Tilstand.BOEJNINGSMOMENT)) {
			tekstFeltBoejningsmoment
			.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment()));
		}
		
	}

	private void setLaengdeRetning() {
		if (vinkelretPaaFt.isSelected()) {
			tekstFeltKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			pteController.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		} else {
			tekstFeltKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
			pteController.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FDIM);
		}
	}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltBoejningsmoment);
		formaterTekstfelt(tekstFeltLaengde);
		
		tekstFeltLaengde.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				laengdeErAEndret = true;
			}
		});
		
		tekstFeltBoejningsmoment.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				boejningsmomentErAEndret = true;
			}
		});
	}
	
	

}
