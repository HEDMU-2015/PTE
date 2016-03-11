package Logic;

public class SigmaRefImpl extends PTEEntityImpl implements SigmaRef {

	private double sigmaRef = Double.NaN;
	private SigmaB sigmaB;
	private SigmaN sigmaN;
	private Tau_ForskydningsSpaending tau_ForskydningsSpaendingen;

	public SigmaRefImpl(SigmaB sigmaB, SigmaN sigmaN, Tau_ForskydningsSpaending tau_ForskydningsSpaendingen) {
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
