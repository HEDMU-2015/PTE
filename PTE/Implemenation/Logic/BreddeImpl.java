package Logic;

public class BreddeImpl extends PTEEntityImpl implements Bredde {

	private double bredde = Double.NaN;

	public void setBredde(double bredde) {
		this.bredde = bredde;
	}
	
	public double getBredde(){
		return bredde;
	}
	
	public void nulstil(){
		bredde = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.BREDDE;
	}
	
}
