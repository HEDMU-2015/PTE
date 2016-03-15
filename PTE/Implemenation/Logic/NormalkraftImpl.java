package Logic;

class NormalkraftImpl extends PTEEntityImpl implements Normalkraft {

	private double normalkraft = Double.NaN;
	private DimensionerendeKraft dimensionerendeKraft;
	private Vinkel vinkel;

	public NormalkraftImpl(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel) {
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
		if (!Double.isNaN(normalkraft)) {
			return normalkraft;
		}

		if (dimensionerendeKraft == Double.NaN || vinkel == Double.NaN) {
			return Double.NaN;
		}
		if (this.vinkel.getProfil() == Profil.VANDRET) {
			return (Math.sin(Math.toRadians(vinkel)) * this.dimensionerendeKraft.getDimensionerendeKraft());
		} else { // this.vinkel.getProfil() == Profil.LODRET
			return (Math.cos((Math.toRadians(vinkel))) * this.dimensionerendeKraft.getDimensionerendeKraft());
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
