package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.VinkelException;

public class VinkelTest {

	Vinkel vinkel;

	@Before
	public void setUp() throws Exception {

		vinkel = new VinkelImpl();
	}

	@Test
	public void setVinkelNulstilTest() {
		vinkel.setVinkel(5);
		vinkel.nulstil();
		assertEquals(Double.NaN, vinkel.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelNegativTest() {
		try {
			vinkel.setVinkel(-60);
			fail("Vinklen er negativ");
		} catch (VinkelException e) {
			// Success

		}
	}

	@Test
	public void setVinkelNormalTest() {
		vinkel.setVinkel(60);
		assertEquals(60, vinkel.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelRetTest() {
		vinkel.setVinkel(90);
		assertEquals(90, vinkel.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelOverRetTest() {
		try {
			vinkel.setVinkel(100);
			fail("Vinklen overstiger 90 grader");
		} catch (VinkelException e) {
			// Success

		}
	}

	@Test
	public void setVinkelKommaTest() {
		vinkel.setVinkel(55.37831);
		assertEquals(55.378, vinkel.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelNulTest() {
		vinkel.setVinkel(0);
		assertEquals(0, vinkel.getVinkel(), 0.001);

	}
}
