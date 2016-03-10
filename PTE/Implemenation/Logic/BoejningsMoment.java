package Logic;

import Exceptions.UdefineretProfilException;

public interface BoejningsMoment extends PTEEntity{

	public double getBoejningsMoment()  throws UdefineretProfilException;

	public void setBoejningsMoment(double BoejningsMoment);
	
	public void setDimensionerendeKraft(DimensionerendeKraft dimensionerendeKraft);
	
	public void setForskydningskraft(Forskydningskraft forskydningskraft);
	
	public void nulstil();
	
}
