package Logic;

import Exceptions.UdefineretProfilException;

public class NormalspaendingImpl extends PTEEntityImpl implements Normalspaending {

	private IndtastAreal areal;
	private Normalkraft normalkraft;
	private double sigmaN = Double.NaN;

	public NormalspaendingImpl(IndtastAreal areal, Normalkraft normalkraft) {

		this.areal = areal;
		this.normalkraft = normalkraft;
		this.areal.tilfoejAfhaengigEntitet(this);
		this.normalkraft.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setSigmaN(double sigmaN) {
		this.sigmaN = sigmaN;
		nulstilBoern();
	}

	@Override
	public double getSigmaN() {
		try {
			sigmaN = normalkraft.getNormalkraft() / areal.getIndtastAreal();
		} catch (UdefineretProfilException e) {

		}
		return sigmaN;
	}

	@Override
	public void nulstil() {
		setSigmaN(Double.NaN);
		nulstilBoern();
	}
	
	public void nulstilBoern() {
		areal.nulstil();
		normalkraft.nulstil();
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIGMAN;
	}

}
