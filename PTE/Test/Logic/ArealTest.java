package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.ArealException;
import Exceptions.LaengdeException;

public class ArealTest {

	Areal areal;

	@Before
	public void setUp() throws Exception {
		areal = new ArealImpl();

	}

	@Test
	public void getArealNulstilTest() {
		areal.setAreal(5);
		areal.nulstil();
		assertEquals(Double.NaN, areal.getAreal(), 0.001);
	}

	@Test
	public void getArealNegativTest() {
		areal.setAreal(-60);
		try {
			areal.getAreal();
			fail("Negativ areal");
		} catch (ArealException e) {
			// Success

		}
	}

	@Test
	public void getArealNormalTest() {
		areal.setAreal(205);
		assertEquals(205, areal.getAreal(), 0.001);
	}

	@Test
	public void setLaengdeKommaTest() {
		areal.setAreal(55.3779);
		assertEquals(55.378, areal.getAreal(), 0.001);

	}

	@Test
	public void getArealNulTest() {
		areal.setAreal(0);
		try {
			areal.getAreal();
			fail("Nul Areal");
		} catch (ArealException e) {
			// Success

		}

	}

}
