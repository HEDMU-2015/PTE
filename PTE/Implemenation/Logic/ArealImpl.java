package Logic;


public class ArealImpl  extends PTEEntityImpl implements Areal {

	private double areal = Double.NaN;
	
	@Override
	public void setAreal(double areal) {
		this.areal = areal;
	}

	@Override
	public double getAreal() {
		return areal;
	}

	@Override
	public void nulstil() {
		setAreal(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
	
		return Tilstand.AREAL;
	}

}
