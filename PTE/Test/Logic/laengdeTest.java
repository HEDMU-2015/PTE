package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.LaengdeException;
import Exceptions.VinkelException;

public class laengdeTest {

	Laengde laengde;

	@Before
	public void setUp() throws Exception {

		laengde = new LaengdeImpl();

	}

	@Test
	public void laengdetest() {
		laengde.setLaengde(5);
		laengde.nulstil();
		assertEquals(Double.NaN, laengde.getLaengde(), 0.001);
	}

	@Test
	public void setLaengdeNegativTest() {
		
		try {
			laengde.setLaengde(-60);
			fail("Negativ Længde");
		} catch (LaengdeException e) {
			// Success

		}
	}

	@Test
	public void setLaengde2NormalTest() {
		laengde.setLaengde(60);
		assertEquals(60, laengde.getLaengde(), 0.001);

	}

	@Test
	public void setLaengdeNormalTest() {
		laengde.setLaengde(205);
		assertEquals(205, laengde.getLaengde(), 0.001);
	}

	@Test
	public void setLaengdeKommaTest() {
		laengde.setLaengde(55.3779);
		assertEquals(55.378, laengde.getLaengde(), 0.001);

	}

}
