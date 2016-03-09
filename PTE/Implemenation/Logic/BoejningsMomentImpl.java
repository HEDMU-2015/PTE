package Logic;

import Exceptions.UdefineretProfilException;

class BoejningsMomentImpl extends PTEEntityImpl implements BoejningsMoment {

	private double boejningsMoment = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft;
	private Forskydningskraft forskydningskraft;
	private Vinkel v;
	private DimensionerendeKraft dk;
	private Forskydningskraft fk;

	public BoejningsMomentImpl(DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft) {
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.forskydningskraft = forskydningskraft;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
		this.forskydningskraft.tilfoejAfhaengigEntitet(this);

	}

	@Override
	public void setBoejningsMoment(double boejningsMoment) {
		this.boejningsMoment = boejningsMoment;
	}

	public double getBoejningsMoment(double laengde) throws UdefineretProfilException {
		LaengdeRetning laengdeRetning = v.getLaengdeRetning();

		if (laengdeRetning == LaengdeRetning.VINKELRET_TIL_FDIM) {
			return (laengde * dk.getDimensionerendeKraft());
		} else if (laengdeRetning == LaengdeRetning.VINKELRET_TIL_FT) {
			return (laengde * fk.getForskydningskraft());
		} else
			throw new UdefineretProfilException("Udefineret Profil");

	}

	public double getBoejningsMoment() {
		boejningsMoment = getBoejningsMoment();
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
