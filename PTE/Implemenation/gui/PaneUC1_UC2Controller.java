package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import Logic.Profil;
import Logic.Tilstand;

public class PaneUC1_UC2Controller extends PTEPane implements Initializable, Observer {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

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
		if(!tekstFeltVinkel.getText().isEmpty()){
			setProfil();
			setData();
			getTextFn_FT();
		}else{
			regneVaegtEllerDimensionerendeKraft();
		}
	}


	@FXML
	private void haandterResetKnap() {

		this.tekstFeltVaegt.setText("");
		this.tekstFeltDimensionerendeKraft.setText("");
		this.tekstFeltVinkel.setText("");
		this.tekstFeltNormalkraft.setText("");
		this.tekstFeltForskydningskraft.setText("");

	}
	
	@FXML
	private void haandterCleartekstFeltDimensionerndeKraft(){
		this.tekstFeltDimensionerendeKraft.setText("");

	}
	
	@FXML
	private void haandterCleartekstFeltVaegt(){
		this.tekstFeltVaegt.setText("");

	}

	private void formaterTekstfelt(TextField input){
		tekstfeltFormat.formaterTekstfeltInput(input);
	}

	private void getTextFn_FT() {
		tekstFeltNormalkraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
		tekstFeltForskydningskraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));		
	}

	private void setData() {
		pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
		regneVaegtEllerDimensionerendeKraft();
		
	}

	private void regneVaegtEllerDimensionerendeKraft() {

		if(tekstFeltDimensionerendeKraft.getText().isEmpty()){
			
			
			pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
			tekstFeltDimensionerendeKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));

		}else{			
			pteController.setDimensioneredndeKraft(tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
			pteController.dimensionerendeKraftTilVaegt();
			tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}	
	}

	private void setProfil() {
		if(vandret.isSelected()){
			pteController.setProfil(Profil.VANDRET);
			System.out.println("vandret");
		}else{
			pteController.setProfil(Profil.LODRET);
			System.out.println("lodred");
		}
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltDimensionerendeKraft);
		formaterTekstfelt(tekstFeltVaegt);
		formaterTekstfelt(tekstFeltVinkel);

	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if(tilstande.contains(Tilstand.VAEGT)){
			pteController.dimensionerendeKraftTilVaegt();
			tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}

		if(tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)){
			tekstFeltDimensionerendeKraft.setText
			(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
		}

		if(tilstande.contains(Tilstand.FORSKYDNINGSKRAFT)){
			tekstFeltForskydningskraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
		}

		if(tilstande.contains(Tilstand.NORMALKRAFT)){
			tekstFeltNormalkraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
		}

	}

}
