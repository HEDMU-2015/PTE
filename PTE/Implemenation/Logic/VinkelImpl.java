package Logic;

import Exceptions.VinkelException;

class VinkelImpl extends PTEEntityImpl implements Vinkel {
	double vinkel = Double.NaN;
	Profil profil;
	LaengdeRetning laengdeRetning;

	@Override
	public void setVinkel(double vinkel) {
		this.vinkel = vinkel;
		
	}

	@Override
	public double getVinkel() {
		if (vinkel > 90) {
			throw new VinkelException();
		} 
		if (vinkel < 0) {
			throw new VinkelException();
			}
		

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
	@Override
	public void setLaengdeRetning(LaengdeRetning laengdeRetning) {
		this.laengdeRetning = laengdeRetning;		
	}

	@Override
	public LaengdeRetning getLaengdeRetning() {
		return laengdeRetning;
	}

}