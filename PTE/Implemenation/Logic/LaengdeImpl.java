package Logic;

class LaengdeImpl extends PTEEntityImpl implements Laengde {
	private double laengde;

	@Override
	public double getLaengde() {
		return laengde;
	}

	@Override
	public void setLaengde(double laengde) {
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
