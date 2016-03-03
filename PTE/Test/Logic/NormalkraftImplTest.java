package Logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class NormalkraftImplTest{
	
	@Test
	public void testGetNormalkraft() {
		NormalkraftImpl nk = new NormalkraftImpl(new DimensionerendeKraftImpl(new VaegtImpl(), new TyngdekraftImpl()), 
					     new VinkelImpl());
		System.out.println(Math.sin((Math.toRadians(50))));
		assertEquals(3007.797, nk.getNormalkraft(50,3926.4), 0.001);
	}
}
