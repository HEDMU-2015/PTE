package Logic;

import Exceptions.VinkelException;

class VinkelImpl extends PTEEntityImpl implements Vinkel {
	private double vinkel = Double.NaN;
	private Profil profil = Profil.UDEFINERET;
	private LaengdeRetning laengdeRetning;

	@Override
	public void setVinkel(double vinkel) {
		if (vinkel > 90) {
			throw new VinkelException();
		} 
		if (vinkel < 0) {
			throw new VinkelException();
			}
	
		this.vinkel = vinkel;
		
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
		vinkel = Double.NaN;
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