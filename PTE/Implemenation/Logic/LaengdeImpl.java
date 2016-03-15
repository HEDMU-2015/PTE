package Logic;

import Exceptions.LaengdeException;

class LaengdeImpl extends PTEEntityImpl implements Laengde {
	
	private double laengde = Double.NaN;

	@Override
	public double getLaengde() {
		
		return laengde;
	}

	@Override
	public void setLaengde(double laengde) {
		if (laengde < 0) {
			throw new LaengdeException();
		}
		this.laengde = laengde;
	}

	@Override
	public double Laengde() {
		return laengde;
	}

	@Override
	public void nulstil() {
		setLaengde(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.LAENGDE;
	}

}
