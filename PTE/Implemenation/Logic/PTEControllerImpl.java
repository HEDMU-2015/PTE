package Logic;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UdefineretProfilException;

/**
 * @author Tsvetelin Tsonev - <tsvetelin.tsonev@yahoo.co.uk>
 *
 */
public class PTEControllerImpl implements PTEController {

	private Vinkel vinkel;
	private Vaegt vaegt;
	private DimensionerendeKraft dimensionerendeKraft;
	private Normalkraft normalKraft;
	private Tyngdekraft tyngdeKraft;
	private Forskydningskraft forskydningsKraft;
	private Areal areal;
	private Tau_Forskydningsspaending tau_Forskydningsspaending;
	private Laengde laengde;
	private List<Observer> observers;
	private LogicFactory logicFactory;
	private LaengdeRetning laengdeRetning;
	private BoejningsMoment boejningsMoment;
	

	public PTEControllerImpl() {
		logicFactory = new LogicFactoryImpl();
		vinkel = logicFactory.createVinkel();
		vinkel.setProfil(Profil.UDEFINERET);
		vaegt = logicFactory.createVaegt();
		tyngdeKraft = logicFactory.craeteTyngdeKraft();
		dimensionerendeKraft = logicFactory.craeteDimensionerendeKraft(vaegt, tyngdeKraft);
		normalKraft = logicFactory.createNormalKraft(dimensionerendeKraft, vinkel);
		forskydningsKraft = logicFactory.createForskydningskraft(vinkel, dimensionerendeKraft);
		areal = logicFactory.createAreal();
		tau_Forskydningsspaending = logicFactory.createTau_Forskydningsspaending(areal, forskydningsKraft);	
		observers = new ArrayList<Logic.Observer>();
	}

	@Override
	public void vaelgProfil(Profil profil) {
		this.vinkel.setProfil(profil);
		notifyObservers(vinkel.getAfhaengigheder());
	}

	@Override
	public void tilmeldObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(List<Tilstand> tilstande) {
		for (Logic.Observer o : observers) {
			o.update(tilstande);
		}
	}

	@Override
	public double getForskydningkraft() {
		double fN = Double.NaN;
		try {
			fN = this.forskydningsKraft.getForskydningskraft();
		} catch (UdefineretProfilException e) {

		}
		return fN;
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningsKraft.setForskydningskraft(forskydningskraft);
		notifyObservers(forskydningsKraft.getAfhaengigheder());
	}

	@Override
	public double getTyngdekraft() {
		return this.tyngdeKraft.getTyngdekraft();
	}

	@Override
	public void setTyngdekraft(double tyngdekraft) {
		this.setTyngdekraft(tyngdekraft);
		notifyObservers(tyngdeKraft.getAfhaengigheder());

	}

	@Override
	public void setVinkel(double vinkel) {
		this.vinkel.setVinkel(vinkel);
		notifyObservers(this.vinkel.getAfhaengigheder());
	}

	@Override
	public double getVinkel() {
		return this.vinkel.getVinkel();
	}

	@Override
	public void setProfil(Profil profil) {
		this.vinkel.setProfil(profil);
		notifyObservers(vinkel.getAfhaengigheder());
	}

	@Override
	public Profil getProfil() {
		return this.vinkel.getProfil();
	}

	@Override
	public double getVaegt() {
		return this.vaegt.getVaegt();
	}

	@Override
	public void setVaegt(double vaegt) {
		this.vaegt.setVaegt(vaegt);
		notifyObservers(this.vaegt.getAfhaengigheder());
	}

	@Override
	public double getNormalkraft() {
		double normalkraft = Double.NaN;
		try {
			normalkraft = this.normalKraft.getNormalkraft();
		} catch (UdefineretProfilException e) {
			// Det sker aldrig
		}
		return normalkraft;
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalKraft.setNormalkraft(normalkraft);
		notifyObservers(normalKraft.getAfhaengigheder());
	}

	@Override
	public double getDimensionerendeKraft() {
		return this.dimensionerendeKraft.getDimensionerendeKraft();
	}

	@Override
	public void setDimensioneredndeKraft(double dimensionerendeKraft) {
		this.dimensionerendeKraft.setDimensionerendeKraft(dimensionerendeKraft);
		vaegt.setVaegt(this.dimensionerendeKraft.dimensionerendeKraftTilVaegt());
		notifyObservers(this.vaegt.getAfhaengigheder());
	}
	
	
	@Override
	public double getAreal() {
		return this.areal.getAreal();
	}

	@Override
	public void setAreal(double areal) {
		this.areal.setAreal(areal);
		notifyObservers(this.areal.getAfhaengigheder());
	}
		
	

	@Override
	public double getTau_Forskydningsspaending() {
		double tau_Forskydningsspaending = Double.NaN;
		try {
			tau_Forskydningsspaending = this.tau_Forskydningsspaending.getTau_Forskydningsspaending();
		} catch (UdefineretProfilException e) {

		}
		return tau_Forskydningsspaending;
	}

	@Override
	public void setTau_Forskydningsspaending(double tau_Forskydningsspaending) {
		this.tau_Forskydningsspaending.setTau_Forskydningsspaending(tau_Forskydningsspaending);
		notifyObservers(this.tau_Forskydningsspaending.getAfhaengigheder());		
	}

	@Override
	public void nulstil() {
		List<Tilstand> tilstande = new ArrayList<Tilstand>();

		this.vaegt.nulstil();
		tilstande.addAll(vaegt.getAfhaengigheder());

		this.vinkel.nulstil();
		tilstande.addAll(vinkel.getAfhaengigheder());

		this.tyngdeKraft.nulstil();
		tilstande.addAll(tyngdeKraft.getAfhaengigheder());

		this.dimensionerendeKraft.nulstil();
		tilstande.addAll(dimensionerendeKraft.getAfhaengigheder());

		this.normalKraft.nulstil();
		tilstande.addAll(normalKraft.getAfhaengigheder());

		this.forskydningsKraft.nulstil();
		tilstande.addAll(forskydningsKraft.getAfhaengigheder());
		
		this.areal.nulstil();
		tilstande.addAll(areal.getAfhaengigheder());
		
		this.tau_Forskydningsspaending.nulstil();
		tilstande.addAll(tau_Forskydningsspaending.getAfhaengigheder());

		notifyObservers(tilstande);
	}

	@Override
	public LaengdeRetning getLaengdeRetning() {
		return this.vinkel.getLaengdeRetning();
	}

	@Override
	public void setLaengdeRetning(LaengdeRetning laengdeRetning) {
		this.laengdeRetning = laengdeRetning;
		notifyObservers(this.vinkel.getAfhaengigheder());
	}

	@Override
	public double getLaengde() {
		return this.laengde.getLaengde();
	}

	@Override
	public void setLaengde(double laengde) {
		this.laengde.setLaengde(laengde);
		notifyObservers(this.laengde.getAfhaengigheder());
	}

	@Override
	public double getBoejningsMoment() {
		double boejningsMoment = Double.NaN;
		try {
			boejningsMoment = this.boejningsMoment.getBoejningsMoment();
		} catch (UdefineretProfilException e) {
			e.printStackTrace();
		}
		return boejningsMoment;
	}

	@Override
	public void setBoejningsMoment(BoejningsMoment boejningsMoment) {
		this.boejningsMoment = boejningsMoment;
		notifyObservers(this.boejningsMoment.getAfhaengigheder());
	}

}
