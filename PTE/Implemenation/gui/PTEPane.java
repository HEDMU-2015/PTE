package gui;

import Logic.Observer;
import Logic.PTEController;

public abstract class PTEPane implements Observer{
	
	protected PTEController pteController;
	
	public void setPTEController(PTEController pteController){
		this.pteController = pteController;
		this.pteController.tilmeldObserver(this);
	}
	
}
