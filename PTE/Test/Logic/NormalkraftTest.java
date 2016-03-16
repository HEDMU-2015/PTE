package Logic;

import static org.junit.Assert.*;

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

public class NormalkraftTest{
	VaegtImpl vaegt;
	VinkelImpl vinkel;
	Tyngdekraft tyngdekraft;
	DimensionerendeKraftImpl dimensionerendeKraft;
	NormalkraftImpl normalkraft;

	@Before
	public void setUp() {
		vaegt = new VaegtImpl();
		tyngdekraft = new TyngdekraftImpl();		
		vinkel = new VinkelImpl();
		dimensionerendeKraft = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		normalkraft = new NormalkraftImpl(dimensionerendeKraft, vinkel);

	}

	@Test
	public void getNormalkraftNulstilTest() throws UdefineretProfilException {
		vinkel.setProfil(Profil.VANDRET);
		normalkraft.setNormalkraft(5);
		normalkraft.nulstil();
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
	}

	

	@Test
	public void getNormalkraftNulVinkelTest() throws UdefineretProfilException {
		vaegt.setVaegt(10);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(0);
		assertEquals(100, normalkraft.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalNulTest() throws UdefineretProfilException {
		vaegt.setVaegt(0);
		tyngdekraft.setTyngdekraft(10);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		try {
			normalkraft.getNormalkraft();
			fail("Exception bliver ikke kastet.");

		} catch (NormalkraftException e) {
			// success
		} catch (VaegtException e) {

		}
	}

	
	@Test
	public void getNormalkraftNormalTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);

		assertEquals(0.872, normalkraft.getNormalkraft(), 0.001);
	}

	@Test
	public void getNormalkraftAfrundTest() throws UdefineretProfilException {
		vaegt.setVaegt(2);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.LODRET);
		vinkel.setVinkel(3.03456);

		assertEquals(9.986, normalkraft.getNormalkraft(), 0.001);
	}

	

	@Test
	public void getNormalkraftVinkelDoubleNanTest() throws UdefineretProfilException {
		vaegt.setVaegt(5);
		vaegt.setVaegt(Double.NaN);
		tyngdekraft.setTyngdekraft(5);
		vinkel.setProfil(Profil.VANDRET);
		vinkel.setVinkel(5);
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
	}
	
	/**
	 * Test af integrationen mellem NormalkraftImpl 
	 * og alle underliggende klasser
	 * Denne test er lavet af Anders på klassen
	 */
	@Test
	public void testNulstilNormalkraftHierarki() {
		// 3. Setup
		vaegt.setVaegt(50);
		tyngdekraft.setTyngdekraft(20);
		vinkel.setVinkel(75);
		vinkel.setProfil(Profil.LODRET);
		assertEquals(50 * 20, dimensionerendeKraft.getDimensionerendeKraft(), 0.001);
		assertEquals(Math.cos(Math.toRadians(75)) * 50 * 20, normalkraft.getNormalkraft(), 0.001);
		
		// 2. Execution
		normalkraft.nulstil();
		
		// 1. Verification
		assertEquals(Double.NaN, vaegt.getVaegt(), 0.001);
		assertEquals(9.816, tyngdekraft.getTyngdekraft(), 0.001);
		assertEquals(Double.NaN, dimensionerendeKraft.getDimensionerendeKraft(), 0.001);
		assertEquals(Double.NaN, vinkel.getVinkel(), 0.001);
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
	}
	
	/**
	 * Unit test af NormalkraftImpl
	 */
	@Test
	public void testNulstilBoernTilNormalkraft() {
		// 3. Setup
		NulstilbarDimensionerendeKraftMock fdimMock = new NulstilbarDimensionerendeKraftMock();
		NulstilbarVinkelMock vinkelMock = new NulstilbarVinkelMock();
		normalkraft = new NormalkraftImpl(fdimMock, vinkelMock);
		
		// 2. Execution
		normalkraft.nulstil();
		
		// 1. Verification
		assertEquals(1, fdimMock.nulstilTaeller);
		assertEquals(1, vinkelMock.nulstilTaeller);
		assertEquals(Double.NaN, normalkraft.getNormalkraft(), 0.001);
		
		// 4. Teardown
		// Nothing to tear down...
	}

	private class NulstilbarVinkelMock implements Vinkel {

		public int nulstilTaeller = 0;

		@Override
		public List<Tilstand> getAfhaengigheder() {
			fail("getAfhaengigheder kaldt i Vinkel");
			return null;
		}

		@Override
		public void tilfoejAfhaengigEntitet(PTEEntity entity) {
		}

		@Override
		public void setVinkel(double vinkel) {
			fail("setVinkel kaldt i Vinkel");
		}

		@Override
		public double getVinkel() {
			return Double.NaN;
		}

		@Override
		public void setProfil(Profil profil) {
			fail("setProfil kaldt i Vinkel");
		}

		@Override
		public Profil getProfil() {
			return Profil.VANDRET;
		}

		@Override
		public void setLaengdeRetning(LaengdeRetning laengdeRetning) {
			fail("setLaengdeRetning kaldt i Vinkel");
		}

		@Override
		public LaengdeRetning getLaengdeRetning() {
			fail("getLaengdeRetning kaldt i Vinkel");
			return null;
		}

		@Override
		public void nulstil() {
			nulstilTaeller++;
		}

	}

	private class NulstilbarDimensionerendeKraftMock implements DimensionerendeKraft {

		public int nulstilTaeller = 0;

		@Override
		public List<Tilstand> getAfhaengigheder() {
			fail("getAfhaengigheder kaldt i DimensionerendeKraft");
			return null;
		}

		@Override
		public void tilfoejAfhaengigEntitet(PTEEntity entity) {
		}

		@Override
		public double getDimensionerendeKraft() {
			return Double.NaN;
		}

		@Override
		public void setDimensionerendeKraft(double dimensionerendeKraft) {
			fail("setDimensionerendeKraft kaldt i DimensionerendeKraft");
		}

		@Override
		public double dimensionerendeKraftTilVaegt() {
			fail("dimensionerendeKraftTilVaegt kaldt i DimensionerendeKraft");
			return 0;
		}

		@Override
		public void nulstil() {
			nulstilTaeller++;
		}

	}

}
