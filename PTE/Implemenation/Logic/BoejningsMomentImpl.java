package Logic;

import Exceptions.UdefineretLaengdeRetningException;
import Exceptions.UdefineretProfilException;

class BoejningsMomentImpl extends PTEEntityImpl implements BoejningsMoment {

	private double boejningsMoment = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft = null;
	private Forskydningskraft forskydningskraft = null;
	private Vinkel v;
	private Laengde l;

	public BoejningsMomentImpl(Vinkel v, Laengde l, DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft) {
		if (v == null || l == null || dimensionerendeKraft == null || forskydningskraft == null) {
			throw new IllegalArgumentException();
		}
		this.v = v;
		this.v.tilfoejAfhaengigEntitet(this);
		this.l = l;
		this.l.tilfoejAfhaengigEntitet(this);
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
		this.forskydningskraft = forskydningskraft;
		this.forskydningskraft.tilfoejAfhaengigEntitet(this);
	}
	
	@Override
	public void setBoejningsMoment(double boejningsMoment) {
		this.boejningsMoment = boejningsMoment;
		nulstilBoern();
	}

	@Override
	public double getBoejningsMoment() {
		return getBoejningsMoment(v.getLaengdeRetning(), l.getLaengde(), dimensionerendeKraft.getDimensionerendeKraft(),
				forskydningskraft.getForskydningskraft());

	}

	double getBoejningsMoment(LaengdeRetning lr, double l, double dimensionerendeKraft, double forskydningskraft) {
		
		if (lr == LaengdeRetning.VINKELRET_TIL_FDIM) {
			return (l * dimensionerendeKraft);
		} else if (lr == LaengdeRetning.VINKELRET_TIL_FT) {
			return (l * forskydningskraft);
		} else
			throw new UdefineretLaengdeRetningException("Udefineret laengde retning");
	}

	@Override
	public void nulstil() {
		setBoejningsMoment(Double.NaN);
		nulstilBoern();
	}
	
	private void nulstilBoern() {
		v.nulstil();
		l.nulstil();
		dimensionerendeKraft.nulstil();
		forskydningskraft.nulstil();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.BOEJNINGSMOMENT;
	}

}
