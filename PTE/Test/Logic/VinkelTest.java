package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.VinkelException;

public class VinkelTest {

	Vinkel v;

	@Before
	public void setUp() throws Exception {

		v = new VinkelImpl();
	}

	@Test
	public void setVinkelNulstilTest() {
		v.setVinkel(5);
		v.nulstil();
		assertEquals(Double.NaN, v.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelNegativTest() {
		v.setVinkel(-60);
		try {
			v.getVinkel();
			fail("Vinklen er negativ");
		} catch (VinkelException e) {
			// Success

		}
	}

	@Test
	public void setVinkelNormalTest() {
		v.setVinkel(60);
		assertEquals(60, v.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelRetTest() {
		v.setVinkel(90);
		assertEquals(90, v.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelOverRetTest() {
		v.setVinkel(100);
		try {
			v.getVinkel();
			fail("Vinklen overstiger 90 grader");
		} catch (VinkelException e) {
			// Success

		}
	}

	@Test
	public void setVinkelKommaTest() {
		v.setVinkel(55.37831);
		assertEquals(55.378, v.getVinkel(), 0.001);

	}

	@Test
	public void setVinkelNulTest() {
		v.setVinkel(0);
		assertEquals(0, v.getVinkel(), 0.001);

	}
}
