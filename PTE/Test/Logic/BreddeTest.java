package Logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	

}
