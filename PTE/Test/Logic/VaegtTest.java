package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.VaegtException;

public class VaegtTest {

	VaegtImpl v;

	@Before
	public void setUp() throws Exception {
		v = new VaegtImpl();

	}

	@Test
	public void setVaegtNulstil() {

		v.setVaegt(5);
		v.nulstil();
		assertEquals(Double.NaN, v.getVaegt(), 0.001);
	}

	@Test
	public void setVaegtNegativ() {
		v.setVaegt(-5);

		try {
			v.getVaegt();
			fail("Exception bliver ikke kastet.");
			
		} catch (VaegtException e) {
			// success
		}

	}

	@Test
	public void setVaegtNul() {
		v.setVaegt(0);

		try {
			v.getVaegt(); 
			fail("Exception bliver ikke kastet.");
			
		} catch (VaegtException e) {
			// success
		}
	}

	@Test
	public void setVaegtAfrunding() {
		
		v.setVaegt(3.03456);
		assertEquals(3.035, v.getVaegt(), 0.001);
	}

	@Test
	public void setVaegtDecimaltal() {
		v.setVaegt(0.001);
		assertEquals(0.001, v.getVaegt(), 0.001);
	}
}
