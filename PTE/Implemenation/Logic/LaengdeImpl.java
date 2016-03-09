package Logic;

class LaengdeImpl extends PTEEntityImpl implements Laengde {
	private double Laengde;

	@Override
	public double getLaengde() {
		return Laengde;
	}

	@Override
	public void setLaengde(double Laengde) {
		this.Laengde = Laengde;
	}
 
	@Override
	public double Laengde() {
		return Laengde;
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
