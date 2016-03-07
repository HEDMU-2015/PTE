package Logic;

import Exceptions.UdefineretProfilException;

class NormalkraftImpl extends PTEEntityImpl implements Normalkraft {

	private double normalkraft = 0;
	private DimensionerendeKraft dimensionerendeKraft;
	private Vinkel vinkel;
	
	public NormalkraftImpl(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel){
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.vinkel = vinkel;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
		this.vinkel.tilfoejAfhaengigEntitet(this);
	}
	
	@Override
	public double getNormalkraft() throws UdefineretProfilException {
		normalkraft = getNormalkraft(vinkel.getVinkel(), dimensionerendeKraft.getDimensionerendeKraft());

		return normalkraft;
	}
	double getNormalkraft(double vinkel, double dimensionerendeKraft) throws UdefineretProfilException {
		if (this.vinkel.getProfil() == Profil.VANDRET){
			return (Math.cos(Math.toRadians(vinkel))*this.dimensionerendeKraft.getDimensionerendeKraft());
		}
		else if (this.vinkel.getProfil() == Profil.LODRET){
			return (Math.sin((Math.toRadians(vinkel)))*this.dimensionerendeKraft.getDimensionerendeKraft());
		}
		else throw new UdefineretProfilException("Udefineret Profil");
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalkraft = normalkraft;
		
	}
	
	@Override
	public void nulstil() {
		setNormalkraft(0);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.NORMALKRAFT;
	}

}
