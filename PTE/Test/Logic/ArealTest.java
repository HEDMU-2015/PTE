package Logic;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Exceptions.ArealException;
import Exceptions.LaengdeException;

public class ArealTest {

	ArealImpl areal;
	FormImpl form;

	@Test
	public void getArealNulstilTest() {
		
		form.getProfilType()
		double bredde = 5;
		double hoejde = 5;
		double godstykkelse = 2;
		double diameter = 2;

		assertEquals(10, areal.getAreal(bredde, diameter, godstykkelse, hoejde), 0.001);
	}

	@Test
	public void getArealNegativTest() {
		areal.setAreal(-60);
		try {
			areal.getAreal();
			fail("Negativ areal");
		} catch (ArealException e) {
			// Success

		}
	}

	@Test
	public void getArealNormalTest() {
		areal.setAreal(205);
		assertEquals(205, areal.getAreal(), 0.001);
	}

	@Test
	public void setLaengdeKommaTest() {
		areal.setAreal(55.3779);
		assertEquals(55.378, areal.getAreal(), 0.001);

	}

	@Test
	public void getArealNulTest() {
		areal.setAreal(0);
		try {
			areal.getAreal();
			fail("Nul Areal");
		} catch (ArealException e) {
			// Success

		}

	}

	@Before
	public void setUp() throws Exception {
		form = new FormImpl();
		areal = new ArealImpl(new Bredde() {

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
			public void setBredde(double bredde) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getBredde() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Diameter() {

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
			public void setDiameter(double diameter) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getDiameter() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Godstykkelse() {

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
			public void setGodstykkelse(double godstykkelse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getGodstykkelse() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Hoejde() {

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
			public void setHoejde(double godstykkelse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public double getHoejde() {
				// TODO Auto-generated method stub
				return 0;
			}
		}, new Form() {

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
			public void setProfilType(ProfilType profilType) {
				// TODO Auto-generated method stub

			}

			@Override
			public void nulstil() {
				// TODO Auto-generated method stub

			}

			@Override
			public ProfilType getProfilType() {
				// TODO Auto-generated method stub
				return null;
			}
		});

	}
}
