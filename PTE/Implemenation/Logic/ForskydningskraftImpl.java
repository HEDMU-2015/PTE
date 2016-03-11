package Logic;

import Exceptions.ForskydningskraftException;
import Exceptions.UdefineretProfilException;

class ForskydningskraftImpl extends PTEEntityImpl implements Forskydningskraft {

	private double forskydningskraft = Double.NaN;
	private Vinkel vinkel;
	private DimensionerendeKraft dimensionerendeKraft;

	public ForskydningskraftImpl(Vinkel vinkel, DimensionerendeKraft dimensionerendeKraft) {
		if (vinkel == null || dimensionerendeKraft == null) {
			throw new IllegalArgumentException();
		}
		this.vinkel = vinkel;
		this.dimensionerendeKraft = dimensionerendeKraft;
		this.vinkel.tilfoejAfhaengigEntitet(this);
		this.dimensionerendeKraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningskraft = forskydningskraft;
		nulstilBoern();
	}

	@Override
	public double getForskydningskraft() {
		return getForskydningskraft(vinkel.getProfil(), vinkel.getVinkel(), dimensionerendeKraft.getDimensionerendeKraft());

	}

	double getForskydningskraft(Profil profil, double vinkel, double dimensionerendeKraft) {
		
		if(vinkel == Double.NaN
				|| dimensionerendeKraft <= 0
				|| dimensionerendeKraft == Double.NaN 
				|| vinkel < 0 
				|| vinkel > 90 
				){
			throw new ForskydningskraftException();
		}
		
		if (profil == Profil.VANDRET) {
			return (Math.cos(Math.toRadians(vinkel)) * dimensionerendeKraft);
		} else if (profil == Profil.LODRET) {
			return (Math.sin(Math.toRadians(vinkel)) * dimensionerendeKraft);
		} else {
			throw new UdefineretProfilException("Udefineret Profil");
		}
		
	}

	@Override
	public void nulstil() {
		setForskydningskraft(Double.NaN);
		nulstilBoern();
	}
	
	private void nulstilBoern() {
		vinkel.nulstil();
		dimensionerendeKraft.nulstil();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.FORSKYDNINGSKRAFT;
	}

}
