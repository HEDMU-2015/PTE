package Logic;

import Exceptions.ArealException;

public class IndtastArealImpl extends PTEEntityImpl implements IndtastAreal {

	private double areal = Double.NaN;

	@Override
	public void setIndtastAreal(double areal) {
		this.areal = areal;
	}

	@Override
	public double getIndtastAreal() {
		if (areal <= 0) {
			areal = Double.NaN;
			throw new ArealException("Areal Exception : areal er 0 eller mindre.");
		}
		return areal;
	}

	@Override
	public void nulstil() {
		setIndtastAreal(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.INDTAST_AREAL;
	}

}