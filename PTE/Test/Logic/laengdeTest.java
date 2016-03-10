package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.LaengdeException;
import Exceptions.VinkelException;

public class laengdeTest {

	Laengde l;
	
	@Before
	public void setUp() throws Exception {
	
		l = new LaengdeImpl();
		
	}

	@Test
	public void laengdetest() {
		l.setLaengde(5);
		l.nulstil();
		assertEquals(Double.NaN, l.getLaengde(), 0.001);
	}
	@Test
	public void setLaengdeNegativTest() {
		l.setLaengde(-60);
		try {
			l.getLaengde();
			fail("Negativ Længde");
		} catch (LaengdeException e) {
			// Success

		}
	}

	@Test
	public void setLaengde2NormalTest() {
		l.setLaengde(60);
		assertEquals(60, l.getLaengde(), 0.001);

	}

	@Test
	public void setLaengdeNormalTest() {
		l.setLaengde(205);
		assertEquals(205, l.getLaengde(), 0.001);
	}
	@Test
	public void setLaengdeKommaTest() {
		l.setLaengde(55.3779);
		assertEquals(55.378, l.getLaengde(), 0.001);

	}

	@Test
	public void setLaengdeNulTest() {
		l.setLaengde(0);
		try {
			l.getLaengde();
			fail("Nul Længde");
		} catch (LaengdeException e) {
			// Success

		}

	}

}
