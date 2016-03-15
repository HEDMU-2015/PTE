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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaneUC11Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	private Profil profil;

	@FXML
	private TextField tekstFeltSigmaB, tekstFeltSigmaN, tekstFeltkg, tekstFeltdk, tekstFeltSigmaRef, tekstFeltnk, tekstFeltfs, tekstFeltfk, tekstFeltmb;

	@Override
	public void update(List<Tilstand> tilstande) {
		
		if(tilstande.contains(Tilstand.PROFIL)){
			profil = pteController.getProfil();
			if(profil == Profil.VANDRET){
				tekstFeltfk.setText("cos( " + pteController.getVinkel() + ") * " + pteController.getDimensionerendeKraft());
			}
			else{
				tekstFeltfk.setText("sin( " + pteController.getVinkel() + ") * " + pteController.getDimensionerendeKraft());
			}
		if (tilstande.contains(Tilstand.VAEGT)){
			tekstFeltfk.setText(pteController.getDimensionerendeKraft() + ") / " + pteController.getTyngdekraft()); 
		}
		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)){
			tekstFeltfk.setText(pteController.getVaegt() + ") * " + pteController.getTyngdekraft()); 
		}
		if (tilstande.contains(Tilstand.BOEJNINGSMOMENT)){
			tekstFeltfk.setText(pteController.getDimensionerendeKraft() + ") * " + pteController.getInertimoment()); 
		}
		if (tilstande.contains(Tilstand.TAU_FORSKYDNINGSSPAENDING)){
			tekstFeltfk.setText(pteController.getForskydningkraft() + ") / " + pteController.getAreal()); 
		}
		if (tilstande.contains(Tilstand.SIGMAN)){
			tekstFeltfk.setText(pteController.getNormalkraft() + ") / " + pteController.getAreal()); 
		}
		if (tilstande.contains(Tilstand.SIGMAB)){
			tekstFeltfk.setText(pteController.getForskydningkraft() + ") * " + pteController.getForskydningspunkt() + " / " + ); 
		}
		if (tilstande.contains(Tilstand.TAU_FORSKYDNINGSSPAENDING)){
			tekstFeltfk.setText(pteController.getForskydningkraft() + ") / " + pteController.getAreal()); 
		}
		
	}

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
