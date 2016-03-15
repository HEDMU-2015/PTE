package Logic;

public class InertimomentImpl extends PTEEntityImpl implements Inertimoment {
	
	private double inertimoment = Double.NaN;

	@Override
	public double getInertimoment() {
		return inertimoment;
	}

	@Override
	public void setInertimoment(double inertimoment) {
		this.inertimoment = inertimoment;
	}

	@Override
	public void nulstil() {
		setInertimoment(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {

		return Tilstand.INERTIMOMENT;
	}

}
