package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.Observer;
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
		//get til set
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
		// TODO Auto-generated method stub
		
	}

}
