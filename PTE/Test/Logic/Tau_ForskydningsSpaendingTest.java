package Logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Tau_ForskydningsSpaendingTest {

	VaegtImpl v;
	TyngdekraftImpl t;
	DimensionerendeKraftImpl dk;
	VinkelImpl vi;
	ForskydningskraftImpl ft;
	ArealImpl a;
	Tau_ForskydningsSpaendingImpl tau;
	
	@Before
	public void setUp() throws Exception {
	v = new VaegtImpl();
	t = new TyngdekraftImpl();
	dk = new DimensionerendeKraftImpl(v, t);
	vi = new VinkelImpl();
	ft = new ForskydningskraftImpl(vi, dk);
	a = new ArealImpl();	
//	tau = new Tau_ForskydningsSpaendingImpl(a, ft);
	}

	@Test
	public void getTauNulstilTest() {
		tau.setTau_ForskydningsSpaending(5);
		tau.nulstil();
		assertEquals(Double.NaN, tau.getTau_ForskydningsSpaending(), 0.001);
	}
	
	@Test
	public void GetTauNormalTest() {
		a.setAreal(40);
		ft.setForskydningskraft(40);
		
		assertEquals(1, tau.getTau_ForskydningsSpaending(), 0.001);
	}
	

}
