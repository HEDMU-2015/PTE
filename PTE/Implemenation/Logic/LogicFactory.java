package Logic;

public interface LogicFactory {
	public Vinkel createVinkel();
	public Vaegt createVaegt();
	public Tyngdekraft craeteTyngdeKraft(); 
	public DimensionerendeKraft craeteDimensionerendeKraft(Vaegt vaegt, Tyngdekraft tyngdeKraft);
	public Forskydningskraft createForskydningskraft(Vinkel vinkel, DimensionerendeKraft dimensionerendeKraft);
	public Normalkraft createNormalKraft(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel);
	public Areal createAreal();
	public Tau_Forskydningsspaending createTau_Forskydningsspaending(Areal areal, Forskydningskraft forskydningsKraft);
}
