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

	
	//Juyoung : Areal + Tau_Forskydningsspaending
	@Override
	public Areal createAreal() {
		
		return new ArealImpl();
	}

	@Override
	public Tau_Forskydningsspaending createTau_Forskydningsspaending(Areal areal, Forskydningskraft forskydningskraft) {
		return new Tau_ForskydningsspaendingImpl(areal, forskydningskraft);
	}
	
	@Override
	public Laengde createLaengde() {
		return new LaengdeImpl();
	}
	
	@Override
	public BoejningsMoment createBoejningsMoment(Vinkel v, Laengde l) {
		return new BoejningsMomentImpl(v, l);
	}

}
