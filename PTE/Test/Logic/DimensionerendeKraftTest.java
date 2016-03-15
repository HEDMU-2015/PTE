package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;

public class DimensionerendeKraftTest {

	DimensionerendeKraftImpl dimensionerendeKraft;

	@Test
	public void getDimensionerendeKraftNulstilTest() {
		
		 
		dimensionerendeKraft.setDimensionerendeKraft(5);		
		dimensionerendeKraft.nulstil();
		assertEquals(Double.NaN, dimensionerendeKraft.getDimensionerendeKraft(), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftNegativVaegtTest() {

		double vaegt = -10;
		double tyngdekraft = 10;

		try {
			dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft);
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftNegativTyngdekraftTest() {

		double vaegt = 10;
		double tyngdekraft = -10;

		try {
			dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft);
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulVaegtTest() {
		double vaegt = 0;
		double tyngdekraft = 10;

		try {
			dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft);
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftNulTyngdekraftTest() {
		double vaegt = 10;
		double tyngdekraft = 0;

		try {
			dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft);
			fail("Exception bliver ikke kastet.");

		} catch (DimensionerendeKraftException e) {
			// success
		}
	}

	@Test
	public void GetDimensionerendeKraftEnsVaegtOgTyngdeKraftTest() {
		double vaegt = 10;
		double tyngdekraft = 10;
		assertEquals(100, dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftAfrundTest() {
		double vaegt = 10.55555;
		double tyngdekraft = 10;
		assertEquals(105.556, dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft), 0.001);
	}

	@Test
	public void GetDimensionerendeKraftTyngdekraftNaNTest() {
		double vaegt = 10;
		double tyngdekraft = Double.NaN;
		assertEquals(Double.NaN, dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft), 0.001);

	}

	@Test
	public void GetDimensionerendeKraftVaegtNaNTest() {
		double vaegt = Double.NaN;
		double tyngdekraft = 10;
		assertEquals(Double.NaN, dimensionerendeKraft.getDimensionerendeKraft(vaegt, tyngdekraft), 0.001);

	}

	@Before
	public void setUp() throws Exception {
		
		dimensionerendeKraft = new DimensionerendeKraftImpl(new Vaegt() {

			@Override
			public void tilfoejAfhaengigEntitet(PTEEntity entity) {
				// TODO Auto-generated method stub

			}

			@Override
			public List<Tilstand> getAfhaengigheder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setVaegt(double vaegt) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getVaegt() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Tyngdekraft() {

			@Override
			public void tilfoejAfhaengigEntitet(PTEEntity entity) {
				// TODO Auto-generated method stub

			}

			@Override
			public List<Tilstand> getAfhaengigheder() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setTyngdekraft(double tyngdekraft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getTyngdekraft() {
				// TODO Auto-generated method stub
				return 0;
			}
		});

	}
}
