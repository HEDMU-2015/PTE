package Logic;

public class NormalkraftImpl implements Normalkraft {

	private double normalkraft = 0;
	private DimensionerendeKraft dimensionerendeKraft;
	private Vinkel vinkel;
	
	public NormalkraftImpl(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel){
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.vinkel = vinkel;
	}
	
	@Override
	public double getNormalkraft() {
		normalkraft = Math.sin(vinkel.getVinkel()) * dimensionerendeKraft.getDimensionerendeKraft();

		return normalkraft;
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalkraft = normalkraft;
		
	}
	
	@Override
	public void nulstil() {
		setNormalkraft(0);
	}

}
