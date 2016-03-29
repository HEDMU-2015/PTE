package gui;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Logic.LaengdeRetning;
import Logic.PTEController;
import Logic.Profil;

public class UdregningTilPdf {

	private DecimalFormat format = new DecimalFormat("#.###");
	
	public final static String FDIM = "Fdim";
	public static final String FN = "Fn";
	public final static String FT = "Ft";
	public static final String TAU = "Tau";
	public final static String BOEJNINGSMOMENT = "MB";
	public final static String INTERTIMOMENT = "i";
	public final static String FORSKYDNINGSPUNKT = "e";
	public static final String AREAL = "A";
	public static final String SIGMA_N = "SigmaN";
	public static final String SIGMA_REF = "SigmaRef";
	public static final String SIGMA_B = "SigmaB";
	public static final String SF = "SF";
	public final static String VAEGT = "vægt";
	public final static String TYNGDEKRAFT = "Tyngdekraft";
	public final static String FLYDESPAENDING = "SigmaTill";
	public final static String LAENGDE = "l";
	public static final String INGEN_RESULTAT = "?";
	
	private String resultat;
	
	private PTEController pteController;
	private List<String> hoejreListe;
	private List<String> underListe;
	
	public UdregningTilPdf(PTEController pteController) {
		this.pteController = pteController;
		
		hoejreListe = new ArrayList<String>();
		hoejreListe.add(normalkraftTilPdf());
		hoejreListe.add(forskydningskraftTilPdf());
		hoejreListe.add(dimensionerendeKraftTilPdf());
		hoejreListe.add(boejningsmomentTilPdf());
		
		underListe = new ArrayList<String>();
		underListe.add(foskydningsspaendingTilPdf());
		underListe.add(normalspaendingTilPdf());
		underListe.add(boejningsspaendingTilPdf());
		underListe.add(referencespaendingTilPdf());
		underListe.add(sikkerhedsfaktorTilPdf());
	}
	
	public List<String> getHoejreListe() {
		return hoejreListe;
	}
	
	public List<String> getUnderListe() {
		return underListe;
	}
	
	private String createStringFromDouble(double value) {
		return format.format(value);
	}

	private String boejningsmomentTilPdf() {
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

	private String boejningsspaendingTilPdf() {
		String boejningsMoment = createStringFromDouble(pteController.getBoejningsMoment());
		if (Double.isNaN(pteController.getBoejningsMoment())) {
			boejningsMoment = BOEJNINGSMOMENT;
		}

		String forskydningspunkt = createStringFromDouble(pteController.getForskydningspunkt());
		if (Double.isNaN(pteController.getForskydningspunkt())) {
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

	private String dimensionerendeKraftTilPdf() {
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

	private String forskydningskraftTilPdf() {
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

	private String normalkraftTilPdf() {
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
	
	private String foskydningsspaendingTilPdf() {
		String forskydningskraft;
		String areal;
		if(Double.isNaN(pteController.getForskydningkraft())) {
			forskydningskraft = FN;
		} else {
			forskydningskraft = createStringFromDouble(pteController.getForskydningkraft());
		}
		if(Double.isNaN(pteController.getIndtastAreal())) {
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
				.append(" / ").append(areal).append(" = ").append(resultat).append(" N/mm2").toString();
		
	}
	
	private String normalspaendingTilPdf() {
		String normalkraft;
		String areal;
		if(Double.isNaN(pteController.getNormalkraft())) {
			normalkraft = FN;
		} else {
			normalkraft = createStringFromDouble(pteController.getNormalkraft());
		}
		if(Double.isNaN(pteController.getIndtastAreal())) {
			areal = AREAL;
		} else {
			areal = createStringFromDouble(pteController.getIndtastAreal());
		}
		if(Double.isNaN(pteController.getSigmaN())) {
			resultat = INGEN_RESULTAT;
		} else {
			resultat = createStringFromDouble(pteController.getSigmaN());
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(SIGMA_N).append(" = ").append(normalkraft)
				.append(" / ").append(areal).append(" = ").append(resultat).append(" N/mm2").toString();
		
	}
	
	private String referencespaendingTilPdf() {
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
		return SIGMA_REF + " = " + "sqrt(pow(" + sigmaB + " + " + sigmaN + ") + 3 * " + "pow(" + tau + ")) = " + resultat + " N/mm2"; 
		
	}
	
	private String sikkerhedsfaktorTilPdf() {
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
