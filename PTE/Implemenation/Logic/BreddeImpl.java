package Logic;

import Exceptions.BreddeException;

public class BreddeImpl extends PTEEntityImpl implements Bredde {

	private double bredde = Double.NaN;

	public void setBredde(double bredde) {
		this.bredde = bredde;
	}

	public double getBredde() {
		if (bredde < 0) {
			throw new BreddeException();
		}
		return bredde;
	}

	public void nulstil() {
		bredde = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.BREDDE;
	}

}
