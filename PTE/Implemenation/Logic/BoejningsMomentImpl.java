package Logic;

import Exceptions.UdefineretProfilException;

class BoejningsMomentImpl extends PTEEntityImpl implements BoejningsMoment {

	private double boejningsMoment = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft = null;
	private Forskydningskraft forskydningskraft = null;
	private Vinkel v;
	private Laengde l;

	public BoejningsMomentImpl(Vinkel v, Laengde l) {
		this.v = v;
		this.l = l;
	}
	
	@Override
	public void setDimensionerendeKraft(DimensionerendeKraft dimensionerendeKraft) {
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
	}
	
	@Override
	public void setForskydningskraft(Forskydningskraft forskydningskraft) {
		this.forskydningskraft = forskydningskraft;
		this.forskydningskraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setBoejningsMoment(double boejningsMoment) {
		this.boejningsMoment = boejningsMoment;
	}

	@Override
	public double getBoejningsMoment() throws UdefineretProfilException {
		double laengde = l.getLaengde();

		if (dimensionerendeKraft != null) {
			return (laengde * dimensionerendeKraft.getDimensionerendeKraft());
		} else if (forskydningskraft != null) {
			return (laengde * forskydningskraft.getForskydningskraft());
		} else
			throw new UdefineretProfilException("Udefineret Profil");

	}

	double getBoejningsMoment(double laengde) {
		boejningsMoment = 0; // tilføj formlen
		return boejningsMoment;
	}

	@Override
	public void nulstil() {
		setBoejningsMoment(Double.NaN);

	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.BOEJNINGSMOMENT;
	}

}
