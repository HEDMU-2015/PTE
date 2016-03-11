package Logic;

import Exceptions.NormalkraftException;
import Exceptions.UdefineretProfilException;

class NormalkraftImpl extends PTEEntityImpl implements Normalkraft {

	private double normalkraft = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft;
	private Vinkel vinkel;
	
	public NormalkraftImpl(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel){
		if (dimensionerendeKraft == null || vinkel == null) {
			throw new IllegalArgumentException();
		}
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.vinkel = vinkel;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
		this.vinkel.tilfoejAfhaengigEntitet(this);
	}
	
	@Override
	public double getNormalkraft() {
		return getNormalkraft(vinkel.getVinkel(), dimensionerendeKraft.getDimensionerendeKraft());

	}
	double getNormalkraft(double vinkel, double dimensionerendeKraft) {
		if(vinkel == Double.NaN) {
			return normalkraft;
		}
		
		if(dimensionerendeKraft<=0 
				|| dimensionerendeKraft== Double.NaN 
				|| vinkel<0 
				|| vinkel>90 
				|| vinkel == Double.NaN){
			throw new NormalkraftException();
		}
		if (this.vinkel.getProfil() == Profil.VANDRET){
			return (Math.sin(Math.toRadians(vinkel))*this.dimensionerendeKraft.getDimensionerendeKraft());
		}
		else if (this.vinkel.getProfil() == Profil.LODRET){
			return (Math.cos((Math.toRadians(vinkel)))*this.dimensionerendeKraft.getDimensionerendeKraft());
		}
		else {
			throw new UdefineretProfilException("Udefineret Profil");
		}
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalkraft = normalkraft;
		nulstilBoern();
	}
	
	@Override
	public void nulstil() {
		setNormalkraft(Double.NaN);
		nulstilBoern();
	}
	
	private void nulstilBoern() {
		dimensionerendeKraft.nulstil();
		vinkel.nulstil();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.NORMALKRAFT;
	}

}
