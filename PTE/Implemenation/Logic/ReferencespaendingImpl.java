package Logic;

public class ReferencespaendingImpl extends PTEEntityImpl implements Referencespaending {

	private double sigmaRef = Double.NaN;
	private Boejningsspaending sigmaB;
	private Normalspaending sigmaN;
	private Forskydningsspaending tau_ForskydningsSpaendingen;

	public ReferencespaendingImpl(Boejningsspaending sigmaB, Normalspaending sigmaN, Forskydningsspaending tau_ForskydningsSpaendingen) {
		this.sigmaB = sigmaB;
		this.sigmaB.tilfoejAfhaengigEntitet(this);
		this.sigmaN = sigmaN;
		this.sigmaN.tilfoejAfhaengigEntitet(this);
		this.tau_ForskydningsSpaendingen = tau_ForskydningsSpaendingen;
		this.tau_ForskydningsSpaendingen.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public double getSigmaRef() {
		sigmaRef = Math.sqrt(Math.pow(sigmaB.getSigmaB() + sigmaN.getSigmaN(), 2)
				+ 3 * tau_ForskydningsSpaendingen.getTau_ForskydningsSpaending());

		return sigmaRef;
	}

	@Override
	public void nulstil() {
		setSigmaRef(Double.NaN);
		nulstilBoern();
	}
	
	public void nulstilBoern() {
		sigmaB.nulstil();
		sigmaN.nulstil();
		tau_ForskydningsSpaendingen.nulstil();
	}

	@Override
	public void setSigmaRef(double sigmaRef) {
		this.sigmaRef = sigmaRef;
		nulstilBoern();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIGMA_REF;
	}

}
