package Logic;

class SikkerhedsfaktorImpl extends PTEEntityImpl implements Sikkerhedsfaktor {

	private double sikkerhedsfaktor = Double.NaN;
	private Flydespaending flydespaending;
	private Referencespaending sigmaRef;

	public SikkerhedsfaktorImpl(Flydespaending flydespaending, Referencespaending sigmaRef) {
		this.flydespaending = flydespaending;
		this.sigmaRef = sigmaRef;
		this.flydespaending.tilfoejAfhaengigEntitet(this);
		this.sigmaRef.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setSikkerhedsfaktor(double sikkerhedsfaktor) {
		this.sikkerhedsfaktor = sikkerhedsfaktor;
		nulstilBoern();
	}

	@Override
	public double getSikkerhedsfaktor() {
		return getSikkerhedsfaktor(flydespaending.getFlydespaending(), sigmaRef.getSigmaRef());
	}

	double getSikkerhedsfaktor(double flydespaending, double referencespaending) {
		return flydespaending / referencespaending;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIKKERHEDSFAKTOR;
	}

	@Override
	public boolean erSikkerhedsfaktorForLavt() {
		return sikkerhedsfaktor <= 1;
	}

	@Override
	public void nulstil() {
		sikkerhedsfaktor = Double.NaN;
		nulstilBoern();
	}

	public void nulstilBoern() {
		flydespaending.nulstil();
		sigmaRef.nulstil();
	}

}
