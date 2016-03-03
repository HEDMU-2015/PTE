package Logic;

public class VaegtImpl implements Vaegt {

	private double vaegt;
	
	@Override
	public double getVaegt() {
		return vaegt;
	}

	@Override
	public void setVaegt(double vaegt) {
		this.vaegt = vaegt;
	} 

	@Override
	public void nulstil() {
		setVaegt(0);
	}

}
