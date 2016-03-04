package gui;

import javafx.scene.control.TextField;

public interface TekstFormattering {

	void formaterTekstfeltInput(TextField input);
	
	Double formaterStringTilDouble(String tekstinput);
	
	String formaterDoubleTilString(Double resultat);
}
