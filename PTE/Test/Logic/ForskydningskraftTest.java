package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

	public class ForskydningskraftTest {

		@Test
		public void testGetForskydningskraft() {
			ForskydningskraftImpl fk = new ForskydningskraftImpl(new VinkelImpl(), 
			new DimensionerendeKraftImpl(new VaegtImpl(), new TyngdekraftImpl()));
			assertEquals(3788.842, fk.getForskydningskraft(50,3926.4), 0.001);
			assertEquals(3926.398, fk.getForskydningskraft(0.001,3926.4), 0.001);
			assertEquals(-1755.805, fk.getForskydningskraft(89.999,3926.4), 0.001);
		}
	
}
