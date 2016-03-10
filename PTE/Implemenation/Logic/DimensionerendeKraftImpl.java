package Logic;

import Exceptions.DimensionerendeKraftException;

class DimensionerendeKraftImpl  extends PTEEntityImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = Double.NaN;
	double vaegtFraDimensionerendeKraft = Double.NaN;
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
		if(vaegt.getVaegt()<=0){
			throw new DimensionerendeKraftException();
		}else{if(tyngdekraft.getTyngdekraft()<=0){
			throw new DimensionerendeKraftException();
		}
			
		}
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
