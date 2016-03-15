package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.VaegtException;

public class VaegtTest {

	VaegtImpl vaegt;

	@Before
	public void setUp() throws Exception {
		vaegt = new VaegtImpl();

	}

	@Test
	public void setVaegtNulstil() {

		vaegt.setVaegt(5);
		vaegt.nulstil();
		assertEquals(Double.NaN, vaegt.getVaegt(), 0.001);
	}

	@Test
	public void setVaegtNegativ() {

		try {
			vaegt.setVaegt(-5);
			fail("Exception bliver ikke kastet.");

		} catch (VaegtException e) {
			// success
		}

	}

	@Test
	public void setVaegtNul() {

		try {
			vaegt.setVaegt(0);
			fail("Exception bliver ikke kastet.");

		} catch (VaegtException e) {
			// success
		}
	}

	@Test
	public void setVaegtAfrunding() {

		vaegt.setVaegt(3.03456);
		assertEquals(3.035, vaegt.getVaegt(), 0.001);
	}

	@Test
	public void setVaegtDecimaltal() {
		vaegt.setVaegt(0.001);
		assertEquals(0.001, vaegt.getVaegt(), 0.001);
	}
}
