package Logic;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.UdefineretProfilException;

public class NormalkraftImplTest{
	
	@Test
	public void testGetNormalkraft() {
		NormalkraftImpl nk = new NormalkraftImpl(new VinkelImpl(),
		new DimensionerendeKraftImpl(new VaegtImpl(), new TyngdekraftImpl()));
		assertEquals(3788.842, nk.getNormalkraft(50,3926.4), 0.001);
		assertEquals(3926.398, nk.getNormalkraft(0.001,3926.4), 0.001);
		assertEquals(-1755.805, nk.getNormalkraft(89.999,3926.4), 0.001);
	}
}
