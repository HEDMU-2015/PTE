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
	private Tau_ForskydningsSpaending tau_ForskydningsSpaending;
	private Laengde laengde;
	private List<Observer> observers;
	private LogicFactory logicFactory;
	private BoejningsMoment boejningsMoment;
	private SigmaN sigmaN;
	private Forskydningspunkt forskydningspunkt;
	private Inertimoment inertimoment;
	private SigmaB sigmaB;
	private SigmaRef sigmaRef;

	public PTEControllerImpl() {
		logicFactory = new LogicFactoryImpl();
		vinkel = logicFactory.createVinkel();
		vinkel.setProfil(Profil.UDEFINERET);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		vaegt = logicFactory.createVaegt();
		tyngdeKraft = logicFactory.craeteTyngdeKraft();
		dimensionerendeKraft = logicFactory.craeteDimensionerendeKraft(vaegt, tyngdeKraft);
		normalKraft = logicFactory.createNormalKraft(dimensionerendeKraft, vinkel);
		forskydningsKraft = logicFactory.createForskydningskraft(vinkel, dimensionerendeKraft);
		areal = logicFactory.createAreal();
		tau_ForskydningsSpaending = logicFactory.createTau_ForskydningsSpaending(areal, forskydningsKraft);	
		laengde = logicFactory.createLaengde();
		boejningsMoment = logicFactory.createBoejningsMoment(vinkel, laengde);
		sigmaN = logicFactory.createSigmaN(areal, normalKraft);
		forskydningspunkt = logicFactory.createForskydningspunkt();
		inertimoment = logicFactory.createInertimoment();
		sigmaB = logicFactory.createSigmaB(boejningsMoment, forskydningspunkt, inertimoment);
		sigmaRef = logicFactory.createSigmaRef(sigmaB, sigmaN, tau_ForskydningsSpaending);
		
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
		
		this.tau_ForskydningsSpaending.nulstil();
		tilstande.addAll(tau_ForskydningsSpaending.getAfhaengigheder());

		this.sigmaN.nulstil();
		tilstande.addAll(sigmaN.getAfhaengigheder());
		
		this.forskydningspunkt.nulstil();
		tilstande.addAll(forskydningspunkt.getAfhaengigheder());
		
		this.inertimoment.nulstil();
		tilstande.addAll(inertimoment.getAfhaengigheder());
		
		this.sigmaB.nulstil();
		tilstande.addAll(sigmaB.getAfhaengigheder());
		
		notifyObservers(tilstande);
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
	public double getTau_ForskydningsSpaending() {
		double tau_ForskydningsSpaending = Double.NaN;
		tau_ForskydningsSpaending = this.tau_ForskydningsSpaending.getTau_ForskydningsSpaending();
		return tau_ForskydningsSpaending;
	}

	@Override
	public void setTau_ForskydningsSpaending(double tau_ForskydningsSpaending) {
		this.tau_ForskydningsSpaending.setTau_ForskydningsSpaending(tau_ForskydningsSpaending);
		notifyObservers(this.tau_ForskydningsSpaending.getAfhaengigheder());		
	}

	
	@Override
	public LaengdeRetning getLaengdeRetning() {
		return this.vinkel.getLaengdeRetning();
	}

	@Override
	public void setLaengdeRetning(LaengdeRetning laengdeRetning) {
		this.vinkel.setLaengdeRetning(laengdeRetning);
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
				this.boejningsMoment.setDimensionerendeKraft(dimensionerendeKraft);
				this.boejningsMoment.setForskydningskraft(forskydningsKraft);
				boejningsMoment = this.boejningsMoment.getBoejningsMoment();
			
		} catch (UdefineretProfilException e) {
			e.printStackTrace();
		}
		return boejningsMoment;
	}

	@Override
	public void setBoejningsMoment(double boejningsMoment) {
		this.boejningsMoment.setBoejningsMoment(boejningsMoment);
		notifyObservers(this.boejningsMoment.getAfhaengigheder());
	}

	@Override
	public double getSigmaN() {
		double sigmaN = Double.NaN;
		sigmaN = this.sigmaN.getSigmaN();
		return sigmaN;
	}

	@Override
	public void setSigmaN(double sigmaN) {
		this.sigmaN.setSigmaN(sigmaN);
		notifyObservers(this.sigmaN.getAfhaengigheder());	
		
	}

	@Override
	public double getForskydningspunkt() {
		double forskydningspunkt = Double.NaN;
		forskydningspunkt = this.forskydningspunkt.getForskydningspunkt();
		return forskydningspunkt;
	}

	@Override
	public void setForskydningspunkt(double forskydningspunkt) {
		this.forskydningspunkt.setForskydningspunkt(forskydningspunkt);;
		notifyObservers(this.forskydningspunkt.getAfhaengigheder());
		
	}

	@Override
	public double getInertimoment() {
		double inertimoment = Double.NaN;
		inertimoment = this.inertimoment.getInertimoment();
		return inertimoment;
	}

	@Override
	public void setInertimoment(double inertimoment) {
		this.inertimoment.setInertimoment(inertimoment);
		notifyObservers(this.inertimoment.getAfhaengigheder());
		
	}

	@Override
	public double getSigmaB() {
		double sigmaB = Double.NaN;
		sigmaB = this.sigmaB.getSigmaB();		
		return sigmaB;
	}

	@Override
	public void setSigmaB(double sigmaB) {
		this.sigmaB.setSigmaB(sigmaB);
		notifyObservers(this.sigmaB.getAfhaengigheder());
		
	}

	@Override
	public double getSigmaRef() {
		double sigmaRef = Double.NaN;
		sigmaRef = this.sigmaRef.getSigmaRef();
		return sigmaRef;
	}

	@Override
	public void setSigmaRef(double sigmaRef) {
		this.sigmaRef.setSigmaRef(sigmaRef);
		notifyObservers(this.sigmaB.getAfhaengigheder());
	}

	

}
