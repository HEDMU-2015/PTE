package Logic;

import Exceptions.UdefineretProfilException;

interface Forskydningskraft extends PTEEntity{
	
	public void setForskydningskraft(double forskydningskraft);
	
	public double getForskydningskraft() throws UdefineretProfilException;

	public void nulstil();

}
