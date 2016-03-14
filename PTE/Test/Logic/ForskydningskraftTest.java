package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.ForskydningskraftException;
import Exceptions.NormalkraftException;
import Exceptions.TyngdekraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VaegtException;
import Exceptions.VinkelException;

public class ForskydningskraftTest {

	VaegtImpl vaegt;
	VinkelImpl vinkel;
	Tyngdekraft tyngdekraft;
	DimensionerendeKraftImpl dimensioneredeKraft;
	ForskydningskraftImpl forskydningskraft;

	@Before
	public void setUp() {
		vaegt = new VaegtImpl();
		vinkel = new VinkelImpl();
		tyngdekraft = new TyngdekraftImpl();
		dimensioneredeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		forskydningskraft = new ForskydningskraftImpl(vinkel, dimensioneredeKraft);

	}

	@Test
	public void getForskydningskraftNulstilTest() throws UdefineretProfilException {
		vinkel.setProfil(Profil.VANDRET);
		forskydningskraft.setForskydningskraft(5);
		forskydningskraft.nulstil();
		assertEquals(Double.NaN, forskydningskraft.getForskydningskraft(), 0.001);
	}

	
	

	@Test
	public void getForskydningskraftNulVinkelTest() throws UdefineretProfilException {
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(0);
		assertEquals(0, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftNulTest() throws UdefineretProfilException {
		vaegt.setVaegt(0);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		try {
			forskydningskraft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	@Test
	public void getForskydningskraftNormalTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		assertEquals(9.962, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftAfrundTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(3.03456);

		assertEquals(0.529, forskydningskraft.getForskydningskraft(), 0.001);
	}

	
	

	@Test
	public void getForskydningskraftVinkelDoubleNanTest() throws UdefineretProfilException {
		vaegt.setVaegt(Double.NaN);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		assertEquals(Double.NaN, forskydningskraft.getForskydningskraft(), 0.001);
			
	}
}
