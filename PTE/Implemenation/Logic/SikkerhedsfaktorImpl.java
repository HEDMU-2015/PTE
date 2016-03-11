package Logic;

class SikkerhedsfaktorImpl extends PTEEntityImpl implements Sikkerhedsfaktor {

	double sikkerhedsfaktor = Double.NaN;
	Flydespaending flydespaending;
	SigmaRef sigmaRef;
	
	public SikkerhedsfaktorImpl(Flydespaending flydespaending, SigmaRef sigmaRef) {
		this.flydespaending = flydespaending;
		this.sigmaRef = sigmaRef;
	}
	
	@Override
	public void setSikkerhedsfaktor(double sikkerhedsfaktor) {
		this.sikkerhedsfaktor = sikkerhedsfaktor;
	}

	@Override
	public double getSikkerhedsfaktor() {
		return flydespaending.getFlydespaending() / sigmaRef.getSigmaRef();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIKKERHEDSFAKTOR;
	}

	@Override
	public boolean erSikkerhedsfaktorForLavt() {
		return sikkerhedsfaktor <= 1;
	}

}
