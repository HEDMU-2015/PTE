package Logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SikkerhedsfaktorTest {
	
	SikkerhedsfaktorImpl sikkerhedsfaktor;

	@Test
	public void getSikkerhedsfaktornormaltest() {
	double sigmaRef = 5;
	double flydespaending = 10;
	
	assertEquals(2, sikkerhedsfaktor.getSikkerhedsfaktor(flydespaending , sigmaRef ), 0.001);
		
	}
	
	@Test
	public void getSikkerhedsfaktornegativReferancespaendingTest() {
	double sigmaRef = -5;
	double flydespaending = 10;
	
	assertEquals(-2, sikkerhedsfaktor.getSikkerhedsfaktor(flydespaending , sigmaRef ), 0.001);
		
	}
	
	@Test
	public void getSikkerhedsnegativflydespaendingtest() {
	double sigmaRef = 5;
	double flydespaending = -10;
	
	assertEquals(-2, sikkerhedsfaktor.getSikkerhedsfaktor(flydespaending , sigmaRef ), 0.001);
		
	}
	
	@Test
	public void getSikkerhedsfaktorReferencespaendingDoubleNaNTest() {
	double sigmaRef = Double.NaN;
	double flydespaending = 10;
	
	assertEquals(Double.NaN, sikkerhedsfaktor.getSikkerhedsfaktor(flydespaending , sigmaRef ), 0.001);
		
	}
	
	@Test
	public void getSikkerhedsfaktorflydespaendingDoubleNaNTest() {
	double sigmaRef = 10;
	double flydespaending = Double.NaN;
	
	assertEquals(Double.NaN, sikkerhedsfaktor.getSikkerhedsfaktor(flydespaending , sigmaRef ), 0.001);
		
	}
	
	@Test
	public void getSikkerhedsfaktorSikkerhedsfaktorNulstilTest() {
	sikkerhedsfaktor.setSikkerhedsfaktor(5);
	sikkerhedsfaktor.nulstil();
	assertEquals(Double.NaN, sikkerhedsfaktor.getSikkerhedsfaktor(), 0.001);
		
	}
	
	
	
	@Before
	public void setUp() throws Exception {
	
	
	sikkerhedsfaktor = new SikkerhedsfaktorImpl(new Flydespaending() {
		
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
		public void setFlydespaending(double flydspaending) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nulstil() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public double getFlydespaending() {
			// TODO Auto-generated method stub
			return 0;
		}
	},new SigmaRef() {
		
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
		public void setReference(double refe) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nulstil() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public double getSigmaRef() {
			// TODO Auto-generated method stub
			return 0;
		}
	});
	}

}
