package Logic;

import static org.junit.Assert.*;

import org.junit.Test;

import Logic.ForskydningskraftTest.TyngdekraftStub;
import Logic.ForskydningskraftTest.VaegtStubHalvKg;
import Logic.ForskydningskraftTest.VaegtStubTiKg;

public class ForskydningskraftTest {

	

	

	public class TyngdekraftStub implements Tyngdekraft {

		@Override
		public double getTyngdekraft() {
			
			return 9.816;
		}

		@Override
		public void setTyngdekraft(double tyngdekraft) {
			fail();

		}

		@Override
		public void nulstil() {
		fail();

		}

	}

	@Test
	public void testDimensionerendeKraftTiKg() {
		DimensionerendeKraft dk = new DimensionerendeKraftStub();
		Vinkel v = new VinkelStub();
		Forskydningskraft dk = new ForskydningskraftImpl(vaegt, tyngdekraft);
		
		assertEquals(98.16,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class VaegtStubTiKg implements Vaegt {

		@Override
		public double getVaegt() {
		
			return 10;
		}

		@Override
		public void setVaegt(double vaegt) {
			fail();

		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	
	@Test
	public void testDimensionerendeKraftHalvKg() {
		Tyngdekraft tyngdekraft = new TyngdekraftStub();
		Vaegt vaegt = new VaegtStubHalvKg();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(4.908,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class VaegtStubHalvKg implements Vaegt {

		@Override
		public double getVaegt() {
			
			return 0.5;
		}

		@Override
		public void setVaegt(double vaegt) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	@Test
	public void testDimensionerendeKraftMegetStortTal() {
		Tyngdekraft tyngdekraft = new TyngdekraftStub();
		Vaegt vaegt = new VaegtStubMegetStortTal();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(981599990.184,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class VaegtStubMegetStortTal implements Vaegt {

		@Override
		public double getVaegt() {
			
			return 99999999;
		}

		@Override
		public void setVaegt(double vaegt) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	@Test
	public void testDimensionerendeKraftTaetPaeNul() {
		Tyngdekraft tyngdekraft = new TyngdekraftStub();
		Vaegt vaegt = new VaegtStubTaetPaeNul();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(0.000009816,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class VaegtStubTaetPaeNul implements Vaegt {

		@Override
		public double getVaegt() {
			
			return 0.000001;
		}

		@Override
		public void setVaegt(double vaegt) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	@Test
	public void testDimensionerendeKraftMangeDecimaler() {
		Tyngdekraft tyngdekraft = new TyngdekraftStub();
		Vaegt vaegt = new VaegtStubMangeDecimaler();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(100.4601185,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class VaegtStubMangeDecimaler implements Vaegt {

		@Override
		public double getVaegt() {
			
			return 10.2343234;

		}

		@Override
		public void setVaegt(double vaegt) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	@Test
	public void testDimensionerendeKraftHoejTyngdekraft() {
		Tyngdekraft tyngdekraft = new TyngdekraftStubHoejTyngdekraft();
		Vaegt vaegt = new VaegtStubTiKg();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(196.18,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class TyngdekraftStubHoejTyngdekraft implements Tyngdekraft {

		@Override
		public double getTyngdekraft() {
			
			return 19.618;

		}

		@Override
		public void setTyngdekraft(double tyngdekraft) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
	@Test
	public void testDimensionerendeKraftLavTyngdekraft() {
		Tyngdekraft tyngdekraft = new TyngdekraftStubLavTyngdekraft();
		Vaegt vaegt = new VaegtStubTiKg();
		DimensionerendeKraft dk = new DimensionerendeKraftImpl(vaegt, tyngdekraft);
		
		assertEquals(0.000001,dk.getDimensionerendeKraft(),0.001);
		
		
	}
	public class TyngdekraftStubLavTyngdekraft implements Tyngdekraft {

		@Override
		public double getTyngdekraft() {
			
			return 0.00001;

		}

		@Override
		public void setTyngdekraft(double tyngdekraft) {
			fail();
		}

		@Override
		public void nulstil() {
			fail();

		}

	}
}
