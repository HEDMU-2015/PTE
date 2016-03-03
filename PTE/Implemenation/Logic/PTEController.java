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
	
	public void nulstil();
	
}
