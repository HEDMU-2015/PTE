package Logic;

import Exceptions.ArealException;

/**
 * 
 * @author Juyoung Choi
 *
 */


public class ArealImpl  extends PTEEntityImpl implements Areal {

	private double areal = Double.NaN;
	
	@Override
	public void setAreal(double areal) {
		this.areal = areal;
	}

	@Override
	public double getAreal() {
		if(areal<=0){
			areal = Double.NaN; // fra OC ; slet dette input
			throw new ArealException("Areal Exception : areal er 0 eller mindre.");
		}
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
