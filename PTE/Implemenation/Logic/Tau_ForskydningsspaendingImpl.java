package Logic;


import Exceptions.UdefineretProfilException;
/**
 * 
 * @author Juyoung Choi
 * 
 *
 */

public class Tau_ForskydningsSpaendingImpl extends PTEEntityImpl implements Tau_ForskydningsSpaending {
	private double tau_ForskydningsSpaending = Double.NaN;
	private Forskydningskraft forsykdningskraft;
	private Areal areal;
	
	

	public Tau_ForskydningsSpaendingImpl (Areal areal, Forskydningskraft forskydningskraft){		
		this.areal = areal;
		this.forsykdningskraft = forskydningskraft;
		this.areal.tilfoejAfhaengigEntitet(this);
		this.forsykdningskraft.tilfoejAfhaengigEntitet(this);

	}


	@Override
	public void setTau_ForskydningsSpaending(double tau_ForskydningsSpaending) {
		this.tau_ForskydningsSpaending = tau_ForskydningsSpaending;		
	}

	@Override
	public double getTau_ForskydningsSpaending() {
		//What is the difference between throws or try-catch? interface doesn't show it... isn't it better?
		//We check if the areal <= 0 before, so no need to have if{} here
		try {
			tau_ForskydningsSpaending = forsykdningskraft.getForskydningskraft() / areal.getAreal();
		} catch (UdefineretProfilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tau_ForskydningsSpaending;
	}

	@Override
	public void nulstil() {
		setTau_ForskydningsSpaending(Double.NaN);
	}


	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.TAU_FORSKYDNINGSSPAENDING;
	}
}
