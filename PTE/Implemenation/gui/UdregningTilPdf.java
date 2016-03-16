package gui;
import java.util.ArrayList;
import java.util.List;

import Logic.LaengdeRetning;
import Logic.PTEController;
import Logic.Profil;

public class UdregningTilPdf {

	public final static String FDIM = "Fdim";
	public static final String FN = "Fn";
	public final static String FT = "Ft";
	public static final String TAU = "Τ";
	public final static String BOEJNINGSMOMENT = "MB";
	public final static String INTERTIMOMENT = "i";
	public final static String FORSKYDNINGSPUNKT = "e";
	public static final String AREAL = "A";
	public static final String SIGMA_N = "σn";
	public static final String SIGMA_REF = "σref";
	public static final String SIGMA_B = "σb";
	public static final String SF = "SF";
	public final static String BOEJNINGSSPAENDING = "SigmaB";
	public final static String VAEGT = "vægt";
	public final static String TYNGDEKRAFT = "Tyngdekraft";
	public final static String FLYDESPAENDING = "σtill";
	public final static String LAENGDE = "l";
	public static final String INGEN_RESULTAT = "?";
	
	private String resultat;
	
	private PTEController pteController;
	private List<String> hoejreListe;
	private List<String> underListe;
	
	public UdregningTilPdf(PTEController pteController) {
		this.pteController = pteController;
		hoejreListe = new ArrayList<String>();
		underListe = new ArrayList<String>();
	}
	
	public List<String> getHoejreListe() {
		hoejreListe.add(normalkraftTilPdf());
		hoejreListe.add(forskydningskraftTilPdf());
		hoejreListe.add(dimensionerendeKraftTilPdf());
		hoejreListe.add(boejningsmomentTilPdf());
		return hoejreListe;
	}
	
	public List<String> getUnderListe() {
		underListe.add(foskydningsspaendingTilPdf());
		underListe.add(normalspaendingTilPdf());
		underListe.add(boejningsspaendingTilPdf());
		underListe.add(referencespaendingTilPdf());
		underListe.add(sikkerhedsfaktorTilPdf());
		return underListe;
	}
	
	private String createStringFromDouble(double value) {
		return String.valueOf(value);
	}

	public String boejningsmomentTilPdf() {
		String laengde = "";
		String kraft = "";

		if(Double.isNaN(pteController.getLaengde())) {
			laengde = LAENGDE;
		} else {
			laengde = createStringFromDouble(pteController.getLaengde());
		}
		if (pteController.getLaengdeRetning() == LaengdeRetning.VINKELRET_TIL_FDIM) {
			if (Double.isNaN(pteController.getDimensionerendeKraft())) {
				kraft = FDIM;
			} else {
				kraft = createStringFromDouble(pteController.getDimensionerendeKraft());
			}
		} else {
			if (Double.isNaN(pteController.getForskydningkraft())) {
				kraft = FT;
			} else {
				kraft = createStringFromDouble(pteController.getForskydningkraft());
			}
		}

		if (Double.isNaN(pteController.getBoejningsMoment())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getBoejningsMoment());
		}

