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
	
	private static AnchorPane ap;
	private Line kranArm, xAxis, yAxis;
	private double x, y, height, width, nuvaerendeVinkel, rotateVinkel, katetA, vinkelSidePadding, vinkelHoejdePadding;
	boolean erKranTegnet = false;
	Profil profil;
	Path snit;
	Text vinkelVaerdi;
	Arc arc;

	public KranTegner(double width, double height) {
		ap = new AnchorPane();
        this.width = width;
        this.height = height;
        this.x = width/4;
        this.y = this.height - this.height/3;
        
        vinkelSidePadding = 10;
        vinkelHoejdePadding = 17;
        
        kranArm = new Line(x, y, width - width/4, y);
        arc = new Arc(); 
        snit = new Path();
        vinkelVaerdi = new Text();
        
        katetA = 30;
        
        tegnXYAxis();
	}
	
	private void tegnKran() {
        setUpArc();
        setUpKranArm();
		setUpBoelgeStreg();
		ap.getChildren().addAll(arc, kranArm, vinkelVaerdi, snit);
	}
	
	private void setUpKranArm() {
		kranArm.setStrokeWidth(10);
		kranArm.setStroke(Color.web("#55A9FC"));
		kranArm.setStrokeLineCap(StrokeLineCap.ROUND);
		kranArm.toFront();
	}
	
	private void setUpArc() {
		arc.setStrokeWidth(2);
		arc.setStroke(Color.web("#000000"));
		arc.setFill(Color.CORNSILK.deriveColor(0, 0, 0, 0.0));
	}
	
	private double udrengKatet_b() {
		return katetA / Math.tan(Math.toRadians(nuvaerendeVinkel));
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
	
	private void setUpBoelgeStreg() {
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
	    snit.getTransforms().add(new Rotate(-90, x, y));
	    snit.getElements().add(moveToCurve2);
	    snit.getElements().add(curve2);
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
	
	private void rotateKranArmOgSnit(double vinkel) {
		if(vinkel <= 90 && vinkel >= 0) {
			nuvaerendeVinkel = vinkel;
			kranArm.getTransforms().clear();
			snit.getTransforms().clear();
			snit.getTransforms().add(new Rotate(-90, x, y));
			if(profil == Profil.VANDRET) {
				kranArm.getTransforms().add(new Rotate(-vinkel, x, y));
				snit.getTransforms().add(new Rotate(-vinkel, x, y));
				vinkelVaerdi.setText(String.valueOf(vinkel) + "°");
				flytArcOgVinkelVandret();
			} else if(profil == Profil.LODRET) {
				kranArm.getTransforms().add(new Rotate(-(90 - vinkel), x, y));
				snit.getTransforms().add(new Rotate(-(90 - vinkel), x, y));
				vinkelVaerdi.setText(String.valueOf(vinkel) + "°");
				flytArcOgVinkelLodret();
			}
		}
	}
	
//	private void rotateLodretKranArmOgSnit(double vinkel) {
//		if(vinkel <= 90 && vinkel >= 0) {
//			Rotate kranRotate = null;
//			Rotate snitRotate = null;
//			if(vinkel > nuvaerendeVinkel) {
//				rotateVinkel = vinkel - nuvaerendeVinkel;
//				nuvaerendeVinkel = vinkel;
//				kranRotate = new Rotate(rotateVinkel, x, y);
//				snitRotate = new Rotate(rotateVinkel, x, y);
//			} else {
//				rotateVinkel = nuvaerendeVinkel - vinkel;
//				nuvaerendeVinkel = vinkel;
//				kranRotate = new Rotate(-rotateVinkel, x, y);
//				snitRotate = new Rotate(-rotateVinkel, x, y);
//			}
//			kranArm.getTransforms().add(kranRotate);
//			snit.getTransforms().add(snitRotate);
//			vinkelVaerdi.setText(String.valueOf(nuvaerendeVinkel) + "°");
//			flytArcOgVinkelLodret();
//			kranArm.getTransforms().clear();
//		}
//	}
	
	public static Node getNode() {
		return ap;
	}
	
//	private void rotateKranArmOgSnit(double vinkel) {
//		if(profil == Profil.VANDRET) {
//			rotateVandretKranArmOgSnit(vinkel);
//		} else if(profil == Profil.LODRET) {
//			rotateLodretKranArmOgSnit(vinkel);
//		}
//	}
	
	@Override
	public void update(List<Tilstand> tilstande) {
		if(tilstande.contains(Tilstand.VINKEL)) {
			profil = pteController.getProfil();
			if(!erKranTegnet) {
				tegnKran();
				erKranTegnet = true;
			}
			rotateKranArmOgSnit(pteController.getVinkel());
		}
	}
}
