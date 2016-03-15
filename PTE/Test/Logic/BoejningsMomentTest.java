package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Exceptions.LaengdeException;
import Exceptions.TyngdekraftException;
import Exceptions.VaegtException;

public class BoejningsMomentTest {

	

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void getBoejningsMomentNormalTest() {
		vaegt.setVaegt(5);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(10);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		laengde.setLaengde(200);

		assertEquals(9848.078, boejningsMoment.getBoejningsMoment(), 0.001);
	}

	@Test
	public void getBoejningsMomentNulTest() {
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		boejningsMoment.setBoejningsMoment(5);
		boejningsMoment.nulstil();

		assertEquals(Double.NaN, boejningsMoment.getBoejningsMoment(), 0.001);
	}

	@Test
	public void GetBoejningsmomentNegativVaegtTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(-10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setVinkel(10);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		
		try {
			boejningsMoment.getBoejningsMoment();
			fail("Exception blev ikke kastet");
		} catch (VaegtException e) {
			//success
		}
	}

	@Test
	public void GetBoejningsmomentNegativTyngdekraftTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(-10);
		vinkel.setVinkel(10);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);

		try {
			boejningsMoment.getBoejningsMoment();
			fail("Exception blev ikke kastet");
		} catch (TyngdekraftException e) {
			// success
		}

	}
	@Test
	public void GetBoejningsmomentNegativLaengdeTest() {
		vinkel.setProfil(Profil.VANDRET);
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setVinkel(10);
		vinkel.setLaengdeRetning(LaengdeRetning.VINKELRET_TIL_FT);
		laengde.setLaengde(-50);
		
		try {
			boejningsMoment.getBoejningsMoment();
			fail("Exception blev ikke kastet");
		} catch (LaengdeException e) {
			// success
		}

	}

}
