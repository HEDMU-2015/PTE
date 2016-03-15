package Logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BoejningsspaenidingTest {

	BoejningsspaendingImpl boejningsspaending;
	
	
	
	

	@Test
	public void  boejningsspaendingNormalTest() {
	
		
		
	assertEquals(50, boejningsspaending.getSigmaB(), 0.001);
	}

	
	
	
	
	
	@Before
	public void setUp() throws Exception {
	boejningsspaending = new BoejningsspaendingImpl(new BoejningsMoment() {
		
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
		public void setBoejningsMoment(double BoejningsMoment) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nulstil() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public double getBoejningsMoment() {
			// TODO Auto-generated method stub
			return 0;
		}
	},new Forskydningspunkt() {
		
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
		public void setForskydningspunkt(double forskydningspunkt) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nulstil() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public double getForskydningspunkt() {
			// TODO Auto-generated method stub
			return 0;
		}
	}, new Inertimoment() {
		
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
		public void setInertimoment(double inertimoment) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void nulstil() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public double getInertimoment() {
			// TODO Auto-generated method stub
			return 0;
		}
	});
	}
}
