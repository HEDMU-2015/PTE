package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Logic.Profil;
import Logic.Tilstand;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
				tekstFeltfk.setText("cos(" 
					+ (Double.isNaN(pteController.getVinkel()) ? "v" : tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel())) 
					+ ") * " + (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())) 
					+ (Double.isNaN(pteController.getForskydningkraft()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			}
			else{
				tekstFeltfk.setText("sin(" 
					+ (Double.isNaN(pteController.getVinkel()) ? "v" : tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel())) 
					+ ") * " + (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ (Double.isNaN(pteController.getForskydningkraft()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			}
			
			if(profil == Profil.LODRET){
				tekstFeltnk.setText("cos(" 
					+ (Double.isNaN(pteController.getVinkel()) ? "v" : tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel())) 
					+ ") * " + (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ (Double.isNaN(pteController.getNormalkraft()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			}
			else{
				tekstFeltnk.setText("sin("
				+ (Double.isNaN(pteController.getVinkel()) ? "v" : tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel())) 
					+ ") * " + (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ (Double.isNaN(pteController.getNormalkraft()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			}
		}	
				
		if (tilstande.contains(Tilstand.VAEGT)){
			tekstFeltkg.setText(
					(Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ " / " + tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ (Double.isNaN(pteController.getVaegt()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt())); 
		}
		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)){
			tekstFeltdk.setText(
					(Double.isNaN(pteController.getVaegt()) ? "vægt" :tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt())) 
					+ " * " + tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())); 
		}
		if (tilstande.contains(Tilstand.BOEJNINGSMOMENT)){
			tekstFeltmb.setText(
					(Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim" : tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ " * " 
					+ (Double.isNaN(pteController.getLaengde()) ? "længde" : tekstfeltFormat.formaterDoubleTilString(pteController.getLaengde()))
					+ (Double.isNaN(pteController.getBoejningsMoment()) ? "" : " = ") +tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment())); 
		}
		if (tilstande.contains(Tilstand.TAU_FORSKYDNINGSSPAENDING)){
			tekstFeltfs.setText(
					(Double.isNaN(pteController.getForskydningkraft()) ? "Ft" : tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()))
					+ " / " 
					+ (Double.isNaN(pteController.getIndtastAreal()) ? "areal" : tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()))
					+ (Double.isNaN(pteController.getTau_ForskydningsSpaending()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending())); 
		}
		if (tilstande.contains(Tilstand.SIGMAN)){
			tekstFeltSigmaN.setText(
					(Double.isNaN(pteController.getNormalkraft()) ? "Fn" : tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft())) 
					+ " / " + 
					(Double.isNaN(pteController.getIndtastAreal()) ? "areal" : tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()))
					+ (Double.isNaN(pteController.getSigmaN()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN())); 
		}
		if (tilstande.contains(Tilstand.SIGMAB)){
			tekstFeltSigmaB.setText("( " 
					+ (Double.isNaN(pteController.getBoejningsMoment()) ? "MB" : tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment())) 
					+ " * " 
					+ (Double.isNaN(pteController.getForskydningspunkt()) ? "e" : tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningspunkt())) +" ) " 
					+ " / " 
					+ (Double.isNaN(pteController.getInertimoment()) ? "I" : tekstfeltFormat.formaterDoubleTilString(pteController.getInertimoment()))
					+ (Double.isNaN(pteController.getSigmaB()) ? "" : " = ") +tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB())); 
		}
		if (tilstande.contains(Tilstand.SIGMA_REF)){
			tekstFeltSigmaRef.setText(" √ ((" 
				+ (Double.isNaN(pteController.getSigmaB()) ? "σB" : tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB())) 
				+ " + " 
				+ (Double.isNaN(pteController.getSigmaN()) ? "σN" : tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN())) + ")² + 3 *"
				+ (Double.isNaN(pteController.getTau_ForskydningsSpaending()) ? "τ" : tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending())) + "²)"
				+ (Double.isNaN(pteController.getSigmaRef()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef())); 
		}
		
		if (tilstande.contains(Tilstand.SIKKERHEDSFAKTOR)){
			tekstFeltSikkerhedsfaktor.setText(
					(Double.isNaN(pteController.getFlydespaending()) ? "σtill" : tekstfeltFormat.formaterDoubleTilString(pteController.getFlydespaending())) 
					+ " / " 
					+ (Double.isNaN(pteController.getSigmaRef()) ? "σref" : tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()))
					+ (Double.isNaN(pteController.getSikkerhedsfaktor()) ? "" : " = ") + tekstfeltFormat.formaterDoubleTilString(pteController.getSikkerhedsfaktor()));  
		
			if(tekstFeltSikkerhedsfaktor.getText()!= "" && pteController.erSikkerhedsfaktorForLavt()){
				System.out.println("pteController " + pteController.erSikkerhedsfaktorForLavt());
				tekstFeltSikkerhedsfaktor.setStyle("-fx-background-color: pink;");
			}
		
		
		}
		
	}	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
