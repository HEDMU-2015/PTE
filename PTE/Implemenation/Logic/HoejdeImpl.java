package Logic;

public class HoejdeImpl extends PTEEntityImpl implements Hoejde {

	private double hoejde = Double.NaN;

	public void setHoejde(double hoejde) {
		this.hoejde = hoejde;
	}
	
	public double getHoejde(){
		return hoejde;
	}
	
	public void nulstil(){
		hoejde = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.HOEJDE;
	}
	
}
