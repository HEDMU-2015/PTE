package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import Exceptions.ArealException;
import Exceptions.TyngdekraftException;
import Exceptions.VaegtException;

public class Tau_ForskydningsSpaendingTest {

	VaegtImpl vaegt;
	TyngdekraftImpl tyngdekraft;
	DimensionerendeKraftImpl dimensioneredeKraft;
	VinkelImpl vinkel;
	ForskydningskraftImpl forskydningskraft;
	ArealImpl areal;
	Tau_ForskydningsSpaendingImpl forskydningsSpaending;
	
	@Before
	public void setUp() throws Exception {
	vaegt = new VaegtImpl();
	tyngdekraft = new TyngdekraftImpl();
	vinkel = new VinkelImpl();
	dimensioneredeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
	forskydningskraft = new ForskydningskraftImpl(vinkel, dimensioneredeKraft);
	areal = new ArealImpl();	
	forskydningsSpaending = new Tau_ForskydningsSpaendingImpl(areal, forskydningskraft);
	}

	@Test
	public void getTauNulstilTest() {
		vinkel.setProfil(Profil.VANDRET);
		forskydningsSpaending.setTau_ForskydningsSpaending(5);
		forskydningsSpaending.nulstil();
		assertEquals(Double.NaN, forskydningsSpaending.getTau_ForskydningsSpaending(), 0.001);
	}
	
	@Test
	public void GetTauNormalTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setVinkel(10);
		areal.setAreal(10);
		assertEquals(9.848, forskydningsSpaending.getTau_ForskydningsSpaending(), 0.001);
	}
	@Test
	public void GetTauNegativVaegtTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(-10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setVinkel(10);
		areal.setAreal(40);
		
		try {
			forskydningsSpaending.getTau_ForskydningsSpaending();
			fail("Exception blev ikke kastet");
		} catch (VaegtException e) {
			//success
		}
		
	}
	@Test
	public void GetTauNegativTyngdekraftTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(-10);
		vinkel.setVinkel(10);
		areal.setAreal(40);
		
		try {
			forskydningsSpaending.getTau_ForskydningsSpaending();
			fail("Exception blev ikke kastet");
		} catch (TyngdekraftException e) {
			//success
		}
		
	}
	@Test
	public void GetTauNegativArealTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setVinkel(10);
		areal.setAreal(-40);
		
		try {
			forskydningsSpaending.getTau_ForskydningsSpaending();
			fail("Exception blev ikke kastet");
		} catch (ArealException e) {
			//success
		}
		
	}

}
