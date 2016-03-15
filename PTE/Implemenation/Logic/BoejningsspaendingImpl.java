package Logic;

import Exceptions.SigmaBException;
import Exceptions.UdefineretProfilException;

public class BoejningsspaendingImpl extends PTEEntityImpl implements Boejningsspaending {

	private double sigmaB = Double.NaN;
	private Forskydningspunkt forskydningspunkt;
	private Inertimoment inertimoment;
	private BoejningsMoment boejningsMoment;

	public BoejningsspaendingImpl(BoejningsMoment boejningsMoment, Forskydningspunkt forskydningspunkt,
			Inertimoment inertimoment) {
		this.forskydningspunkt = forskydningspunkt;
		this.inertimoment = inertimoment;
		this.boejningsMoment = boejningsMoment;
		this.forskydningspunkt.tilfoejAfhaengigEntitet(this);
		this.inertimoment.tilfoejAfhaengigEntitet(this);
		this.boejningsMoment.tilfoejAfhaengigEntitet(this);
	}

	@Override
	public void setSigmaB(double sigmaB) {
		this.sigmaB = sigmaB;
	}

	@Override
	public double getSigmaB() {
		try {
			sigmaB = (boejningsMoment.getBoejningsMoment() * forskydningspunkt.getForskydningspunkt())
					/ inertimoment.getInertimoment();
			if (sigmaB <= 0.0) {
				throw new SigmaBException("SigmaB exception : SigmaB <= 0.0");
			}
		} catch (UdefineretProfilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sigmaB;
	}

	@Override
	public void nulstil() {
		setSigmaB(Double.NaN);
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.SIGMAB;
	}

}
