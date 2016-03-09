package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VaegtException;

public class DimensionerendeKraftTest {

	VinkelImpl vi;
	VaegtImpl v;
	TyngdekraftImpl t;
	ForskydningskraftImpl ft;
	NormalkraftImpl fn;
	DimensionerendeKraftImpl dk;

	@Before
	public void setUp() throws Exception {

		v = new VaegtImpl();
		t = new TyngdekraftImpl();
		vi = new VinkelImpl();
		ft = new ForskydningskraftImpl(vi, dk);
		fn = new NormalkraftImpl(dk, vi);
		dk = new DimensionerendeKraftImpl(v, t);

	}

	@Test
	public void getDimensionerendeKraftNulstilTest(){
		dk.setDimensionerendeKraft(5);
		dk.nulstil();
		assertEquals(Double.NaN, dk.getDimensionerendeKraft(), 0.001);
//		assertEquals(Double.NaN, fn.getNormalkraft(), 0.001);
//		assertEquals(Double.NaN, ft.getForskydningskraft(), 0.001);
	}

	@Test
	public void GetDimensionerendeKraftNegativVaegtTest() {
		v.setVaegt(-5);
		t.setTyngdekraft(9.816);

		try {
			dk.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulVaegtTest() {
		v.setVaegt(0);
		t.setTyngdekraft(9.816);
		
		try {
			dk.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulTyngdekraftTest() {
		v.setVaegt(5);
		t.setTyngdekraft(0);

		try {
			dk.getDimensionerendeKraft();
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftEnsVaegtOgTyngdeKraftTest() {
		v.setVaegt(5);
		t.setTyngdekraft(5);
		System.out.println(dk.getDimensionerendeKraft());
		assertEquals(25, dk.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftAfrundTest() {
		v.setVaegt(3.03456);
		t.setTyngdekraft(10);
	
		System.out.println(dk.getDimensionerendeKraft());
		assertEquals(30.346, dk.getDimensionerendeKraft(), 0.001);
	}

	@Test
	public void GetDimensionerendeKraftTyngdekraftNaNTest() {
		v.setVaegt(5);
		t.setTyngdekraft(5);
		t.setTyngdekraft(Double.NaN);
		assertEquals(Double.NaN, dk.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftVaegtNaNTest() {
		v.setVaegt(5);
		v.setVaegt(Double.NaN);
		t.setTyngdekraft(5);
		assertEquals(Double.NaN, dk.getDimensionerendeKraft(), 0.001);

	}
}
