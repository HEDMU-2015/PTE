package Logic;

public interface LogicFactory {
	public Vinkel createVinkel();
	public Vaegt createVaegt();
	public Tyngdekraft craeteTyngdeKraft(); 
	public DimensionerendeKraft craeteDimensionerendeKraft(Vaegt vaegt, Tyngdekraft tyngdeKraft);
	public Forskydningskraft createForskydningskraft(Vinkel vinkel, DimensionerendeKraft dimensionerendeKraft);
	public Normalkraft createNormalKraft(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel);
	public Areal createAreal();
	public Tau_ForskydningsSpaending createTau_ForskydningsSpaending(Areal areal, Forskydningskraft forskydningsKraft);
	public Laengde createLaengde();
	public BoejningsMoment createBoejningsMoment(Vinkel v, Laengde l, DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft);
	public SigmaN createSigmaN(Areal areal, Normalkraft normalKraft);
	public Forskydningspunkt createForskydningspunkt();
	public Inertimoment createInertimoment();
	public SigmaB createSigmaB(BoejningsMoment boejningsMoment, Forskydningspunkt forskydningspunkt, Inertimoment inertimoment);
	public SigmaRef createSigmaRef(SigmaB sigmaB, SigmaN sigmaN, Tau_ForskydningsSpaending tau_ForskydningsSpaendingen);
}
