package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

	private boolean laengdeErAEndret = false;
	private boolean boejningsmomentErAEndret = false;

	@FXML
	private TextField tekstFeltLaengde;

	@FXML
	private TextField tekstFeltBoejningsmoment;

	@FXML
	private Label labelKraft;

	@FXML
	private RadioButton vinkelretPaaFt;

	@FXML
	private RadioButton vinkelretPaaFdim;

	@FXML
	private ToggleGroup laengdeRetning;

	@FXML
	public void haandterUdregnKnap() {
		setLaengdeRetning();
		if (laengdeErAEndret) {
			laengdeErAEndret = false;
			pteController.setLaengde(tekstfeltFormat.formaterStringTilDouble(tekstFeltLaengde.getText()));
		}
		if (boejningsmomentErAEndret) {
			boejningsmomentErAEndret = false;
			pteController.setBoejningsMoment(tekstfeltFormat.formaterStringTilDouble(tekstFeltBoejningsmoment.getText()));
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
		System.out.println("test vi er i boejningsmoment");
//		if ()
	}

	private void setLaengdeRetning() {
		if (vinkelretPaaFt.isSelected()) {
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
		
		tekstFeltBoejningsmoment.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				boejningsmomentErAEndret = true;
			}
		});
	}
	
	

}
