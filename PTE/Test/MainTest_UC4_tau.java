

import Logic.PTEController;
import Logic.PTEControllerImpl;
import Logic.Profil;

public class MainTest_UC4_tau {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PTEController p = new PTEControllerImpl();
		p.setVinkel(16);
		p.setProfil(Profil.VANDRET);
		p.setDimensioneredndeKraft(690*9.816);
		p.setAreal(100);
		double tau = p.getTau_ForskydningsSpaending();
		System.out.println("tau : " + tau);
	}

}
