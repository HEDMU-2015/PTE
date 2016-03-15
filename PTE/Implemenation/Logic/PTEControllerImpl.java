package Logic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tsvetelin Tsonev - <tsvetelin.tsonev@yahoo.co.uk>
 *
 */
public class PTEControllerImpl implements PTEController {

	private Vinkel vinkel;
	private Vaegt vaegt;
	private DimensionerendeKraft dimensionerendeKraft;
	private Normalkraft normalkraft;
	private Tyngdekraft tyngdekraft;
	private Forskydningskraft forskydningskraft;
	private Areal areal;
	private Forskydningsspaending forskydningsspaending;
	private Laengde laengde;
	private List<Observer> observers;
	private BoejningsMoment boejningsmoment;
	private Normalspaending normalspaending;
	private Forskydningspunkt forskydningspunkt;
	private Inertimoment inertimoment;
	private Boejningsspaending boejningsspaending;
	private Referencespaending referencespaending;
	private Bredde bredde;
	private Diameter diameter;
	private Hoejde hoejde;
	private Godstykkelse godstykkelse;
	private Form form;
	private IndtastAreal indtastAreal;
	private Flydespaending flydeSpaending;
	private Sikkerhedsfaktor sikkerhedsfaktor;

	public PTEControllerImpl(LogicFactory logicFactory) {
		vinkel = logicFactory.createVinkel();
		vinkel.setProfil(Profil.UDEFINERET);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		form = logicFactory.createForm();
		form.setProfilType(ProfilType.CIRKEL);
		flydeSpaending = logicFactory.createFlydespaendning();
		vaegt = logicFactory.createVaegt();
		tyngdekraft = logicFactory.craeteTyngdeKraft();
		dimensionerendeKraft = logicFactory.craeteDimensionerendeKraft(vaegt, tyngdekraft);
		normalkraft = logicFactory.createNormalKraft(dimensionerendeKraft, vinkel);
		forskydningskraft = logicFactory.createForskydningskraft(vinkel, dimensionerendeKraft);
		bredde = logicFactory.createBredde();
		diameter = logicFactory.createDiameter();
		godstykkelse = logicFactory.createGodstykkelse();
		hoejde = logicFactory.createHoejde();
		areal = logicFactory.createAreal(bredde, diameter, godstykkelse, hoejde, form);
		forskydningsspaending = logicFactory.createTau_ForskydningsSpaending(areal, forskydningskraft);
		laengde = logicFactory.createLaengde();
		boejningsmoment = logicFactory.createBoejningsMoment(vinkel, laengde, dimensionerendeKraft, forskydningskraft);
		normalspaending = logicFactory.createSigmaN(areal, normalkraft);
		forskydningspunkt = logicFactory.createForskydningspunkt();
		inertimoment = logicFactory.createInertimoment();
		boejningsspaending = logicFactory.createSigmaB(boejningsmoment, forskydningspunkt, inertimoment);
		referencespaending = logicFactory.createSigmaRef(boejningsspaending, normalspaending, forskydningsspaending);
		indtastAreal = logicFactory.createIndtastAreal();
		sikkerhedsfaktor = logicFactory.createSikkerhedsfaktor(flydeSpaending, referencespaending);
		
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

		this.tyngdekraft.nulstil();
		tilstande.addAll(tyngdekraft.getAfhaengigheder());

		this.dimensionerendeKraft.nulstil();
		tilstande.addAll(dimensionerendeKraft.getAfhaengigheder());

		this.normalkraft.nulstil();
		tilstande.addAll(normalkraft.getAfhaengigheder());

		this.forskydningskraft.nulstil();
		tilstande.addAll(forskydningskraft.getAfhaengigheder());

		this.areal.nulstil();
		tilstande.addAll(areal.getAfhaengigheder());

		this.forskydningsspaending.nulstil();
		tilstande.addAll(forskydningsspaending.getAfhaengigheder());

		this.normalspaending.nulstil();
		tilstande.addAll(normalspaending.getAfhaengigheder());

		this.forskydningspunkt.nulstil();
		tilstande.addAll(forskydningspunkt.getAfhaengigheder());

		this.inertimoment.nulstil();
		tilstande.addAll(inertimoment.getAfhaengigheder());

		this.boejningsspaending.nulstil();
		tilstande.addAll(boejningsspaending.getAfhaengigheder());

		this.laengde.nulstil();
		tilstande.addAll(laengde.getAfhaengigheder());

		this.bredde.nulstil();
		tilstande.addAll(bredde.getAfhaengigheder());

		this.hoejde.nulstil();
		tilstande.addAll(hoejde.getAfhaengigheder());

		this.diameter.nulstil();
		tilstande.addAll(diameter.getAfhaengigheder());

		this.godstykkelse.nulstil();
		tilstande.addAll(godstykkelse.getAfhaengigheder());
		
		this.normalspaending.nulstil();
		tilstande.addAll(normalspaending.getAfhaengigheder());
		
		this.forskydningspunkt.nulstil();
		tilstande.addAll(forskydningspunkt.getAfhaengigheder());
		
		this.inertimoment.nulstil();
		tilstande.addAll(inertimoment.getAfhaengigheder());
		
		this.referencespaending.nulstil();
		tilstande.addAll(referencespaending.getAfhaengigheder());
		
		notifyObservers(tilstande);
	}

	@Override
	public double getForskydningkraft() {
		return this.forskydningskraft.getForskydningskraft();
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningskraft.setForskydningskraft(forskydningskraft);
		notifyObservers(this.forskydningskraft.getAfhaengigheder());
	}

