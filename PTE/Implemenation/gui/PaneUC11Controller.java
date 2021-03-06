package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import Logic.PTEController;
import Logic.Profil;
import Logic.Tilstand;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.FileUtils;
import utils.FileUtilsImpl;
import utils.SimplePdfWriter;
import utils.SimplePdfWriterImpl;

public class PaneUC11Controller extends PTEPane implements Initializable {

	private TekstFormattering tekstfeltFormat = new TekstFormatteringImpl();
	private Profil profil;
	private static final String ADVARSEL = "-fx-background-color: pink;";
	private static final String CSS = "@util/gui.css";

	@FXML
	private Button pdf;

	@FXML
	private TextField tekstFeltSigmaB, tekstFeltSigmaN, tekstFeltkg, tekstFeltdk, tekstFeltSigmaRef, tekstFeltnk,
			tekstFeltfs, tekstFeltfk, tekstFeltmb, tekstFeltAreal, tekstFeltSikkerhedsfaktor;

	@FXML
	private void haandterEkporterKnap() {
		FileUtils fu = new FileUtilsImpl();

		FileChooser fc = new FileChooser();
		fc.setTitle("PTECalculator");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF filer (*.pdf)", "*.pdf");
		fc.getExtensionFilters().add(extFilter);

		Stage stage = new Stage();
		File pdfFile = null;
		if ((pdfFile = fc.showSaveDialog(stage)) != null) {
			fu.createFile(pdfFile.getAbsolutePath());
		}

		String imgDirectoryPath = System.getProperty("user.home") + File.separator + PTEController.PTE_FOLDER
				+ File.separator + "img";
		File imgDirectory = new File(imgDirectoryPath);
		if (!imgDirectory.exists()) {
			imgDirectory.mkdirs();
		}

		
		SnapshotParameters param = new SnapshotParameters();
		param.setDepthBuffer(true);
		WritableImage image = KranTegner.getNode().snapshot(param, null);
		File newImage = new File(imgDirectory + File.separator + "kran-" + new Date().getTime());
		
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", newImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		UdregningTilPdf pdfData = new UdregningTilPdf(this.pteController);
		System.out.println(pdfFile.getAbsolutePath());
		SimplePdfWriter spw = new SimplePdfWriterImpl(pdfFile.getAbsolutePath());
		
		String hoejreData = "";
		for (int i = 0; i < pdfData.getHoejreListe().size(); i++) {
			hoejreData += pdfData.getHoejreListe().get(i) + "\n";
		}
		
		spw.addImageWithText(newImage.getAbsolutePath(), hoejreData);
		
		for (int i = 0; i < pdfData.getUnderListe().size(); i++) {
			spw.addParagraph(pdfData.getUnderListe().get(i));	
		}
		spw.close();
	}

	@Override
	public void update(List<Tilstand> tilstande) {
		if (tilstande.contains(Tilstand.VINKEL)) {
			profil = pteController.getProfil();

			if (profil == Profil.VANDRET) {
				tekstFeltfk.setText("cos("
						+ (Double.isNaN(pteController.getVinkel()) ? "v"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()))
						+ ") * "
						+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
						+ (Double.isNaN(pteController.getForskydningkraft()) ? "" : " = ")
						+ tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			} else {
				tekstFeltfk.setText("sin("
						+ (Double.isNaN(pteController.getVinkel()) ? "v"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()))
						+ ") * "
						+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
						+ (Double.isNaN(pteController.getForskydningkraft()) ? "" : " = ")
						+ tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()));
			}

