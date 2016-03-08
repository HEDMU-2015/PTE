package Logic;

class DimensionerendeKraftImpl  extends PTEEntityImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = 0;
	double vaegtFraDimensionerendeKraft = 0;
	private Vaegt vaegt;
	private Tyngdekraft tyngdekraft;

	public DimensionerendeKraftImpl(Vaegt vaegt, Tyngdekraft tyngdekraft) {
		this.vaegt = vaegt;
		this.tyngdekraft = tyngdekraft;
		this.vaegt.tilfoejAfhaengigEntitet(this);
		this.tyngdekraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public double getDimensionerendeKraft() {
		dimensionerendeKraft = vaegt.getVaegt() * tyngdekraft.getTyngdekraft();
		return dimensionerendeKraft;
	}

	@Override
	public void setDimensionerendeKraft(double dimensionerendeKraft) {
		this.dimensionerendeKraft = dimensionerendeKraft;
	}

	@Override
	public double dimensionerendeKraftTilVaegt() {
		return (dimensionerendeKraft / tyngdekraft.getTyngdekraft());
	}
	
	@Override
	public void nulstil() {
		setDimensionerendeKraft(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.DIMENSIONERENDE_KRAFT;
	}

}
