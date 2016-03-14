package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.TyngdekraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VaegtException;

public class DimensionerendeKraftTest {

	VinkelImpl vinkel;
	VaegtImpl vaegt;
	TyngdekraftImpl tyngdekraft;
	DimensionerendeKraftImpl dimensioneredeKraft;

	@Before
	public void setUp() throws Exception {

		vaegt = new VaegtImpl();
		tyngdekraft = new TyngdekraftImpl();
		vinkel = new VinkelImpl();
		dimensioneredeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);

	}

	@Test
	public void getDimensionerendeKraftNulstilTest(){
		dimensioneredeKraft.setDimensionerendeKraft(5);
		dimensioneredeKraft.nulstil();
		assertEquals(Double.NaN, dimensioneredeKraft.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftNegativVaegtTest() {
		vaegt.setVaegt(-5);
		tyngdekraft.setTyngdekraft(9.816);

		try {
			dimensioneredeKraft.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		} catch (VaegtException e){
			//success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulVaegtTest() {
		vaegt.setVaegt(0);
		tyngdekraft.setTyngdekraft(9.816);
		
		try {
			dimensioneredeKraft.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}catch (VaegtException e){
			//success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulTyngdekraftTest() {
		vaegt.setVaegt(5);
		tyngdekraft.setTyngdekraft(0);

		try {
			dimensioneredeKraft.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}catch (TyngdekraftException e){
			//success
		}
	}

	@Test
	public void GetDimensionerendeKraftEnsVaegtOgTyngdeKraftTest() {
		vaegt.setVaegt(5);
		tyngdekraft.setTyngdekraft(5);
		assertEquals(25, dimensioneredeKraft.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftAfrundTest() {
		vaegt.setVaegt(3.03456);
		tyngdekraft.setTyngdekraft(10);
		assertEquals(30.346, dimensioneredeKraft.getDimensionerendeKraft(), 0.001);
	}

	@Test
	public void GetDimensionerendeKraftTyngdekraftNaNTest() {
		vaegt.setVaegt(5);
		tyngdekraft.setTyngdekraft(5);
		tyngdekraft.setTyngdekraft(Double.NaN);
		assertEquals(Double.NaN, dimensioneredeKraft.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftVaegtNaNTest() {
		vaegt.setVaegt(5);
		vaegt.setVaegt(Double.NaN);
		tyngdekraft.setTyngdekraft(5);
		assertEquals(Double.NaN, dimensioneredeKraft.getDimensionerendeKraft(), 0.001);

	}
}