	@Override
	public double getTyngdekraft() {
		return this.tyngdekraft.getTyngdekraft();
	}

	@Override
	public void setTyngdekraft(double tyngdekraft) {
		this.setTyngdekraft(tyngdekraft);
		notifyObservers(this.tyngdekraft.getAfhaengigheder());
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
		return this.normalkraft.getNormalkraft();
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalkraft.setNormalkraft(normalkraft);
		notifyObservers(this.normalkraft.getAfhaengigheder());
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
	public double getTau_ForskydningsSpaending() {
		return this.forskydningsspaending.getTau_ForskydningsSpaending();
	}

	@Override
	public void setTau_ForskydningsSpaending(double tau_ForskydningsSpaending) {
		this.forskydningsspaending.setTau_ForskydningsSpaending(tau_ForskydningsSpaending);
		notifyObservers(this.forskydningsspaending.getAfhaengigheder());
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
		boejningsMoment = this.boejningsmoment.getBoejningsMoment();
		return boejningsMoment;
	}

	@Override
	public void setBoejningsMoment(double boejningsMoment) {
		this.boejningsmoment.setBoejningsMoment(boejningsMoment);
		notifyObservers(this.boejningsmoment.getAfhaengigheder());
	}

	@Override
	public double getSigmaN() {
		double sigmaN = Double.NaN;
		sigmaN = this.normalspaending.getSigmaN();
		return sigmaN;
	}

	@Override
	public void setSigmaN(double sigmaN) {
		this.normalspaending.setSigmaN(sigmaN);
		notifyObservers(this.normalspaending.getAfhaengigheder());
	}

	@Override
	public double getForskydningspunkt() {
		double forskydningspunkt = Double.NaN;
		forskydningspunkt = this.forskydningspunkt.getForskydningspunkt();
		return forskydningspunkt;
	}

	@Override
	public void setForskydningspunkt(double forskydningspunkt) {
		this.forskydningspunkt.setForskydningspunkt(forskydningspunkt);
		;
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
		sigmaB = this.boejningsspaending.getSigmaB();
		return sigmaB;
	}

	@Override
	public void setSigmaB(double sigmaB) {
		this.boejningsspaending.setSigmaB(sigmaB);
		notifyObservers(this.boejningsspaending.getAfhaengigheder());

	}

	@Override
	public double getSigmaRef() {
		double sigmaRef = Double.NaN;
		sigmaRef = this.referencespaending.getSigmaRef();
		return sigmaRef;
	}

	@Override
	public void setSigmaRef(double sigmaRef) {
		this.referencespaending.setSigmaRef(sigmaRef);
		notifyObservers(this.boejningsspaending.getAfhaengigheder());
	}

	@Override
	public double getBredde() {
		double bredde = Double.NaN;
		bredde = this.bredde.getBredde();
		return bredde;
	}

	@Override
	public void setBredde(double bredde) {
		this.bredde.setBredde(bredde);
		notifyObservers(this.bredde.getAfhaengigheder());
	}

	@Override
	public double getDiameter() {
		double diameter = Double.NaN;
		diameter = this.diameter.getDiameter();
		return diameter;
	}

	@Override
	public void setDiameter(double diameter) {
		this.diameter.setDiameter(diameter);
		notifyObservers(this.diameter.getAfhaengigheder());
	}

	@Override
	public double getHoejde() {
		double hoejde = Double.NaN;
		hoejde = this.diameter.getDiameter();
		return hoejde;
	}

	@Override
	public void setHoejde(double hoejde) {
		this.hoejde.setHoejde(hoejde);
		notifyObservers(this.hoejde.getAfhaengigheder());
	}

	@Override
	public double getGodstykkelse() {
		double godstykkelse = Double.NaN;
		godstykkelse = this.godstykkelse.getGodstykkelse();
		return godstykkelse;
	}

	@Override
	public void setGodstykkelse(double godstykkelse) {
		this.godstykkelse.setGodstykkelse(godstykkelse);
		notifyObservers(this.godstykkelse.getAfhaengigheder());
	}

	@Override
	public ProfilType getProfilType() {
		return this.form.getProfilType();
	}

	@Override
	public void setProfilType(ProfilType profilType) {
		this.form.setProfilType(profilType);
		notifyObservers(form.getAfhaengigheder());
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
	public double getIndtastAreal() {
		return this.indtastAreal.getIndtastAreal();
	}

	@Override
	public void setIndtastAreal(double indtastAreal) {
		this.indtastAreal.setIndtastAreal(indtastAreal);
		notifyObservers(this.indtastAreal.getAfhaengigheder());
	}

	@Override
	public double getFlydespaending() {
		return this.flydeSpaending.getFlydespaending();
	}

	@Override
	public void setFlydespaending(double flydespaending) {
		this.flydeSpaending.setFlydespaending(flydespaending);
	}
	
	@Override
	public void setSikkerhedsfaktor(double sikkerhedsfaktor) {
		this.sikkerhedsfaktor.setSikkerhedsfaktor(sikkerhedsfaktor);
		notifyObservers(this.sikkerhedsfaktor.getAfhaengigheder());
	}

	@Override
	public double getSikkerhedsfaktor() {
		return sikkerhedsfaktor.getSikkerhedsfaktor();
	}

	@Override
	public boolean erSikkerhedsfaktorForLavt() {
		return sikkerhedsfaktor.erSikkerhedsfaktorForLavt();
	}
	
}
