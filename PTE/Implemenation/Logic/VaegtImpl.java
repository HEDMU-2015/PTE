package Logic;

class VaegtImpl extends PTEEntityImpl implements Vaegt {
	
	private double vaegt;
	
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
		setVaegt(0);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.VAEGT;
	}

}
