package Logic;

class VaegtImpl extends PTEEntityImpl implements Vaegt {
	
	private double vaegt = Double.NaN;
	
	@Override
	public double getVaegt() {
		return vaegt;
	}

	@Override
	public void setVaegt(double vaegt) {
		this.vaegt = vaegt;
	} 

	@Override
	public void nulstil() {
		setVaegt(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.VAEGT;
	}

}
