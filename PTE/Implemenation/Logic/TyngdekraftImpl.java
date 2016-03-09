package Logic;

import Exceptions.TyngdekraftException;
import Exceptions.VaegtException;

class TyngdekraftImpl extends PTEEntityImpl implements Tyngdekraft {

	private double tyngdekraft = 9.816;
	
	@Override
	public double getTyngdekraft() {
		if(tyngdekraft<=0){
			throw new TyngdekraftException();
		}
		return tyngdekraft;
	}

	@Override
	public void setTyngdekraft(double tyngdekraft) {
		this.tyngdekraft = tyngdekraft;
		
	}

	@Override
	public void nulstil() {}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.TYNGDEKRAFT;
	}

}
