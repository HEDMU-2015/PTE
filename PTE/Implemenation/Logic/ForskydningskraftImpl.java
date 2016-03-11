package Logic;

import Exceptions.ForskydningskraftException;
import Exceptions.UdefineretProfilException;

class ForskydningskraftImpl extends PTEEntityImpl implements Forskydningskraft {

	private double forskydningskraft = Double.NaN;
	private Vinkel v;
	private DimensionerendeKraft dk;

	public ForskydningskraftImpl(Vinkel v, DimensionerendeKraft dk) {
		this.v = v;
		this.dk = dk;
		this.v.tilfoejAfhaengigEntitet(this);
		this.dk.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningskraft = forskydningskraft;

	}

	@Override
	public double getForskydningskraft() {
	

		if(v.getVinkel() == Double.NaN
				|| dk.getDimensionerendeKraft()<=0
				|| dk.getDimensionerendeKraft()== Double.NaN 
				|| v.getVinkel()<0 
				|| v.getVinkel()>90 
				){
			throw new ForskydningskraftException();
		}
		
		if (v.getProfil() == Profil.VANDRET) {
			return (Math.cos(Math.toRadians(v.getVinkel())) * dk.getDimensionerendeKraft());
		} else if (v.getProfil() == Profil.LODRET) {
			return (Math.sin(Math.toRadians(v.getVinkel())) * dk.getDimensionerendeKraft());
		} else
				throw new UdefineretProfilException("Udefineret Profil");
	}

	double getForskydningskraft(double vinkel, double dimensionerendeKraft) {
		forskydningskraft = Math.cos(vinkel) * dimensionerendeKraft;

		return forskydningskraft;
	}

	@Override
	public void nulstil() {
		setForskydningskraft(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.FORSKYDNINGSKRAFT;
	}

}
