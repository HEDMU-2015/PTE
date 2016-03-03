package Logic;

public class VinkelImpl implements Vinkel {
	double vinkel;
	Profil profil;
	
	@Override
	public void setVinkel(double vinkel) {
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
		//SD ikke fundet, lavet ud fra gætværk
		profil = null;
		vinkel = 0;

	}

}
