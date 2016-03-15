package Logic;

import Exceptions.UdefineretProfilException;

public class NormalspaendingImpl extends PTEEntityImpl implements Normalspaending{
	
	private Areal areal;
	private Normalkraft normalkraft;
	private double sigmaN = Double.NaN;
	
	

	public NormalspaendingImpl(Areal areal, Normalkraft normalkraft) {
		
		this.areal = areal;
		this.normalkraft = normalkraft;
		this.areal.tilfoejAfhaengigEntitet(this);
		this.normalkraft.tilfoejAfhaengigEntitet(this);
	}

	


	@Override
	public void setSigmaN(double sigmaN) {
		this.sigmaN = sigmaN;		
	}


	@Override
	public double getSigmaN() {
		try {
			sigmaN = normalkraft.getNormalkraft() / areal.getAreal();
		} catch (UdefineretProfilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sigmaN;
	}

	
	
	@Override
	public void nulstil() {
		setSigmaN(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		
		return Tilstand.SIGMAN;
	}




}
