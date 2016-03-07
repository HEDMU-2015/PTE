package Logic;

import Exceptions.UdefineretProfilException;

interface Normalkraft  extends PTEEntity {

	public double getNormalkraft()  throws UdefineretProfilException ;
	
	public void setNormalkraft(double normalkraft);
	
	public void nulstil();
	
}
