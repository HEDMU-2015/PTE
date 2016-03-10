package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.ForskydningskraftException;
import Exceptions.NormalkraftException;
import Exceptions.TyngdekraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VaegtException;
import Exceptions.VinkelException;

public class NormalkraftTest{
	VaegtImpl v;
	VinkelImpl vi;
	Tyngdekraft t;
	DimensionerendeKraftImpl dk;
	NormalkraftImpl fn;

	@Before
	public void setUp() {
		v = new VaegtImpl();
		t = new TyngdekraftImpl();		
		vi = new VinkelImpl();
		dk = new DimensionerendeKraftImpl(v, t);
		fn = new NormalkraftImpl(dk, vi);

	}

	@Test
	public void getNormalkraftNulstilTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		fn.setNormalkraft(5);
		fn.nulstil();
		assertEquals(Double.NaN, fn.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalkraftNegativVinkelTest() throws UdefineretProfilException {
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(-5);
		dk.setDimensionerendeKraft(10);
		try {
			fn.getNormalkraft();
			fail("Exception bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VinkelException e) {
			// success
		}
	}

	@Test
	public void getNormalkraftNulVinkelTest() throws UdefineretProfilException {
		v.setVaegt(10);
		t.setTyngdekraft(10);
		vi.setProfil(Profil.LODRET);
		vi.setVinkel(0);
		assertEquals(100, fn.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalNulTest() throws UdefineretProfilException {
		v.setVaegt(0);
		t.setTyngdekraft(10);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		try {
			fn.getNormalkraft();
			fail("Exception bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	@Test
	public void getNormalkraftNormalTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		assertEquals(0.872, fn.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalkraftAfrundTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.LODRET);
		vi.setVinkel(3.03456);

		assertEquals(9.986, fn.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalkraftVinkelOverHalvFemsTest() throws UdefineretProfilException {
		v.setVaegt(2);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(91);

		try {
			fn.getNormalkraft();
			fail("Exception bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VinkelException e) {

		}
	}

	@Test
	public void getNormalkraftVinkelDoubleNanTest() throws UdefineretProfilException {
		v.setVaegt(Double.NaN);
		t.setTyngdekraft(5);
		vi.setProfil(Profil.VANDRET);
		vi.setVinkel(5);

		try {
			fn.getNormalkraft();
			fail("NormalkraftException bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VinkelException e) {

		} catch (DimensionerendeKraftException e) {

		} catch (TyngdekraftException e) {

		} catch (VaegtException e) {

		}
	}
}