			if (profil == Profil.LODRET) {
				tekstFeltnk.setText("cos("
						+ (Double.isNaN(pteController.getVinkel()) ? "v"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()))
						+ ") * "
						+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
						+ (Double.isNaN(pteController.getNormalkraft()) ? "" : " = ")
						+ tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			} else {
				tekstFeltnk.setText("sin("
						+ (Double.isNaN(pteController.getVinkel()) ? "v"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getVinkel()))
						+ ") * "
						+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim"
								: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
						+ (Double.isNaN(pteController.getNormalkraft()) ? "" : " = ")
						+ tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()));
			}
		}

		if (tilstande.contains(Tilstand.VAEGT)) {
			tekstFeltkg.setText((Double.isNaN(pteController.getDimensionerendeKraft()) ? "vægt"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft())) + " / "
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ (Double.isNaN(pteController.getVaegt()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt()));
		}
		if (tilstande.contains(Tilstand.DIMENSIONERENDE_KRAFT)) {
			tekstFeltdk.setText((Double.isNaN(pteController.getVaegt()) ? "vægt"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getVaegt())) + " * "
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getTyngdekraft())
					+ (Double.isNaN(pteController.getDimensionerendeKraft()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()));
		}
		if (tilstande.contains(Tilstand.BOEJNINGSMOMENT)) {

			tekstFeltmb.setText((Double.isNaN(pteController.getDimensionerendeKraft()) ? "Fdim"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getDimensionerendeKraft()))
					+ " * "
					+ (Double.isNaN(pteController.getLaengde()) ? "længde"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getLaengde()))
					+ (Double.isNaN(pteController.getBoejningsMoment()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment()));
		}
		if (tilstande.contains(Tilstand.TAU_FORSKYDNINGSSPAENDING)) {
			tekstFeltfs.setText((Double.isNaN(pteController.getForskydningkraft()) ? "Ft"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningkraft()))
					+ " / "
					+ (Double.isNaN(pteController.getIndtastAreal()) ? "areal"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()))
					+ (Double.isNaN(pteController.getTau_ForskydningsSpaending()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()));
		}
		if (tilstande.contains(Tilstand.SIGMAN)) {
			tekstFeltSigmaN.setText((Double.isNaN(pteController.getNormalkraft()) ? "Fn"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getNormalkraft()))
					+ " / "
					+ (Double.isNaN(pteController.getIndtastAreal()) ? "areal"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getIndtastAreal()))
					+ (Double.isNaN(pteController.getSigmaN()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN()));
		}
		if (tilstande.contains(Tilstand.SIGMAB)) {
			tekstFeltSigmaB.setText("( "
					+ (Double.isNaN(pteController.getBoejningsMoment()) ? "MB"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getBoejningsMoment()))
					+ " * "
					+ (Double.isNaN(pteController.getForskydningspunkt()) ? "e"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getForskydningspunkt()))
					+ " ) " + " / "
					+ (Double.isNaN(pteController.getInertimoment()) ? "I"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getInertimoment()))
					+ (Double.isNaN(pteController.getSigmaB()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()));
		}
		if (tilstande.contains(Tilstand.SIGMA_REF)) {
			tekstFeltSigmaRef.setText(" √ (("
					+ (Double.isNaN(pteController.getSigmaB()) ? "σB"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaB()))
					+ " + "
					+ (Double.isNaN(pteController.getSigmaN()) ? "σN"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaN()))
					+ ")² + 3 * "
					+ (Double.isNaN(pteController.getTau_ForskydningsSpaending()) ? "τ"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getTau_ForskydningsSpaending()))
					+ "²)" + (Double.isNaN(pteController.getSigmaRef()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()));
		}

		if (tilstande.contains(Tilstand.SIKKERHEDSFAKTOR)) {
			tekstFeltSikkerhedsfaktor.setText((Double.isNaN(pteController.getFlydespaending()) ? "σtill"
					: tekstfeltFormat.formaterDoubleTilString(pteController.getFlydespaending()))
					+ " / "
					+ (Double.isNaN(pteController.getSigmaRef()) ? "σref"
							: tekstfeltFormat.formaterDoubleTilString(pteController.getSigmaRef()))
					+ (Double.isNaN(pteController.getSikkerhedsfaktor()) ? "" : " = ")
					+ tekstfeltFormat.formaterDoubleTilString(pteController.getSikkerhedsfaktor()));

			if (tekstFeltSikkerhedsfaktor.getText() != "" && pteController.erSikkerhedsfaktorForLavt()) {
				tekstFeltSikkerhedsfaktor.setStyle(ADVARSEL);
			} else {
				tekstFeltSikkerhedsfaktor.setStyle(CSS);
			}

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
