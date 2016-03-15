package Logic;

public class ForskydningsspaendingImpl extends PTEEntityImpl implements Forskydningsspaending {
	
	private double tau_ForskydningsSpaending = Double.NaN;
	private Forskydningskraft forskydningskraft;
	private Areal areal;

	public ForskydningsspaendingImpl(Areal areal, Forskydningskraft forskydningskraft) {
		if (areal == null || forskydningskraft == null) {
			throw new IllegalArgumentException();
		}
		
		this.areal = areal;
		this.forskydningskraft = forskydningskraft;
		this.areal.tilfoejAfhaengigEntitet(this);
		this.forskydningskraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setTau_ForskydningsSpaending(double tau_ForskydningsSpaending) {
		this.tau_ForskydningsSpaending = tau_ForskydningsSpaending;
		nulstilBoern();
	}

	private void nulstilBoern() {
		forskydningskraft.nulstil();
		areal.nulstil();
	}

	@Override
	public double getTau_ForskydningsSpaending() {
		if (!Double.isNaN(tau_ForskydningsSpaending)) {
			return tau_ForskydningsSpaending;
		}
		return getTau_ForskydningsSpaending(forskydningskraft.getForskydningskraft(), areal.getAreal());
	}

	double getTau_ForskydningsSpaending(double forskydningskraft, double areal) {
		try {
			return forskydningskraft / areal;
		} catch (Exception e) {
			return Double.NaN;
		}
	}

	@Override
	public void nulstil() {
		setTau_ForskydningsSpaending(Double.NaN);
		nulstilBoern();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.TAU_FORSKYDNINGSSPAENDING;
	}
	
}
