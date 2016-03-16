package Logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Exceptions.DimensionerendeKraftException;
import Exceptions.ForskydningskraftException;
import Exceptions.NormalkraftException;
import Exceptions.TyngdekraftException;
import Exceptions.UdefineretProfilException;
import Exceptions.VaegtException;
import Exceptions.VinkelException;

public class ForskydningskraftTest {

	ForskydningskraftImpl forskydningskraft;
	Profil profil;

	@Test
	public void getForskydningskraftNulstilTest() throws UdefineretProfilException {
		profil = Profil.VANDRET;
		double vinkel = 10;
		double dimensionerendeKraft = 10;

		assertEquals(9.848, forskydningskraft.getForskydningskraft(profil, vinkel, dimensionerendeKraft), 0.001);
	}

	@Test
	public void getForskydningskraftNulVinkelTest() throws UdefineretProfilException {
		
		profil = Profil.VANDRET;
		double vinkel = 0;
		double dimensionerendeKraft = 10;

		assertEquals(10, forskydningskraft.getForskydningskraft(profil, vinkel, dimensionerendeKraft), 0.001);
	}

	@Test
	public void getForskydningskraftNulTest() throws UdefineretProfilException {

		try {
			forskydningskraft.setForskydningskraft(0);
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		}
	}

	@Test
	public void getForskydningskraftNormalTest() throws UdefineretProfilException {

		assertEquals(9.962, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftAfrundTest() throws UdefineretProfilException {

		assertEquals(0.529, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftVinkelDoubleNanTest() throws UdefineretProfilException {

		assertEquals(Double.NaN, forskydningskraft.getForskydningskraft(), 0.001);

	}

	@Before
	public void setUp() {

		forskydningskraft = new ForskydningskraftImpl(new Vinkel() {

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
		}) {

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
		};

	}
}
