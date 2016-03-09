package Logic;

class VinkelImpl extends PTEEntityImpl implements Vinkel {
	double vinkel;
	Profil profil;
	
	@Override
	public void setVinkel(double vinkel) {
		this.vinkel = vinkel;
		vinkel = Double.NaN;
	}

	@Override
	public double getVinkel() {
		return vinkel;
	}

	@Override
	public void setProfil(Profil profil) {
		this.profil = profil;	
	}

	@Override
	public Profil getProfil() {
		return profil;
	}

	@Override
	public void nulstil() {
		setVinkel(Double.NaN);
		profil = Profil.UDEFINERET;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.VINKEL;
	}

}
