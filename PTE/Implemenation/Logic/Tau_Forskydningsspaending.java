package Logic;


import Exceptions.UdefineretProfilException;

interface Tau_Forskydningsspaending extends PTEEntity{

	public void setTau_Forskydningsspaending(double tau_Forskydningsspaending);
	public double getTau_Forskydningsspaending() throws UdefineretProfilException;
	public void nulstil();
	
}