		return BOEJNINGSMOMENT + " = " + laengde + " * " + kraft + " = " + resultat;
	}

	public String boejningsspaendingTilPdf() {
		String boejningsMoment = createStringFromDouble(pteController.getBoejningsMoment());
		if (Double.isNaN(pteController.getBoejningsMoment())) {
			boejningsMoment = BOEJNINGSMOMENT;
		}

		String forskydningspunkt = createStringFromDouble(pteController.getForskydningspunkt());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			forskydningspunkt = FORSKYDNINGSPUNKT;
		}

		String inertimoment = createStringFromDouble(pteController.getInertimoment());
		if (Double.isNaN(pteController.getInertimoment())) {
			inertimoment = INTERTIMOMENT;
		}

		if (Double.isNaN(pteController.getSigmaB())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getSigmaB());
		}

		return SIGMA_B + " = " + boejningsMoment + " * " + forskydningspunkt + " / " + inertimoment + " = "
				+ resultat;
	}

	public String dimensionerendeKraftTilPdf() {
		String vaegt = createStringFromDouble(pteController.getVaegt());
		if (Double.isNaN(pteController.getVaegt())) {
			vaegt = VAEGT;
		}

		String tyngdekraft = createStringFromDouble(pteController.getTyngdekraft());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			tyngdekraft = TYNGDEKRAFT;
		}

		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getDimensionerendeKraft());
		}

		return FDIM + " = " + vaegt + " * " + tyngdekraft + " = " + resultat + " [N]";
	}

	public String forskydningskraftTilPdf() {
		String vinkel = createStringFromDouble(pteController.getVinkel());
		if (pteController.getProfil() == Profil.VANDRET) {
			vinkel = "cos(" + vinkel + ")";
		}
		if (pteController.getProfil() == Profil.LODRET) {
			vinkel = "sin(" + vinkel + ")";
		}

		String fdim = createStringFromDouble(pteController.getDimensionerendeKraft());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			fdim = FDIM;
		}

		if (Double.isNaN(pteController.getForskydningkraft())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getForskydningkraft());
		}

		return FT + " = " + vinkel + " * " + fdim + " = " + resultat + " [N]";
	}

	public String normalkraftTilPdf() {
		String vinkel = createStringFromDouble(pteController.getVinkel());
		if (pteController.getProfil() == Profil.VANDRET) {
			vinkel = "sin(" + vinkel + ")";
		}
		if (pteController.getProfil() == Profil.LODRET) {
			vinkel = "cos(" + vinkel + ")";
		}

		String fdim = createStringFromDouble(pteController.getDimensionerendeKraft());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			fdim = FDIM;
		}

		if (Double.isNaN(pteController.getNormalkraft())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getNormalkraft());
		}

		return FN + " = " + vinkel + " * " + fdim + " = " + resultat + " [N]" ;
	}
	
	public String foskydningsspaendingTilPdf() {
		String forskydningskraft;
		String areal;
		if(Double.isNaN(pteController.getForskydningkraft())) {
			forskydningskraft = FN;
		} else {
			forskydningskraft = createStringFromDouble(pteController.getForskydningkraft());
		}
		if(Double.isNaN(pteController.getAreal())) {
			areal = AREAL;
		} else {
			areal = createStringFromDouble(pteController.getIndtastAreal());
		}
		if(Double.isNaN(pteController.getForskydningkraft())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getForskydningkraft());
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(TAU).append(" = ").append(forskydningskraft)
				.append(" / ").append(areal).append(" = ").append(resultat).append("N/mm2").toString();
		
	}
	
	public String normalspaendingTilPdf() {
		String normalkraft;
		String areal;
		if(Double.isNaN(pteController.getNormalkraft())) {
			normalkraft = FN;
		} else {
			normalkraft = createStringFromDouble(pteController.getNormalkraft());
		}
		if(Double.isNaN(pteController.getAreal())) {
			areal = AREAL;
		} else {
			areal = createStringFromDouble(pteController.getAreal());
		}
		if(Double.isNaN(pteController.getSigmaN())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getSigmaN());
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(SIGMA_N).append(" = ").append(normalkraft)
				.append(" / ").append(areal).append(" = ").append(resultat).append("N/mm2").toString();
		
	}
	
	public String referencespaendingTilPdf() {
		String sigmaB;
		String sigmaN;
		String tau;
		if(Double.isNaN(pteController.getSigmaN())) {
			sigmaN = SIGMA_N;
		} else {
			sigmaN = createStringFromDouble(pteController.getSigmaN());
		}
		if(Double.isNaN(pteController.getSigmaB())) {
			sigmaB = SIGMA_B;
		} else {
			sigmaB = createStringFromDouble(pteController.getSigmaB());
		}
		if(Double.isNaN(pteController.getTau_ForskydningsSpaending())) {
			tau = TAU;
		} else {
			tau = createStringFromDouble(pteController.getTau_ForskydningsSpaending());
		}
		if(Double.isNaN(pteController.getSigmaRef())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getSigmaRef());
		}
		return SIGMA_REF + " = " + "sqrt(pow(" + sigmaB + " + " + sigmaN + ") + 3 * " + "pow(" + tau + "))" + resultat + "N/mm2"; 
		
	}
	
	public String sikkerhedsfaktorTilPdf() {
		String flydespaending;
		String sigmaRef;
		if(Double.isNaN(pteController.getFlydespaending())) {
			flydespaending = FLYDESPAENDING;
		} else {
			flydespaending = createStringFromDouble(pteController.getFlydespaending());
		}
		if(Double.isNaN(pteController.getSigmaRef())) {
			sigmaRef = AREAL;
		} else {
			sigmaRef = createStringFromDouble(pteController.getSigmaRef());
		}
		if(Double.isNaN(pteController.getSikkerhedsfaktor())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getSikkerhedsfaktor());
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(SF).append(" = ").append(flydespaending)
				.append(" / ").append(sigmaRef).append(" = ").append(resultat).toString();
		
	}
	
}
