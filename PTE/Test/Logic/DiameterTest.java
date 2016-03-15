package Logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DiameterException;

public class DiameterTest {

	DiameterImpl diameter;

	@Before
	public void setUp() throws Exception {

		diameter = new DiameterImpl();
	}

	@Test
	public void diameterNulstilTest() {

		diameter.setDiameter(5);
		diameter.nulstil();
		assertEquals(Double.NaN, diameter.getDiameter(), 0.001);

	}

	@Test
	public void diameterNulTest() {

		diameter.setDiameter(0);

		assertEquals(0, diameter.getDiameter(), 0.001);

	}

	@Test
	public void diameterNormalTest() {

		diameter.setDiameter(4);

		assertEquals(4, diameter.getDiameter(), 0.001);

	}

	@Test
	public void diameterNegativTest() {

		try {
			diameter.setDiameter(-4);
		} catch (DiameterException e) {
			// Success
		}

	}

	@Test
	public void diameterKommaTest() {

		diameter.setDiameter(5.5555);

		assertEquals(5.556, diameter.getDiameter(), 0.001);

	}
}
