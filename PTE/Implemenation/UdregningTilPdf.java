import Logic.LaengdeRetning;
import Logic.PTEController;
import Logic.Profil;

public class UdregningTilPdf {

	public final static String FDIM = "Fdim";
	public final static String FT = "Ft";
	public final static String INGEN_RESULTAT = "?";
	public final static String BOEJNINGSMOMENT = "MB";
	public final static String INTERTIMOMENT = "i";
	public final static String FORSKYDNINGSPUNKT = "e";
	public final static String BOEJNINGSSPAENDING = "SigmaB";
	public final static String VAEGT = "vægt";
	public final static String TYNGDEKRAFT = "tyngdekraft";
	public final static String FN = "Fn";
	private PTEController pteController;

	public UdregningTilPdf(PTEController pteController) {
		this.pteController = pteController;
	}

	private String createStringFromDouble(double value) {
		return String.valueOf(value);
	}

	public String boejningsmomentTilPdf() {
		String laengde = createStringFromDouble(pteController.getLaengde());
		String kraft = null;

		if (pteController.getLaengdeRetning() == LaengdeRetning.VINKELRET_TIL_FDIM) {
			kraft = createStringFromDouble(pteController.getDimensionerendeKraft());
			if (Double.isNaN(pteController.getDimensionerendeKraft())) {
				kraft = FDIM;
			}
		} else {
			kraft = createStringFromDouble(pteController.getForskydningkraft());
			if (Double.isNaN(pteController.getForskydningkraft())) {
				kraft = FT;
			}
		}

		String mb = createStringFromDouble(pteController.getBoejningsMoment());
		if (Double.isNaN(pteController.getBoejningsMoment())) {
			mb = INGEN_RESULTAT;
		}

		return "MB = " + laengde + "mm" + " * " + kraft + "N" + " = " + mb + "MB";
	}

	public String dimensionerendeKraftTilPdf() {
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

		String boejningsspaending = createStringFromDouble(pteController.getSigmaB());
		if (Double.isNaN(pteController.getSigmaB())) {
			boejningsspaending = BOEJNINGSSPAENDING;
		}

		return "Sigma B " + boejningsMoment + "MB" + " * " + forskydningspunkt + "e" + " / " + inertimoment + "e "
				+ " = " + boejningsspaending + "Sigma B";
	}

	public String boejningsspaendingTilPdf() {
		String vaegt = createStringFromDouble(pteController.getVaegt());
		if (Double.isNaN(pteController.getVaegt())) {
			vaegt = VAEGT;
		}

		String tyngdekraft = createStringFromDouble(pteController.getTyngdekraft());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			tyngdekraft = TYNGDEKRAFT;
		}

		String fdim = createStringFromDouble(pteController.getDimensionerendeKraft());
		if (Double.isNaN(pteController.getDimensionerendeKraft())) {
			fdim = FDIM;
		}

		return "Fdim = " + vaegt + "kg" + " * " + tyngdekraft + "m/s2" + " = " + fdim + "N";
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

		String ft = createStringFromDouble(pteController.getForskydningkraft());
		if (Double.isNaN(pteController.getForskydningkraft())) {
			ft = FT;
		}

		return "Ft = " + vinkel + "o" + " * " + fdim + "N" + " = " + ft + "N";
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

		String fn = createStringFromDouble(pteController.getNormalkraft());
		if (Double.isNaN(pteController.getNormalkraft())) {
			fn = FN;
		}

		return "Fn = " + vinkel + "o" + " * " + fdim + "N" + " = " + fn + "N";
	}

}
