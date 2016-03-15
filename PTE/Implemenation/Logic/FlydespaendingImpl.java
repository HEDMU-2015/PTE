package Logic;

class FlydespaendingImpl extends PTEEntityImpl implements Flydespaending {

	private double flydespaending = Double.NaN;

	@Override
	public void setFlydespaending(double flydspaending) {
		this.flydespaending = flydspaending;
	}

	@Override
	public double getFlydespaending() {
		return flydespaending;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.FLYDESPAENDING;
	}

	@Override
	public void nulstil() {
		flydespaending = Double.NaN;
	}

}
