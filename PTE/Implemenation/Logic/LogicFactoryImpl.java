package Logic;

public class LogicFactoryImpl implements LogicFactory {

	@Override
	public Vinkel createVinkel() {
		return new VinkelImpl(); 
	}

	@Override
	public Vaegt createVaegt() {
		return new VaegtImpl();
	}

	@Override
	public Tyngdekraft craeteTyngdeKraft() {
		return new TyngdekraftImpl();
	}

	@Override
	public DimensionerendeKraft craeteDimensionerendeKraft(Vaegt vaegt, Tyngdekraft tyngdeKraft) {
		return new DimensionerendeKraftImpl(vaegt, tyngdeKraft);
	}

	@Override
	public Forskydningskraft createForskydningskraft(Vinkel vinkel, DimensionerendeKraft dimensionerendeKraft) {
		return new ForskydningskraftImpl(vinkel, dimensionerendeKraft);
	}

	@Override
	public Normalkraft createNormalKraft(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel) {
		return new NormalkraftImpl(dimensionerendeKraft, vinkel);
	}
	
	@Override
	public Tau_ForskydningsSpaending createTau_ForskydningsSpaending(Areal areal, Forskydningskraft forskydningskraft) {
		return new Tau_ForskydningsSpaendingImpl(areal, forskydningskraft);
	}
	
	@Override
	public Laengde createLaengde() {
		return new LaengdeImpl();
	}
	
	@Override
	public BoejningsMoment createBoejningsMoment(Vinkel v, Laengde l, DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft) {
		return new BoejningsMomentImpl(v, l, dimensionerendeKraft, forskydningskraft);
	}

	@Override
	public SigmaN createSigmaN(Areal areal, Normalkraft normalKraft) {
		return new SigmaNImpl(areal, normalKraft);
	}

	@Override
	public Forskydningspunkt createForskydningspunkt() {
		return new ForskydningspunktImpl();
	}

	@Override
	public Inertimoment createInertimoment() {
		return new InertimomentImpl();
	}

	@Override
	public SigmaB createSigmaB(BoejningsMoment boejningsMoment, Forskydningspunkt forskydningspunkt,
			Inertimoment inertimoment) {
		return new SigmaBImpl(boejningsMoment, forskydningspunkt, inertimoment);
	}

	@Override
	public SigmaRef createSigmaRef(SigmaB sigmaB, SigmaN sigmaN,
			Tau_ForskydningsSpaending tau_ForskydningsSpaendingen) {
		return new SigmaRefImpl(sigmaB, sigmaN, tau_ForskydningsSpaendingen);
	}

	@Override
	public Bredde createBredde() {
		return new BreddeImpl();
	}

	@Override
	public Diameter createDiameter() {
		return new DiameterImpl();
	}

	@Override
	public Godstykkelse createGodstykkelse() {
		return new GodstykkelseImpl();
	}

	@Override
	public Hoejde createHoejde() {
		return new HoejdeImpl();
	}

	@Override
	public Areal createAreal(Bredde bredde, Diameter diameter, Godstykkelse godstykkelse, Hoejde hoejde, Form form) {
		return new ArealImpl(bredde, diameter, godstykkelse, hoejde, form);
	}

	@Override
	public Form createForm() {
		return new FormImpl();
	}

	@Override
	public IndtastAreal createIndtastAreal() {
		return new IndtastArealImpl();
	}

	@Override
	public Flydespaending createFlydespaendning() {
		return new FlydespaendingImpl();
	}
}
