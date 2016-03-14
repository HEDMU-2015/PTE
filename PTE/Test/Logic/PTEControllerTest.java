package Logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class PTEControllerTest {

	@Test
	public void test() {
		PTEController pteController = new PTEControllerImpl(new LogicFactoryImpl());
		pteController.setVinkel(50);
		assertEquals(50, pteController.getVinkel(), 0.001);
	}

}
