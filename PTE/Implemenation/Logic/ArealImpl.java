package Logic;

import Exceptions.ArealException;

public class ArealImpl extends PTEEntityImpl implements Areal {

	private double areal = Double.NaN;
	private Form form;
	private Bredde bredde;
	private Diameter diameter;
	private Godstykkelse godstykkelse;
	private Hoejde hoejde;

	public ArealImpl(Bredde bredde, Diameter diameter, Godstykkelse godstykkelse, Hoejde hoejde, Form form) {
		if (bredde == null || diameter == null || godstykkelse == null || hoejde == null || form == null) {
			throw new IllegalArgumentException();
		}

		this.bredde = bredde;
		this.bredde.tilfoejAfhaengigEntitet(this);
		this.diameter = diameter;
		this.diameter.tilfoejAfhaengigEntitet(this);
		this.godstykkelse = godstykkelse;
		this.godstykkelse.tilfoejAfhaengigEntitet(this);
		this.hoejde = hoejde;
		this.hoejde.tilfoejAfhaengigEntitet(this);
		this.form = form;
		this.form.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public double getAreal() {
		return getAreal(bredde.getBredde(), diameter.getDiameter(), godstykkelse.getGodstykkelse(), hoejde.getHoejde());
	}

	double getAreal(double bredde, double diameter, double godstykkelse, double hoejde) {
		if (!Double.isNaN(areal)) {
			return areal;
		}

		if (bredde == Double.NaN || diameter == Double.NaN || godstykkelse == Double.NaN || hoejde == Double.NaN) {
			return Double.NaN;
		}

		if (this.form.getProfilType() == ProfilType.CIRKEL) {
			return Math.PI * this.diameter.getDiameter();
		} else if (this.form.getProfilType() == ProfilType.HULT_ROER) {
			return this.diameter.getDiameter() * this.godstykkelse.getGodstykkelse();
		} else if (this.form.getProfilType() == ProfilType.KVADRET_ROER) {
			return this.hoejde.getHoejde() * this.bredde.getBredde()
					- (this.hoejde.getHoejde() * (this.godstykkelse.getGodstykkelse() * 2))
							* (this.bredde.getBredde() * (2 * this.godstykkelse.getGodstykkelse()));
		} else {
			if (this.form.getProfilType() == ProfilType.KVADRAT) {
				return this.hoejde.getHoejde() * this.bredde.getBredde();
			}
		}
		return areal;
	}

	@Override
	public void setAreal(double areal) {
		if (areal <= 0){
			throw new ArealException("Areal er 0 eller mindre");
		}
		this.areal = areal;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.AREAL;
	}

	@Override
	public void nulstil() {
		setAreal(Double.NaN);
		nulstilBoern();
	}

	private void nulstilBoern() {
		bredde.nulstil();
		diameter.nulstil();
		godstykkelse.nulstil();
		hoejde.nulstil();
	}

}
