package gui;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.ProfilType;
import Logic.Tilstand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PaneUC10Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();

	@FXML
	private TextField tekstDiameter;
	
	@FXML
	private TextField tekstFeltHoejde;
	
	@FXML
	private ToggleGroup profiltype;
	
	@FXML
	private TextField tekstFeltBredde;
	
	@FXML
	private TextField tekstFeltAreal;
	
	@FXML
	private TextField tekstFeltGodstykkelse;
	
	@FXML
	private RadioButton btnCirkel;
	
	@FXML
	private RadioButton btnHultRoer;
	
	@FXML
	private RadioButton btnKvadratRoer;
	
	@FXML
	private RadioButton btnKvadrat;
	
	
	@FXML
	public void haandterUdregnKnap() {
		pteController.setDiameter(tekstfeltFormat.formaterStringTilDouble(tekstDiameter.getText()));
		pteController.setHoejde(tekstfeltFormat.formaterStringTilDouble(tekstFeltHoejde.getText()));
		pteController.setBredde(tekstfeltFormat.formaterStringTilDouble(tekstFeltBredde.getText()));
		pteController.setDiameter(tekstfeltFormat.formaterStringTilDouble(tekstDiameter.getText()));
		pteController.setGodstykkelse(tekstfeltFormat.formaterStringTilDouble(tekstFeltGodstykkelse.getText()));
		
		if(btnCirkel.isSelected()){
			pteController.setProfilType(ProfilType.CIRKEL);
		} else if(btnHultRoer.isSelected()){
			pteController.setProfilType(ProfilType.HULT_ROER);
			
		} else if(btnKvadrat.isSelected()){
			pteController.setProfilType(ProfilType.KVADRAT);
		} else if(btnKvadratRoer.isSelected()){
			pteController.setProfilType(ProfilType.KVADRET_ROER);
		} else{
			pteController.setProfilType(ProfilType.UDEFINERET);;
		}
		
		tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getAreal()));
	}
	@FXML
	public void haandterResetKnap() { 
		pteController.nulstil();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
			}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formaterTekstfelt(tekstDiameter);
		formaterTekstfelt(tekstFeltHoejde);
		formaterTekstfelt(tekstFeltBredde);
		formaterTekstfelt(tekstFeltAreal);
		formaterTekstfelt(tekstFeltGodstykkelse);
		
	}

}
