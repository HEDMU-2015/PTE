package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.BreddeException;

public class BreddeTest {

	BreddeImpl bredde;

	@Before
	public void setUp() throws Exception {

		bredde = new BreddeImpl();
	}

	@Test
	public void breddeNulstilTest() {

		bredde.setBredde(5);
		bredde.nulstil();
		assertEquals(Double.NaN, bredde.getBredde(), 0.001);

	}

	@Test
	public void breddeNulTest() {

		bredde.setBredde(0);

		assertEquals(0, bredde.getBredde(), 0.001);

	}

	@Test
	public void breddeNormalTest() {

		bredde.setBredde(4);

		assertEquals(4, bredde.getBredde(), 0.001);

	}

	@Test
	public void breddeNegativTest() {

		try {
			bredde.setBredde(-4);

		} catch (BreddeException e) {
			// Success
		}

	}

	@Test
	public void breddeKommaTest() {

		bredde.setBredde(5.5555);

		assertEquals(5.556, bredde.getBredde(), 0.001);

	}
}
