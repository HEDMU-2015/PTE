package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DimensionerendeKraftTest {
	
	VaegtImpl v;
	TyngdekraftImpl t;
	DimensionerendeKraftImpl dk;
	
	@Before
	public void setUp() throws Exception {
	
	v = new VaegtImpl();
	t = new TyngdekraftImpl();
	dk = new DimensionerendeKraftImpl(v , t);
	
	}

	@Test
	public void getDimensionerendeKraftNulstilTest() {
		dk.setDimensionerendeKraft(5);
		dk.nulstil();
		assertEquals(Double.NaN, dk.getDimensionerendeKraft(), 0.001);
	}
	
	
	

}
