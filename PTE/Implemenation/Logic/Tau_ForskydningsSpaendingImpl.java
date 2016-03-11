package Logic;


import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import Exceptions.UdefineretProfilException;
/**
 * 
 * @author Juyoung Choi
 *
 */

public class Tau_ForskydningsSpaendingImpl extends PTEEntityImpl implements Tau_ForskydningsSpaending {
	private double tau_ForskydningsSpaending = Double.NaN;
	private Forskydningskraft forskydningskraft;
	private Areal areal;
	
	

	public Tau_ForskydningsSpaendingImpl (Areal areal, Forskydningskraft forskydningskraft){		
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
