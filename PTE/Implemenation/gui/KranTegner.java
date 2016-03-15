package gui;

import java.util.List;

import Logic.Profil;
import Logic.Tilstand;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class KranTegner extends PTEPane {
	
	private AnchorPane ap;
	Profil profil;
	private Line kranArm, xAxis, yAxis;
	private double x, y, height, width, nuvaerendeVinkel, rotateVinkel, katetA, vinkelSidePadding, vinkelHoejdePadding;
	Path snit;
	Text vinkelVaerdi;
	Arc arc;
	private TextField tf = new TextField();
	private Button button = new Button("Rotate kran arm");
	
	public KranTegner(double width, double height, Profil profil) {
		ap = new AnchorPane();
		this.profil = profil;
        this.width = width;
        this.height = height;
        this.x = width/4;
        this.y = this.height - this.height/3;
        nuvaerendeVinkel = 45;
        vinkelSidePadding = 10;
        vinkelHoejdePadding = 17;
        
        arc = new Arc();
        
        vinkelVaerdi = new Text(String.valueOf(nuvaerendeVinkel) + "°");
        ap.getChildren().add(vinkelVaerdi);
        
        katetA = 30;
	}
	
	public void tegnKran() {
		tegnXYAxis();
		tilfoejArc();
		tegnKranArm();
		tegnBoelgeStreg();
		rotateVandretKranArmOgSnit(nuvaerendeVinkel);
	}
	
	private void tilfoejArc() {
		double katetB = udrengKatet_b();
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(katetB);
		arc.setRadiusY(katetB);
		arc.setStartAngle(0);
		arc.setLength(nuvaerendeVinkel);
		
		arc.setStrokeWidth(2);
		arc.setStroke(Color.web("#000000"));
		arc.setFill(Color.CORNSILK.deriveColor(0, 0, 0, 0.0));
		ap.getChildren().add(arc);
	}
	
	private double udrengKatet_b() {
		return katetA / Math.tan(Math.toRadians(nuvaerendeVinkel));
	}
	
	private void tegnKranArm() {
		kranArm = new Line(x, y, width - width/4, y);
		kranArm.setStrokeWidth(10);
		kranArm.setStroke(Color.web("#55A9FC"));
		kranArm.setStrokeLineCap(StrokeLineCap.ROUND);
		kranArm.getTransforms().add(new Rotate(-nuvaerendeVinkel, x, y));
		
		ap.getChildren().add(kranArm);
	}
	
	private void tegnXYAxis() {
		// X axis
		xAxis = new Line(x - 50, y, width - width/4 + 50, y);
		xAxis.setStrokeWidth(2);
		xAxis.setStroke(Color.BLACK);
		
		double xAxisHeadsStart = width - width/4 + 45;
		double xAxisHeadsEnd = width - width/4 + 52;
		Line xAxisHead1 = new Line(xAxisHeadsStart, y - 5, xAxisHeadsEnd, y);
		Line xAxisHead2 = new Line(xAxisHeadsStart, y + 5, xAxisHeadsEnd, y);
		
		yAxis = new Line(x, y + 50, x, y -(width - width/4 - 50));
		yAxis.setStrokeWidth(2);
		yAxis.setStroke(Color.BLACK);
		
		double yAxisHeadsStart = y -(width - width/4 - 55);
		double yAxisHeadsEnd = y -(width - width/4 - 48);
		Line yAxisHead1 = new Line(x - 5, yAxisHeadsStart , x, yAxisHeadsEnd);
		Line yAxisHead2 = new Line(x + 5, yAxisHeadsStart, x, yAxisHeadsEnd);

		ap.getChildren().add(xAxis);
		ap.getChildren().add(yAxis);
		
        ap.getChildren().add(xAxisHead1);
        ap.getChildren().add(xAxisHead2);
        ap.getChildren().add(yAxisHead1);
        ap.getChildren().add(yAxisHead2);
	}
	
	private void tegnBoelgeStreg() {
		snit = new Path();

		snit.setStroke(Color.web("#F27D7D"));
		snit.setStrokeWidth(3);
		snit.setStrokeLineCap(StrokeLineCap.ROUND);
		snit.setFill(Color.CORNSILK.deriveColor(0, 0, 0, 0.0));
	    
//		double[] snitPunkter = getSnitStartOgSlutPunkter();
		
		MoveTo moveToCurve = new MoveTo();
		moveToCurve.setX(x - 30);
		moveToCurve.setY(y);
		
		CubicCurveTo curve = new CubicCurveTo();
	    curve.setControlX1(x - 20);
	    curve.setControlY1(y - 10);
	    curve.setControlX2(x - 10);
	    curve.setControlY2(y + 10);
	    curve.setX(x);
	    curve.setY(y);
	    
	    snit.getElements().add(moveToCurve);
	    snit.getElements().add(curve);
	    
	    MoveTo moveToCurve2 = new MoveTo();
	    moveToCurve2.setX(x);
	    moveToCurve2.setY(y);
	    CubicCurveTo curve2 = new CubicCurveTo();
	    curve2.setControlX1(x + 10);
	    curve2.setControlY1(y - 10);
	    curve2.setControlX2(x + 20);
	    curve2.setControlY2(y + 10);
	    curve2.setX(x + 30);
	    curve2.setY(y);
	    
	    snit.getElements().add(moveToCurve2);
	    snit.getElements().add(curve2);
	    
	    snit.getTransforms().add(new Rotate(nuvaerendeVinkel, x, y));
	    
		ap.getChildren().add(snit);
	}
	
	private void flytArcOgVinkelVandret() {
		double katetB;
		if(nuvaerendeVinkel >= 45) {
			katetB = katetA;
		} else {
			katetB = udrengKatet_b();
		}
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(katetB);
		arc.setRadiusY(katetB);
		arc.setStartAngle(0);
		arc.setLength(nuvaerendeVinkel);
		if(nuvaerendeVinkel <= 10) {
			vinkelVaerdi.setX(x*3 + vinkelSidePadding);
			vinkelVaerdi.setY(y - vinkelHoejdePadding);
			arc.setRadiusX(x*2 - x/3);
			arc.setRadiusY(x*2 - x/3);
		} else {
			vinkelVaerdi.setX(x + katetB + vinkelSidePadding);
			vinkelVaerdi.setY(y - vinkelHoejdePadding);
		}
	}
	
	private void flytArcOgVinkelLodret() {
		double katetB;
		if(nuvaerendeVinkel >= 45) {
			katetB = katetA;
		} else {
			katetB = udrengKatet_b();
		}
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setStartAngle(90 - nuvaerendeVinkel);
		arc.setLength(nuvaerendeVinkel);
		arc.setRadiusX(katetB);
		arc.setRadiusY(katetB);
		if(nuvaerendeVinkel > 10 && nuvaerendeVinkel <= 22) {
			vinkelVaerdi.setX(x + vinkelSidePadding);
			vinkelVaerdi.setY(y - katetB - vinkelHoejdePadding - vinkelSidePadding);
		} else if(nuvaerendeVinkel <= 10) {
			vinkelVaerdi.setX(x + vinkelSidePadding);
			vinkelVaerdi.setY(y - y/2 - vinkelHoejdePadding*2);
			arc.setRadiusX(y - y/2);
			arc.setRadiusY(y - y/2);
		} else {
			vinkelVaerdi.setX(x + vinkelSidePadding);
			vinkelVaerdi.setY(y - katetB - vinkelHoejdePadding);
		}
	}
	
	private void rotateVandretKranArmOgSnit(double vinkel) {
		if(vinkel <= 90 && vinkel >= 0) {
			Rotate kranRotate = null;
			Rotate snitRotate = null;
			if(vinkel > nuvaerendeVinkel) {
				rotateVinkel = vinkel - nuvaerendeVinkel;
				nuvaerendeVinkel = vinkel;
				kranRotate = new Rotate(-rotateVinkel, x, y);
				snitRotate = new Rotate(-rotateVinkel, x, y);
			} else {
				rotateVinkel = nuvaerendeVinkel - vinkel;
				nuvaerendeVinkel = vinkel;
				kranRotate = new Rotate(rotateVinkel, x, y);
				snitRotate = new Rotate(rotateVinkel, x, y);
			}
			kranArm.getTransforms().add(kranRotate);
			snit.getTransforms().add(snitRotate);
			vinkelVaerdi.setText(String.valueOf(nuvaerendeVinkel) + "°");
			flytArcOgVinkelVandret();
		}
	}
	
	private void rotateLodretKranArmOgSnit(double vinkel) {
		if(vinkel <= 90 && vinkel >= 0) {
			Rotate kranRotate = null;
			Rotate snitRotate = null;
			if(vinkel > nuvaerendeVinkel) {
				rotateVinkel = vinkel - nuvaerendeVinkel;
				nuvaerendeVinkel = vinkel;
				kranRotate = new Rotate(rotateVinkel, x, y);
				snitRotate = new Rotate(rotateVinkel, x, y);
			} else {
				rotateVinkel = nuvaerendeVinkel - vinkel;
				nuvaerendeVinkel = vinkel;
				kranRotate = new Rotate(-rotateVinkel, x, y);
				snitRotate = new Rotate(-rotateVinkel, x, y);
			}
			kranArm.getTransforms().add(kranRotate);
			snit.getTransforms().add(snitRotate);
			vinkelVaerdi.setText(String.valueOf(nuvaerendeVinkel) + "°");
			flytArcOgVinkelLodret();
		}
	}
	
	public Node getNode() {
		return ap;
	}
	
	private void rotateKranArmOgSnit() {
		if(profil == Profil.VANDRET) {
			rotateVandretKranArmOgSnit(nuvaerendeVinkel);
		} else if(profil == Profil.LODRET) {
			rotateLodretKranArmOgSnit(nuvaerendeVinkel);
		}
	}
	
	@Override
	public void update(List<Tilstand> tilstande) {
		if(tilstande.contains(Tilstand.PROFIL)) {
			profil = pteController.getProfil();
		}
		if(tilstande.contains(Tilstand.VINKEL)) {
			nuvaerendeVinkel = pteController.getVinkel();
			rotateKranArmOgSnit();
		}
	}
}
