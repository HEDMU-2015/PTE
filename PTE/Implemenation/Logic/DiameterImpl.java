package Logic;

import Exceptions.LaengdeException;

public class DiameterImpl extends PTEEntityImpl implements Diameter {
	
	private double diameter = Double.NaN;

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	public double getDiameter(){
		return diameter;
	}
	
	public void nulstil(){
		diameter = Double.NaN;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.DIAMETER;
	}
	
}
