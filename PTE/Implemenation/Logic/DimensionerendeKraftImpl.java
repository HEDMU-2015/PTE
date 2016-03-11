package Logic;

import Exceptions.DimensionerendeKraftException;

class DimensionerendeKraftImpl  extends PTEEntityImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = Double.NaN;
	double vaegtFraDimensionerendeKraft = Double.NaN;
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
		return getDimensionerendeKraft(vaegt.getVaegt(), tyngdekraft.getTyngdekraft());
	}
	
	double getDimensionerendeKraft(double vaegt, double tyngdekraft) {
		if(vaegt == Double.NaN){
			System.out.println(dimensionerendeKraft); //TODO slet syso
			return dimensionerendeKraft;
		}
			
		if(vaegt <= 0 || tyngdekraft <= 0){
			throw new DimensionerendeKraftException();
		}
		
		dimensionerendeKraft = vaegt * tyngdekraft;
		return dimensionerendeKraft;
	}
	
	
	
	@Override
	public void setDimensionerendeKraft(double dimensionerendeKraft) {
		this.dimensionerendeKraft = dimensionerendeKraft;
		nulstilBoern();
	}

	@Override
	public double dimensionerendeKraftTilVaegt() {
		return (dimensionerendeKraft / tyngdekraft.getTyngdekraft());
	}
	
	@Override
	public void nulstil() {
		setDimensionerendeKraft(Double.NaN);
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
