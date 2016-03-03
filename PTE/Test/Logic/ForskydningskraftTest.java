package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

	public class ForskydningskraftTest {

		@Test
		public void testGetNormalkraft() {
			ForskydningskraftImpl nk = new ForskydningskraftImpl(new VinkelImpl(), 
						     new DimensionerendeKraftImpl(new VaegtImpl(), new TyngdekraftImpl()));
			assertEquals(3788.842, nk.getForskydningskraft(50,3926.4), 0.001);
			assertEquals(3926.398, nk.getForskydningskraft(0.001,3926.4), 0.001);
			assertEquals(-1755.805, nk.getForskydningskraft(89.999,3926.4), 0.001);
		}
	
}
