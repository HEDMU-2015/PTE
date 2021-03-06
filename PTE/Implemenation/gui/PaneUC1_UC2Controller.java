package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Exceptions.DimensionerendeKraftException;
import Exceptions.VaegtException;
import Exceptions.VinkelException;
import Logic.Profil;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PaneUC1_UC2Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	private boolean vaegtErAEndret = false;
	private boolean dimensionerendeKraftErAEndret = false;
	private boolean vinkelErAEndret = false;
	private static final String ERROR = "-fx-background-color: red;";
	private static final String CSS = "@util/gui.css";


	@FXML
	private TextField tekstFeltVaegt;

	@FXML
	private TextField tekstFeltDimensionerendeKraft;

	@FXML
	private TextField tekstFeltVinkel;

	@FXML
	private TextField tekstFeltNormalkraft;

	@FXML
	private TextField tekstFeltForskydningskraft;

	@FXML
	private RadioButton vandret;

	@FXML
	private RadioButton lodret;

	@FXML
	private ToggleGroup profil;

	@FXML
	private void haandterUdregnKnap() {
		
		tekstFeltVinkel.setStyle(CSS);
		tekstFeltVaegt.setStyle(CSS);
		tekstFeltDimensionerendeKraft.setStyle(CSS);

		if (vaegtErAEndret) {
			try{
			vaegtErAEndret = false;

			pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
			}catch(VaegtException e){
				tekstFeltVaegt.setStyle(ERROR);
			}
		}

		if (dimensionerendeKraftErAEndret) {
			try{
			dimensionerendeKraftErAEndret = false;

			pteController.setDimensioneredndeKraft(
					tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
			}catch(DimensionerendeKraftException e){
				tekstFeltDimensionerendeKraft.setStyle(ERROR);
			}
		}

		if (vinkelErAEndret) {
			try{
				vinkelErAEndret = false;

				pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
			}catch(VinkelException e){
				tekstFeltVinkel.setStyle(ERROR);
			}
	
		}

		if ((vandret.isSelected() && pteController.getProfil() != Profil.VANDRET)
				|| (lodret.isSelected() && pteController.getProfil() != Profil.LODRET)) {

			setProfil();
		}
	}

	@FXML
	private void haandterResetKnap() {
		tekstFeltVinkel.setStyle(CSS);
		tekstFeltVaegt.setStyle(CSS);
		tekstFeltDimensionerendeKraft.setStyle(CSS);
		pteController.nulstil();
	}

	@FXML
	private void haandterCleartekstFeltDimensionerndeKraft() {
		this.tekstFeltDimensionerendeKraft.setText("");
	}

	@FXML
	private void haandterCleartekstFeltVaegt() {
		this.tekstFeltVaegt.setText("");
	}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}

	private void setProfil() {
		if (vandret.isSelected()) {
			pteController.setProfil(Profil.VANDRET);
		} else {
			pteController.setProfil(Profil.LODRET);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltDimensionerendeKraft);
		formaterTekstfelt(tekstFeltVaegt);
		formaterTekstfelt(tekstFeltVinkel);

		tekstFeltVaegt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				vaegtErAEndret = true;
			}
		});

		tekstFeltDimensionerendeKraft.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				dimensionerendeKraftErAEndret = true;
			}
		});

		tekstFeltVinkel.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				vinkelErAEndret = true;
			}
		});
	}

	@Override
	public void update(List<Tilstand> tilstande) {

		if (tilstande.contains(Tilstand.VAEGT)) {

			tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}

		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)) {

			tekstFeltDimensionerendeKraft
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));

			tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}

		if (tilstande.contains(Tilstand.FORSKYDNINGSKRAFT)) {

			tekstFeltForskydningskraft
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
		}

		if (tilstande.contains(Tilstand.NORMALKRAFT)) {

			tekstFeltNormalkraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
		}

		if (tilstande.contains(Tilstand.VINKEL)) {
			
			tekstFeltVinkel.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()));
		}
	}

}
