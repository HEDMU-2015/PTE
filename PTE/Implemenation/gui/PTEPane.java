package gui;

import Logic.PTEController;

public abstract class PTEPane {
	protected PTEController pteController;


	public void setPTEController(PTEController pteController){
		this.pteController = pteController;
	
	}
}
