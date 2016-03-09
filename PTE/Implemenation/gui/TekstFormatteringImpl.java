package gui;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TekstFormatteringImpl implements TekstFormattering {

	private DecimalFormat format = new DecimalFormat("#.###");

	@Override
	public void formaterTekstfeltInput(TextField input) {
		Pattern ingenTegn = Pattern.compile("[-]?[0-9]*(\\,[0-9]*)?");
		input.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!ingenTegn.matcher(newValue).matches())
					input.setText(oldValue);
			}
		});

	}

	@Override
	public Double formaterStringTilDouble(String tekstinput) {

		if (tekstinput.isEmpty()) {
			return Double.NaN; 
		} else {
			return Double.parseDouble(tekstinput.replace(',', '.'));
		}
	}

	@Override
	public String formaterDoubleTilString(Double resultat) {

		if (Double.isNaN(resultat)) {
			return "";
		}

		return format.format(resultat).replace('.', ',');
	}

}
