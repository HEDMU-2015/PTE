package Logic;

public class ForskydningspunktImpl extends PTEEntityImpl implements Forskydningspunkt {

	private double forskydningspunkt=Double.NaN;

	@Override
	public double getForskydningspunkt() {
		return forskydningspunkt;
	}

	@Override
	public void setForskydningspunkt(double forskydningspunkt) {
		this.forskydningspunkt = forskydningspunkt;
	}

	@Override
	public void nulstil() {
		setForskydningspunkt(Double.NaN);

	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.FORSKYDNINGSPUNKT;
	}

}
