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
	private TextField tekstFeltSigmaB, tekstFeltSigmaN, tekstFeltkg, tekstFeltdk, tekstFeltSigmaRef, tekstFeltnk, tekstFeltfs, tekstFeltfk, tekstFeltmb, tekstFeltAreal, tekstFeltSikkerhedsfaktor;

	@Override
	public void update(List<Tilstand> tilstande) {
		if(tilstande.contains(Tilstand.VINKEL)){
			profil = pteController.getProfil();

			if(profil == Profil.VANDRET){
				tekstFeltfk.setText("cos( " + tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()) 
					+ ") * " + tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()) 
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			}
			else{
				tekstFeltfk.setText("sin( " + tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()) 
					+ ") * " + tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			}
			
			if(profil == Profil.LODRET){
				tekstFeltnk.setText("cos( " + tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()) 
					+ ") * " + tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			}
			else{
				tekstFeltnk.setText("sin( " + tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()) 
					+ ") * " + tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			}
		}	
			
			
			
			
		if (tilstande.contains(Tilstand.VAEGT)){
			tekstFeltkg.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()) 
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt())); 
		}
		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)){
			tekstFeltdk.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()) 
					+ " * " + tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())); 
		}
		if (tilstande.contains(Tilstand.BOEJNINGSMOMENT)){
			tekstFeltmb.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()) 
					+ " * " + tekstfeltFormat.formaterDoubleTilString(pteController.getLaengde())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment())); 
		}
		if (tilstande.contains(Tilstand.TAU_FORSKYDNINGSSPAENDING)){
			tekstFeltfs.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()) 
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getAreal())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending())); 
		}
		if (tilstande.contains(Tilstand.SIGMAN)){
			tekstFeltSigmaN.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()) 
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getAreal())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN())); 
		}
		if (tilstande.contains(Tilstand.SIGMAB)){
			tekstFeltSigmaB.setText("( " + tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()) 
					+ " * " + tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningspunkt()) +" ) " 
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getInertimoment())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB())); 
		}
		if (tilstande.contains(Tilstand.SIGMA_REF)){
			tekstFeltSigmaRef.setText(" √ ((" + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()) 
				+ " + " + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN()) + ")² + 3 *"
				+ tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()) + "²)"
				+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef())); 
		}
		if (tilstande.contains(Tilstand.SIKKERHEDSFAKTOR)){
			tekstFeltSikkerhedsfaktor.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getFlydespaending()) 
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef())
					+ " = " +tekstfeltFormat.formaterDoubleTilString(pteController.getSikkerhedsfaktor()));  
		}
		/*if (tilstande.contains(Tilstand.AREAL)){
			tekstFeltAreal.setText(tekstfeltFormat.formaterDoubleTilString(pteController.getFlydespaending()) + " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()));  
		}*/
		}	
	

	private void formaterTekstfelt(TextField input) {
		tekstfeltFormat.formaterTekstfeltInput(input);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
