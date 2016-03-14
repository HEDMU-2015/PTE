package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.TyngdekraftException;
import Exceptions.VaegtException;

public class TyngdekraftTest {

	TyngdekraftImpl tyngdekraft;
	
	@Before
	public void setUp() throws Exception {
		tyngdekraft = new TyngdekraftImpl();
	}

	@Test
	public void setTyngdekraftNulstill() {

		tyngdekraft.setTyngdekraft(9.816);
		
		assertEquals(9.816, tyngdekraft.getTyngdekraft(), 0.001);
	}

	@Test
	public void setTyngdekraftNegativ() {
		tyngdekraft.setTyngdekraft(-5);

		try {
			tyngdekraft.getTyngdekraft();
			fail("Exception bliver ikke kastet.");
			
		} catch (TyngdekraftException e) {
			// success
		}

	}

	@Test
	public void setTyngdekraftNul() {
		tyngdekraft.setTyngdekraft(0);

		try {
			tyngdekraft.getTyngdekraft(); 
			fail("Exception bliver ikke kastet.");
			
		} catch (TyngdekraftException e) {
			// success
		}
	}

	@Test
	public void setTyngdekraftAfrunding() {
		
		tyngdekraft.setTyngdekraft(3.03456);
		assertEquals(3.035, tyngdekraft.getTyngdekraft(), 0.001);
	}

	@Test
	public void setTyngdekraftDecimaltal() {
		tyngdekraft.setTyngdekraft(0.001);
		assertEquals(0.001, tyngdekraft.getTyngdekraft(), 0.001);
	}

}
