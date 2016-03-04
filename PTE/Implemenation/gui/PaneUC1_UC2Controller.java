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
		
		System.out.println("vaegt : " + tekstFeltVaegt.getText());
		System.out.println("vinkel : " + tekstFeltVinkel.getText());
		System.out.println("newton : " + tekstFeltDimensionerendeKraft.getText());
		System.out.println("*****");


		
		getProfil();
		getData();
		setTextFn_FT();
		
		System.out.println("profil : " + pteController.getProfil());
		System.out.println("vaegt : " + pteController.getVaegt());
		System.out.println("vinkel : " + pteController.getVinkel());
		System.out.println("newton : " + pteController.getDimensionerendeKraft());
		System.out.println("--------");



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

}
