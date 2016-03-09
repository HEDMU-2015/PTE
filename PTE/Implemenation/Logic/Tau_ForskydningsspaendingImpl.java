package Logic;


import Exceptions.UdefineretProfilException;

public class Tau_ForskydningsspaendingImpl extends PTEEntityImpl implements Tau_Forskydningsspaending {
	private double tau_Forskydningsspaending = Double.NaN;
	private Forskydningskraft forsykdningskraft;
	private Areal areal;

	public Tau_ForskydningsspaendingImpl (Areal areal, Forskydningskraft forskydningskraft){		
		this.areal = areal;
		this.forsykdningskraft = forskydningskraft;
		this.areal.tilfoejAfhaengigEntitet(this);
		this.forsykdningskraft.tilfoejAfhaengigEntitet(this);

	}


	@Override
	public void setTau_Forskydningsspaending(double tau_Forskydningsspaending) {
		this.tau_Forskydningsspaending = tau_Forskydningsspaending;		
	}

	@Override
	public double getTau_Forskydningsspaending() throws UdefineretProfilException {
		tau_Forskydningsspaending = areal.getAreal() * forsykdningskraft.getForskydningskraft();
		return tau_Forskydningsspaending;
	}

	@Override
	public void nulstil() {
		setTau_Forskydningsspaending(Double.NaN);
	}


	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.TAU_FORSKYDNINGSSPAEDING;
	}
}
