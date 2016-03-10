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
		v.setVaegt(10);
		t.setTyngdekraft(10);
		vi.setProfil(Profil.LODRET);
		vi.setVinkel(0);
		assertEquals(0, ft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftNulTest() throws UdefineretProfilException {
		v.setVaegt(0);
		t.setTyngdekraft(10);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		try {
			ft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	@Test
	public void getForskydningskraftNormalTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		assertEquals(9.962, ft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftAfrundTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.LODRET);
		vi.setVinkel(3.03456);

		assertEquals(0.529, ft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftVinkelOverHalvFemsTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(91);

		try {
			ft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VinkelException e) {

		}
	}

	@Test
	public void getForskydningskraftVinkelDoubleNanTest() throws UdefineretProfilException {
		v.setVaegt(Double.NaN);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		try {
			ft.getForskydningskraft();
			fail("ForskydningskraftException bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VinkelException e) {

		} catch (DimensionerendeKraftException e) {

		} catch (NormalkraftException e) {

		} catch (TyngdekraftException e) {

		} catch (VaegtException e) {

		}
	}
}
