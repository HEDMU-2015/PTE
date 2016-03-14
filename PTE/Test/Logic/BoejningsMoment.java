package Logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoejningsMoment {
	
	VaegtImpl vaegt;
	TyngdekraftImpl tyngdekraft;
	VinkelImpl vinkel;
	DimensionerendeKraftImpl dimensionderendeKraft;
	ForskydningskraftImpl forskydningskraft;
	LaengdeImpl laengde;
	BoejningsMomentImpl boejningsMoment;
		
	@Before
	public void setUp() throws Exception {
	vaegt = new VaegtImpl();
	tyngdekraft = new TyngdekraftImpl();
	vinkel = new VinkelImpl();
	dimensionderendeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
	laengde = new LaengdeImpl();
	forskydningskraft = new ForskydningskraftImpl(vinkel, dimensionderendeKraft);
	boejningsMoment = new BoejningsMomentImpl(vinkel, laengde, dimensionderendeKraft, forskydningskraft);
	}

	@Test
	public void getBoejningsMomentNormalTest() {
		vaegt.setVaegt(5);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(10);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		laengde.setLaengde(200);
		forskydningskraft.setForskydningskraft(10);		
		
		assertEquals(50, boejningsMoment.getBoejningsMoment(), 0.001);
	}
	
	@Test
	public void getBoejningsMomentNulTest() {
		boejningsMoment.setBoejningsMoment(5);
		boejningsMoment.nulstil();
		
		assertEquals(Double.NaN, boejningsMoment.getBoejningsMoment(), 0.001);
	}

}
