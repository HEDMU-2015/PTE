package Logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exceptions.GodstykkelseException;

public class GodstykkelseTest {

	GodstykkelseImpl godstykkelse;

	@Before
	public void setUp() throws Exception {

		godstykkelse = new GodstykkelseImpl();
	}

	@Test
	public void godstykkelseNulstilTest() {

		godstykkelse.setGodstykkelse(5);
		godstykkelse.nulstil();
		assertEquals(Double.NaN, godstykkelse.getGodstykkelse(), 0.001);

	}

	@Test
	public void godstykkelseNulTest() {

		godstykkelse.setGodstykkelse(0);

		assertEquals(0, godstykkelse.getGodstykkelse(), 0.001);

	}

	@Test
	public void godstykkelseNormalTest() {

		godstykkelse.setGodstykkelse(4);

		assertEquals(4, godstykkelse.getGodstykkelse(), 0.001);

	}

	@Test
	public void godstykkelseNegativTest() {

		godstykkelse.setGodstykkelse(-4);

		try {
			godstykkelse.getGodstykkelse();
		} catch (GodstykkelseException e) {
			// Success
		}

	}

	@Test
	public void godstykkelseKommaTest() {

		godstykkelse.setGodstykkelse(5.5555);

		assertEquals(5.556, godstykkelse.getGodstykkelse(), 0.001);

	}

}
