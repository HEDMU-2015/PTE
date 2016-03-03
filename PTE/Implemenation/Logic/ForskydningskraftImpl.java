package Logic;

import Exceptions.UdefineretProfilException;

public class ForskydningskraftImpl implements Forskydningskraft  {
	Vinkel v;
	DimensionerendeKraft dk;
	public ForskydningskraftImpl(Vinkel v, DimensionerendeKraft dk){
		this.v = v;
		this.dk = dk;
	}
	double forskydningskraft;
	
	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningskraft = forskydningskraft;

	}

	@Override
	public double getForskydningskraft() throws UdefineretProfilException {
		double vinkel = v.getVinkel();
		Profil profil = v.getProfil();
		
		if (profil == Profil.VANDRET){
			return (Math.cos(vinkel)*dk.getDimensionerendeKraft());
		}
		else if (profil == Profil.LODRET){
			return (Math.sin(vinkel)*dk.getDimensionerendeKraft());
		}
		else throw new UdefineretProfilException("Udefineret Profil");
	}

	@Override
	public void nulstil() {
		//SD ikke fundet, lavet ud fra gætværk
		forskydningskraft = 0;
	}
	
	
}
