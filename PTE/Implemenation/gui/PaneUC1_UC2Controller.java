package gui;

import java.net.URL;
import java.util.ResourceBundle;

import Logic.PTEController;
import Logic.PTEControllerImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import Logic.Profil;

public class PaneUC1_UC2Controller implements Initializable{
private PTEController pteController = new PTEControllerImpl();
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
	private void haandterUdregnKnap(ActionEvent event) {
		
		getProfil();
		getData();
		setTextFn_FT();
	}
	
	@FXML
	private void haandterResetKnap(ActionEvent event) {
		
		this.tekstFeltVaegt.setText("");
		this.tekstFeltDimensionerendeKraft.setText("");
		this.tekstFeltVinkel.setText("");
		this.tekstFeltNormalkraft.setText("");
		this.tekstFeltForskydningskraft.setText("");
		
	}
	
	private void setTextFn_FT() {
		tekstFeltNormalkraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
		tekstFeltForskydningskraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
		
	}

	private void formaterTekstfelt(TextField input){
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	
	private void getData() {
		pteController.setVinkel(tekstfeltFormat.formaterStringTilDouble(tekstFeltVinkel.getText()));
		
		if(tekstFeltDimensionerendeKraft.getText().isEmpty()){
			pteController.setVaegt(tekstfeltFormat.formaterStringTilDouble(tekstFeltVaegt.getText()));
			tekstFeltDimensionerendeKraft.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
		}else if(tekstFeltVaegt.getText().isEmpty()){
			pteController.setDimensioneredndeKraft(tekstfeltFormat.formaterStringTilDouble(tekstFeltDimensionerendeKraft.getText()));
			pteController.dimensionerendeKraftTilVaegt();
			tekstFeltVaegt.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}
		
	}




	private void getProfil() {
		if(vandret.isSelected()){
			pteController.setProfil(Profil.VANDRET);
		}else{
			pteController.setProfil(Profil.LODRET);
		}
		
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstFeltDimensionerendeKraft);
		formaterTekstfelt(tekstFeltVaegt);
		formaterTekstfelt(tekstFeltVinkel);
		
	}

}
