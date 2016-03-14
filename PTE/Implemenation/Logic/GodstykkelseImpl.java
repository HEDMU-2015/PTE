package Logic;

import Exceptions.LaengdeException;

public class GodstykkelseImpl extends PTEEntityImpl implements Godstykkelse{

	private double godstykkelse = Double.NaN;

	public void setGodstykkelse(double godstykkelse) {
		this.godstykkelse = godstykkelse;
	}
	
	public double getGodstykkelse(){
		return godstykkelse;
	}
	
	public void nulstil(){
		godstykkelse = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.GODSTYKKELSE;
	}
	
}
