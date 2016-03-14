package Logic;

import Exceptions.HoejdeException;

public class HoejdeImpl extends PTEEntityImpl implements Hoejde {

	private double hoejde = Double.NaN;

	public void setHoejde(double hoejde) {
		this.hoejde = hoejde;
	}

	public double getHoejde() {
		if (hoejde < 0) {
			throw new HoejdeException();
		}
		return hoejde;
	}

	public void nulstil() {
		hoejde = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.HOEJDE;
	}

}
