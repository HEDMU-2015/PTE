package Logic;

import java.util.List;
import Logic.Observer;

public interface PTEController {

	public void vaelgProfil(Profil profil);

	public void tilmeldObserver(Observer observer);

	public void notifyObservers(List<Tilstand> tilstande);

	public double getForskydningkraft();

	public void setForskydningskraft(double forskydningskraft);

	public double getTyngdekraft();

	public void setTyngdekraft(double tyngdekraft);

	public void setVinkel(double vinkel);

	public double getVinkel();

	public void setProfil(Profil profil);

	public Profil getProfil();

	public double getVaegt();

	public void setVaegt(double vaegt);

	public double getNormalkraft();

	public void setNormalkraft(double normalkraft);

	public double getDimensionerendeKraft();

	public void setDimensioneredndeKraft(double dimensioneredndeKraft);

	public void setLaengdeRetning(LaengdeRetning laengdeRetning);
	
	public double getTau_ForskydningsSpaending();

	public void setTau_ForskydningsSpaending(double tau_ForskydningsSpaending);

	public void nulstil();

	public LaengdeRetning getLaengdeRetning();

	public double getLaengde();

	public void setLaengde(double laengde);

	public double getBoejningsMoment();

	public void setBoejningsMoment(double boejningsMoment);

	public double getSigmaN();

	public void setSigmaN(double sigmaN);

	public double getForskydningspunkt();

	public void setForskydningspunkt(double forskydningspunkt);

	public double getInertimoment();

	public void setInertimoment(double inertimoment);

	public double getSigmaB();

	public void setSigmaB(double sigmaRef);

	public double getSigmaRef();

	public void setSigmaRef(double sigmaRef);

	public double getBredde();

	public void setBredde(double bredde);

	public double getDiameter();

	public void setDiameter(double diameter);

	public double getHoejde();

	public void setHoejde(double hoejde);

	public double getGodstykkelse();

	public void setGodstykkelse(double godstykkelse);

	public double getAreal();

	public void setAreal(double areal);
	
	public ProfilType getProfilType();
	
	public void setProfilType(ProfilType profilType);
}
