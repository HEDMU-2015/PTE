package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Exceptions.BoejningsMomentException;

public class BoejningsMomentTest {

	BoejningsMomentImpl boejningsMoment;
	LaengdeRetning laengdeRetning;

	@Test
	public void getBoejningsMomentNormalFdimTest() {

		laengdeRetning = LaengdeRetning.VINKELRET_TIL_FDIM;
		double laengde = 50;
		double dimensionerendeKraft = 2;
		double forskydningkraft = 3;

		assertEquals(100,
				boejningsMoment.getBoejningsMoment(laengdeRetning, laengde, dimensionerendeKraft, forskydningkraft),
				0.001);
	}

	@Test
	public void getBoejningsMomentNormalFtTest() {

		laengdeRetning = LaengdeRetning.VINKELRET_TIL_FT;
		double laengde = 50;
		double dimensionerendeKraft = 2;
		double forskydningkraft = 3;

		assertEquals(150,
				boejningsMoment.getBoejningsMoment(laengdeRetning, laengde, dimensionerendeKraft, forskydningkraft),
				0.001);
	}

	@Test
	public void getBoejningsMomentKommatalFdimTest() {

		laengdeRetning = LaengdeRetning.VINKELRET_TIL_FDIM;
		double laengde = 50.555555;
		double dimensionerendeKraft = 10;
		double forskydningkraft = 100;

		assertEquals(505.556,
				boejningsMoment.getBoejningsMoment(laengdeRetning, laengde, dimensionerendeKraft, forskydningkraft),
				0.001);
	}

	@Test
	public void getBoejningsMomentNegativFdimTest() {

		laengdeRetning = LaengdeRetning.VINKELRET_TIL_FDIM;
		boejningsMoment.setBoejningsMoment(-5);

		try {
			boejningsMoment.getBoejningsMoment();
			fail("bøjningsmoment fejl blev ikke grappet");
		} catch (BoejningsMomentException e) {
			// succes
		}
	}

	@Test
	public void getBoejningsMomentNulFdimTest() {

		laengdeRetning = LaengdeRetning.VINKELRET_TIL_FDIM;
		boejningsMoment.setBoejningsMoment(0);

		try {
			boejningsMoment.getBoejningsMoment();
			fail("bøjningsmoment fejl blev ikke grappet");
		} catch (BoejningsMomentException e) {
			// succes
		}
	}

	@Before
	public void setUp() throws Exception {

		boejningsMoment = new BoejningsMomentImpl(new Vinkel() {

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
			public void setVinkel(double vinkel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setProfil(Profil profil) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setLaengdeRetning(LaengdeRetning laengdeRetning) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getVinkel() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Profil getProfil() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public LaengdeRetning getLaengdeRetning() {
				// TODO Auto-generated method stub
				return null;
			}
		}, new Laengde() {

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
			public void setLaengde(double Laengde) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getLaengde() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public double Laengde() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new DimensionerendeKraft() {

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
			public void setDimensionerendeKraft(double dimensionerendeKraft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getDimensionerendeKraft() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public double dimensionerendeKraftTilVaegt() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Forskydningskraft() {

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
			public void setForskydningskraft(double forskydningskraft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getForskydningskraft() {
				// TODO Auto-generated method stub
				return 0;
			}
		});

	}

}
