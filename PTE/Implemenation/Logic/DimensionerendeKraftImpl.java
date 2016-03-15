package Logic;

import Exceptions.DimensionerendeKraftException;

class DimensionerendeKraftImpl extends PTEEntityImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = Double.NaN;
	private double vaegtFraDimensionerendeKraft = Double.NaN;
	private Vaegt vaegt;
	private Tyngdekraft tyngdekraft;

	public DimensionerendeKraftImpl(Vaegt vaegt, Tyngdekraft tyngdekraft) {
		if (vaegt == null || tyngdekraft == null) {
			throw new IllegalArgumentException();
		}

		this.vaegt = vaegt;
		this.tyngdekraft = tyngdekraft;
		this.vaegt.tilfoejAfhaengigEntitet(this);
		this.tyngdekraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public double getDimensionerendeKraft() {
		if (dimensionerendeKraft == Double.NaN) {
			return dimensionerendeKraft;
		}

		return getDimensionerendeKraft(vaegt.getVaegt(), tyngdekraft.getTyngdekraft());
	}

	double getDimensionerendeKraft(double vaegt, double tyngdekraft) {
		
		if (vaegt == Double.NaN || tyngdekraft == Double.NaN || dimensionerendeKraft == Double.NaN) {
			return dimensionerendeKraft;
		}
		
		if (vaegt <= 0 || tyngdekraft <= 0) {
			throw new DimensionerendeKraftException();
		}

		

		return vaegt * tyngdekraft;
	}

	@Override
	public void setDimensionerendeKraft(double dimensionerendeKraft) {
		if(dimensionerendeKraft <= 0){
			throw new DimensionerendeKraftException();
		}
		
		this.dimensionerendeKraft = dimensionerendeKraft;
		nulstilBoern();
	}

	@Override
	public double dimensionerendeKraftTilVaegt() {
		return (dimensionerendeKraft / tyngdekraft.getTyngdekraft());
	}

	@Override
	public void nulstil() {
		dimensionerendeKraft = Double.NaN;
		nulstilBoern();
	}

	private void nulstilBoern() {
		vaegt.nulstil();
		tyngdekraft.nulstil();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.DIMENSIONERENDE_KRAFT;
	}

}
