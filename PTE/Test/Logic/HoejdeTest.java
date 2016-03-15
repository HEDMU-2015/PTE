package Logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exceptions.HoejdeException;

public class HoejdeTest {
	HoejdeImpl hoejde;

	@Before
	public void setUp() throws Exception {

		hoejde = new HoejdeImpl();
	}

	@Test
	public void hoejdeNulstilTest() {

		hoejde.setHoejde(5);
		hoejde.nulstil();
		assertEquals(Double.NaN, hoejde.getHoejde(), 0.001);

	}

	@Test
	public void hoejdeNulTest() {

		hoejde.setHoejde(0);

		assertEquals(0, hoejde.getHoejde(), 0.001);

	}

	@Test
	public void hoejdeNormalTest() {

		hoejde.setHoejde(4);

		assertEquals(4, hoejde.getHoejde(), 0.001);

	}
	
	
	@Test
	public void hoejdeNegativTest() {

		hoejde.setHoejde(-4);

		try {
			hoejde.getHoejde();
		} catch (HoejdeException e) {
			// Success
		}

	}
	@Test
	public void hoejdeKommaTest() {

		hoejde.setHoejde(5.5555);

		assertEquals(5.556, hoejde.getHoejde(), 0.001);

	}
}
