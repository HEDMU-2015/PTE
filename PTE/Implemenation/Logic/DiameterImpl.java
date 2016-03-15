package Logic;

import Exceptions.DiameterException;

public class DiameterImpl extends PTEEntityImpl implements Diameter {

	private double diameter = Double.NaN;

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getDiameter() {
		if (diameter < 0) {
			throw new DiameterException();
		}
		return diameter;
	}

	public void nulstil() {
		diameter = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.DIAMETER;
	}

}
