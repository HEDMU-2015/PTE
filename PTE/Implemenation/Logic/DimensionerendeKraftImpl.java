package Logic;

class DimensionerendeKraftImpl implements DimensionerendeKraft {

	private double dimensionerendeKraft = 0;
	private Vaegt vaegt;
	private Tyngdekraft tyngdekraft;

	public DimensionerendeKraftImpl(Vaegt vaegt, Tyngdekraft tyngdekraft){
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
	public void nulstil() {
		vaegt.nulstil();
		tyngdekraft.nulstil();
	}

}
