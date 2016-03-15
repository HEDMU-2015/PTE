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

	

	@Test
	public void getForskydningskraftNulstilTest() throws UdefineretProfilException {
		vinkel.setProfil(Profil.VANDRET);
		forskydningskraft.setForskydningskraft(5);
		forskydningskraft.nulstil();
		assertEquals(Double.NaN, forskydningskraft.getForskydningskraft(), 0.001);
	}

	
	

	@Test
	public void getForskydningskraftNulVinkelTest() throws UdefineretProfilException {
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(0);
		assertEquals(0, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftNulTest() throws UdefineretProfilException {
		vaegt.setVaegt(0);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		try {
			forskydningskraft.getForskydningskraft();
			fail("Exception bliver ikke kastet.");

		} catch (ForskydningskraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	@Test
	public void getForskydningskraftNormalTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		assertEquals(9.962, forskydningskraft.getForskydningskraft(), 0.001);
	}

	@Test
	public void getForskydningskraftAfrundTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(3.03456);

		assertEquals(0.529, forskydningskraft.getForskydningskraft(), 0.001);
	}

	
	

	@Test
	public void getForskydningskraftVinkelDoubleNanTest() throws UdefineretProfilException {
		vaegt.setVaegt(Double.NaN);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

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
