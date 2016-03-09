package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.TyngdekraftException;
import Exceptions.VaegtException;

public class TyngdekraftTest {

	TyngdekraftImpl t;
	
	@Before
	public void setUp() throws Exception {
		t = new TyngdekraftImpl();
	}

	@Test
	public void setTyngdekraftNulstill() {

		t.setTyngdekraft(9.816);
		
		assertEquals(9.816, t.getTyngdekraft(), 0.001);
	}

	@Test
	public void setTyngdekraftNegativ() {
		t.setTyngdekraft(-5);

		try {
			t.getTyngdekraft();
			fail("Exception bliver ikke kastet.");
			
		} catch (TyngdekraftException e) {
			// success
		}

	}

	@Test
	public void setTyngdekraftNul() {
		t.setTyngdekraft(0);

		try {
			t.getTyngdekraft(); 
			fail("Exception bliver ikke kastet.");
			
		} catch (TyngdekraftException e) {
			// success
		}
	}

	@Test
	public void setTyngdekraftAfrunding() {
		
		t.setTyngdekraft(3.03456);
		assertEquals(3.035, t.getTyngdekraft(), 0.001);
	}

	@Test
	public void setTyngdekraftDecimaltal() {
		t.setTyngdekraft(0.001);
		assertEquals(0.001, t.getTyngdekraft(), 0.001);
	}

}
