package Logic;

class DimensionerendeKraftImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = 0;
	double vaegtFraDimensionerendeKraft = 0;
	private Vaegt vaegt;
	private Tyngdekraft tyngdekraft;

	public DimensionerendeKraftImpl(Vaegt vaegt, Tyngdekraft tyngdekraft) {
		this.vaegt = vaegt;
		this.tyngdekraft = tyngdekraft;
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
	public double getDimensionerendeKraftTilVaegt() {
		vaegtFraDimensionerendeKraft = dimensionerendeKraft / tyngdekraft.getTyngdekraft();
		return vaegtFraDimensionerendeKraft;
	}

	@Override
	public void setDimensionerendeKraftTilVargt(double vaegtFraDimensionerendeKraft) {
		this.vaegtFraDimensionerendeKraft = vaegtFraDimensionerendeKraft;
	}

	@Override
	public void nulstil() {
		setDimensionerendeKraft(0);
		
	}

}
