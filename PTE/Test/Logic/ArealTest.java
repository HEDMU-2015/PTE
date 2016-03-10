package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.ArealException;
import Exceptions.LaengdeException;

public class ArealTest {

	Areal a;

	@Before
	public void setUp() throws Exception {
		a = new ArealImpl();

	}

	@Test
	public void getArealNulstilTest() {
		a.setAreal(5);
		a.nulstil();
		assertEquals(Double.NaN, a.getAreal(), 0.001);
	}

	@Test
	public void getArealNegativTest() {
		a.setAreal(-60);
		try {
			a.getAreal();
			fail("Negativ areal");
		} catch (ArealException e) {
			// Success

		}
	}

	@Test
	public void getArealNormalTest() {
		a.setAreal(205);
		assertEquals(205, a.getAreal(), 0.001);
	}

	@Test
	public void setLaengdeKommaTest() {
		a.setAreal(55.3779);
		assertEquals(55.378, a.getAreal(), 0.001);

	}

	@Test
	public void getArealNulTest() {
		a.setAreal(0);
		try {
			a.getAreal();
			fail("Nul Areal");
		} catch (ArealException e) {
			// Success

		}

	}

}
