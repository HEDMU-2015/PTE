package Logic;

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
	public double getForskydningskraft() throws UdefineretProfilException {
		double vinkel = v.getVinkel();
		Profil profil = v.getProfil();

		if (profil == Profil.VANDRET) {
			return (Math.sin(Math.toRadians(vinkel)) * dk.getDimensionerendeKraft());
		} else if (profil == Profil.LODRET) {
			return (Math.cos(Math.toRadians(vinkel)) * dk.getDimensionerendeKraft());
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
