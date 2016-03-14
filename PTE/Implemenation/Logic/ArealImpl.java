package Logic;

public class ArealImpl extends PTEEntityImpl implements Areal {

	private double areal = Double.NaN;
	private Form form;
	private Bredde bredde;
	private Diameter diameter;
	private Godstykkelse godstykkelse;
	private Hoejde hoejde;

	public ArealImpl(Bredde bredde, Diameter diameter, Godstykkelse godstykkelse, Hoejde hoejde){
		if (bredde == null || diameter == null || godstykkelse == null || hoejde == null) {
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
	}

	@Override
	public double getAreal() {
		return getAreal(bredde.getBredde(), diameter.getDiameter(), godstykkelse.getGodstykkelse(), hoejde.getHoejde());
	}

	double getAreal(double bredde, double diameter, double godstykkelse, double hoejde) {
		if (!Double.isNaN(areal)) {
			return areal;
		}

		if (this.form.getProfilType() == ProfilType.CIRKEL) {
			return Math.PI * this.diameter.getDiameter();
		} else if (this.form.getProfilType() == ProfilType.HULT_ROER) {
			return this.diameter.getDiameter() *  this.godstykkelse.getGodstykkelse();
		} else { // this.form.getProfilType() == ProfilType.KVADRAT
			return  this.hoejde.getHoejde() *  this.bredde.getBredde();
		}
	}

	@Override
	public void setAreal(double areal) {
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
