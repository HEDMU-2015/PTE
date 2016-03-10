package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.ForskydningskraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VinkelException;

public class NormalkraftTest{
	VaegtImpl v;
	VinkelImpl vi;
	Tyngdekraft t;
	DimensionerendeKraftImpl dk;
	NormalkraftImpl fn;

	@Before
	public void setUp() {
		vi = new VinkelImpl();
		dk = new DimensionerendeKraftImpl(v, t);
		fn = new NormalkraftImpl(dk, vi);

	}

	@Test
	public void getForskydningskraftNulstilTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		fn.setNormalkraft(5);
		fn.nulstil();
		assertEquals(Double.NaN, fn.getNormalkraft(), 0.001);
	}

	@Test
	public void getForskydningskraftNegativVinkelTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(-5);
		dk.setDimensionerendeKraft(10);
		try {
			fn.getNormalkraft();
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

	@Test
	public void getForskydningskraftNormalTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);
		dk.setDimensionerendeKraft(5);
		assertEquals(4.981, ft.getForskydningskraft(), 0.001);
	}
}
