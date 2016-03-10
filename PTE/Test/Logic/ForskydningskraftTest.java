package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.ForskydningskraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VinkelException;

public class ForskydningskraftTest {

	VaegtImpl v;
	VinkelImpl vi;
	Tyngdekraft t;
	DimensionerendeKraftImpl dk;
	ForskydningskraftImpl ft;

	@Before
	public void setUp() {
		v = new VaegtImpl();
		vi = new VinkelImpl();
		t = new TyngdekraftImpl();
		dk = new DimensionerendeKraftImpl(v, t);
		ft = new ForskydningskraftImpl(vi, dk);

	}

	@Test
	public void getForskydningskraftNulstilTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		ft.setForskydningskraft(5);
		ft.nulstil();
		assertEquals(Double.NaN, ft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftNegativVinkelTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(-5);
		dk.setDimensionerendeKraft(10);
		try {
			ft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VinkelException e) {
			// success
		}
	}
	@Test
	public void getForskydningskraftNulVinkelTest() throws UdefineretProfilException {
		vi.setProfil(Profil.LODRET);
		vi.setVinkel(1);
		dk.setDimensionerendeKraft(10);
		assertEquals(0.175, ft.getForskydningskraft(), 0.001);
		}
	@Test
	public void getForskydningskraftNulForskydningskraftTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);
		dk.setDimensionerendeKraft(0);
		try {
			ft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} 
	}
}
