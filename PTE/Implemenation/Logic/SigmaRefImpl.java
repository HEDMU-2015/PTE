package Logic;

public class SigmaRefImpl extends PTEEntityImpl implements SigmaRef {

	private double sigmaRef;
	private SigmaB sigmaB;
	private SigmaN sigmaN;
	private Tau_ForskydningsSpaending tau_ForskydningsSpaendingen;

	public SigmaRefImpl(SigmaB sigmaB, SigmaN sigmaN, Tau_ForskydningsSpaending tau_ForskydningsSpaendingen) {
		this.sigmaB = sigmaB;
		this.sigmaN = sigmaN;
		this.tau_ForskydningsSpaendingen = tau_ForskydningsSpaendingen;
	}

	@Override
	public double getSigmaRef() {
		double sigmaRefResultat = Math.sqrt(Math.pow(sigmaB.getSigmaB() + sigmaN.getSigmaN(), 2)
				+ 3 * tau_ForskydningsSpaendingen.getTau_ForskydningsSpaending());

		return sigmaRefResultat;
	}

	@Override
	public void nulstil() {
		setSigmaRef(Double.NaN);
	}
	@Override
	public void setSigmaRef(double sigmaRef) {
		this.sigmaRef = sigmaRef;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIGMA_REF;
	}

}
