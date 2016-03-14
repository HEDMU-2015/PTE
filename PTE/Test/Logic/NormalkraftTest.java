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
	VaegtImpl vaegt;
	VinkelImpl vinkel;
	Tyngdekraft tyngdekraft;
	DimensionerendeKraftImpl dimensionerendeKraft;
	NormalkraftImpl normalkraft;

	@Before
	public void setUp() {
		vaegt = new VaegtImpl();
		tyngdekraft = new TyngdekraftImpl();		
		vinkel = new VinkelImpl();
		dimensionerendeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		normalkraft = new NormalkraftImpl(dimensionerendeKraft, vinkel);

	}

	@Test
	public void getNormalkraftNulstilTest() throws UdefineretProfilException {
		vinkel.setProfil(Profil.VANDRET);
		normalkraft.setNormalkraft(5);
		normalkraft.nulstil();
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
	}

	

	@Test
	public void getNormalkraftNulVinkelTest() throws UdefineretProfilException {
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(0);
		assertEquals(100, normalkraft.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalNulTest() throws UdefineretProfilException {
		vaegt.setVaegt(0);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		try {
			normalkraft.getNormalkraft();
			fail("Exception bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	@Test
	public void getNormalkraftNormalTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		assertEquals(0.872, normalkraft.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalkraftAfrundTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(3.03456);

		assertEquals(9.986, normalkraft.getNormalkraft(), 0.001);
	}

	

	@Test
	public void getNormalkraftVinkelDoubleNanTest() throws UdefineretProfilException {
		vaegt.setVaegt(5);
		vaegt.setVaegt(Double.NaN);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
	}
}
