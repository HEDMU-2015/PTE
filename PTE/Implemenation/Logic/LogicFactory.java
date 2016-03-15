package Logic;

public interface LogicFactory {
	public Vinkel createVinkel();
	public Vaegt createVaegt();
	public Tyngdekraft craeteTyngdeKraft(); 
	public DimensionerendeKraft craeteDimensionerendeKraft(Vaegt vaegt, Tyngdekraft tyngdeKraft);
	public Forskydningskraft createForskydningskraft(Vinkel vinkel, DimensionerendeKraft dimensionerendeKraft);
	public Normalkraft createNormalKraft(DimensionerendeKraft dimensionerendeKraft, Vinkel vinkel);
	public Forskydningsspaending createTau_ForskydningsSpaending(Areal areal, Forskydningskraft forskydningsKraft);
	public Laengde createLaengde();
	public BoejningsMoment createBoejningsMoment(Vinkel v, Laengde l, DimensionerendeKraft dimensionerendeKraft, Forskydningskraft forskydningskraft);
	public Normalspaending createSigmaN(Areal areal, Normalkraft normalKraft);
	public Forskydningspunkt createForskydningspunkt();
	public Inertimoment createInertimoment();
	public Boejningsspaending createSigmaB(BoejningsMoment boejningsMoment, Forskydningspunkt forskydningspunkt, Inertimoment inertimoment);
	public Referencespaending createSigmaRef(Boejningsspaending sigmaB, Normalspaending sigmaN, Forskydningsspaending tau_ForskydningsSpaendingen);
	public Bredde createBredde();
	public Diameter createDiameter();
	public Godstykkelse createGodstykkelse();
	public Hoejde createHoejde();
	public Areal createAreal(Bredde bredde, Diameter diameter, Godstykkelse godstykkelse, Hoejde hoejde, Form form);
	public Form createForm();
	public IndtastAreal createIndtastAreal();
	public Flydespaending createFlydespaendning();
	public Sikkerhedsfaktor createSikkerhedsfaktor(Flydespaending flydespaending, Referencespaending sigmaRef);
}
