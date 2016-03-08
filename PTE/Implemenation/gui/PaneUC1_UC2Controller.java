package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.Profil;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class PaneUC1_UC2Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	// private Stage window;

	private boolean vaegtErAEndret = false;
	private boolean dimensionerendeKraftErAEndret = false;

	private boolean vinkelErAEndret = false;

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
	private Toggle vandret;

	@FXML
	private Toggle lodret;

	@FXML
	private ToggleGroup profil;

	@FXML
	private void haandterUdregnKnap() {
		if (vaegtErAEndret ) {

		//if (vaegtErAEndret && tekstFeltDimensionerendeKraft.getText().isEmpty()) {
			vaegtErAEndret = false;

			pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
		}

		if (!tekstFeltDimensionerendeKraft.getText().isEmpty() && tekstFeltVaegt.getText().isEmpty()) {
			dimensionerendeKraftErAEndret = false;

			pteController.setDimensioneredndeKraft(
					tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
		}
		// System.out.println("udregn: "+ vaegtErAEndret);

		if (!tekstFeltVaegt.getText().isEmpty() && !tekstFeltVinkel.getText().isEmpty()) {
			pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
			pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
		}

		if (!tekstFeltDimensionerendeKraft.getText().isEmpty() && !tekstFeltVinkel.getText().isEmpty()) {
			pteController.setDimensioneredndeKraft(
					tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
			pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
		}

		/*
		 * if(vaegtErAEndret){ vaegtErAEndret=false;
		 * pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(
		 * tekstFeltVaegt.getText())); } if(dimensionerendeKraftErAEndret){
		 * dimensionerendeKraftErAEndret=false;
		 * pteController.setDimensioneredndeKraft(tekstfeltFormat.
		 * formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText())); }
		 */
		if (vinkelErAEndret) {
			// if( vinkelErAEndret && !tekstFeltVinkel.getText().isEmpty())
			// eller tekstFormatting return 0.0 : ellers når man click vinkel og
			// udregns knap, får man NumberformatException
			vinkelErAEndret = false;
			pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
		}

		if ((vandret.isSelected() && pteController.getProfil() != Profil.VANDRET)
				|| (lodret.isSelected() && pteController.getProfil() != Profil.LODRET)) {
			setProfil();
		}

		// if ((tekstFeltVaegt.getText().isEmpty())&&
		// (tekstFeltDimensionerendeKraft.getText().isEmpty())){
		// DialogBox alert = new DialogBox(window);
		// alert.visAdvarselDialog();
		//
		// } else if(tekstFeltVinkel.getText().isEmpty()){
		// regneVaegtEllerDimensionerendeKraft();
		//
		// } else {
		// setProfil();
		// setData();
		// getTextFn_FT();
		// }
	}

	@FXML
	private void haandterResetKnap() {
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

	// private void getTextFn_FT() {
	// tekstFeltNormalkraft.setText
	// (tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
	// tekstFeltForskydningskraft.setText
	// (tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
	// }

	// private void setData() {
	// regneVaegtEllerDimensionerendeKraft();
	//
	// pteController.setVinkel
	// (tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
	//
	// }

	// private void regneVaegtEllerDimensionerendeKraft() {
	//
	// if(tekstFeltDimensionerendeKraft.getText().isEmpty()){
	//
	// pteController.setVaegt
	// (tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
	// tekstFeltDimensionerendeKraft.setText
	// (tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
	//
	// }else if(tekstFeltVaegt.getText().isEmpty()){
	// pteController.setDimensioneredndeKraft
	// (tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
	// double vaegt = pteController.dimensionerendeKraftTilVaegt();
	// pteController.setVaegt(vaegt);
	// tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(vaegt));
	// }
	// }

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
		System.out.println("Update " + tilstande);
		
		
		if (tilstande.contains(Tilstand.VAEGT)) {
			
				tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));

				tekstFeltDimensionerendeKraft
				.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
			
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
		
		
		
		
		/*if (tilstande.contains(Tilstand.VAEGT)) {
			if (pteController.getVaegt() == 0.0) {
				haandterCleartekstFeltVaegt();
			} else {
				tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));

				tekstFeltDimensionerendeKraft
				.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
			}
		}

		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)) {
			if (pteController.getDimensionerendeKraft() == 0.0) {
				haandterCleartekstFeltDimensionerndeKraft();
			}else{
				tekstFeltDimensionerendeKraft
				.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));

				tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
			}
		}

		if (tilstande.contains(Tilstand.FORSKYDNINGSKRAFT)) {

			if (pteController.getForskydningkraft() == 0.0) {
				haandterCleartekstFeltForskydningsKraft();
			}else{
				if (pteController.getForskydningkraft() == (double) 0 && tekstFeltVinkel.getText().isEmpty()) {
					haandterCleartekstFeltForskydningsKraft();
				} else {
					tekstFeltForskydningskraft
					.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
				}

			}
		}

		if (tilstande.contains(Tilstand.NORMALKRAFT)) {
			if (pteController.getNormalkraft() == 0.0) {
				haandterCleartekstFeltNormalKraft();
			}else{
				if (pteController.getNormalkraft() == (double) 0 && tekstFeltVinkel.getText().isEmpty()) {
					haandterCleartekstFeltNormalKraft();
				} else {
					tekstFeltNormalkraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
				}
			}
		}

		if (tilstande.contains(Tilstand.VINKEL)) {
			if (pteController.getVinkel() == 0.0) {
				haandterCleartekstFeltVinkel();
			}else{
				tekstFeltVinkel.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()));
			}
		}
	}*/

}
