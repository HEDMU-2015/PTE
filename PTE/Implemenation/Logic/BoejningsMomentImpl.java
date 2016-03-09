package Logic;

import Exceptions.UdefineretProfilException;

class BoejningsMomentImpl extends PTEEntityImpl implements BoejningsMoment {

	private double boejningsMoment = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft;
	private Forskydningskraft forskydningskraft;
	
	public BoejningsMomentImpl(DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft){
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.forskydningskraft = forskydningskraft;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
		this.forskydningskraft.tilfoejAfhaengigEntitet(this);
	}
	
	@Override
	public double getBoejningsMoment() throws UdefineretProfilException {
		boejningsMoment = getBoejningsMoment()
	}
	
	double getBoejningsMoment(double forskydningskraft, double dimensionerendeKraft)
	
	@Override
	public void setBoejningsMoment(double BoejningsMoment) {
		
	}
	@Override
	public void nulstil() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected Tilstand getEgenAfhaengighed() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
