package Logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class PTEControllerTest {

	@Test
	public void test() {
		PTEController pte = new PTEControllerImpl(new LogicFactoryImpl());
		pte.setVinkel(50);
		assertEquals(50, pte.getVinkel(), 0.001);
	}

}
