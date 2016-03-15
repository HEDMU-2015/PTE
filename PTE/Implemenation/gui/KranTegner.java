package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class KranTegner{
	
	private AnchorPane ap;
	private Line kranArm, xAxis, yAxis;
	private double x, y, height, width, nuvaerendeVinkel, rotateVinkel, katetA;
	Path snit;
	Text vinkelVaerdi;
	
	Arc arc;
	
	private TextField tf = new TextField();
	private Button button = new Button("Rotate kran arm");
	
	public KranTegner(double width, double height) {
		ap = new AnchorPane();
        this.width = width;
        this.height = height;
        this.x = width/4;
        this.y = this.height - this.height/3;
        nuvaerendeVinkel = 45;
        
        arc = new Arc();
        
        vinkelVaerdi = new Text(x + 40, y - 10, String.valueOf(nuvaerendeVinkel) + "°");
        ap.getChildren().add(vinkelVaerdi);
        
        katetA = 30;
        
        // Skal slettes
        ap.getChildren().add(tf);
		ap.getChildren().add(button);
		HBox hb = new HBox();
		hb.getChildren().add(tf);
		hb.getChildren().add(button);
		ap.getChildren().add(hb);
		button.setOnAction( ae -> {
			double slutVinkel = Double.valueOf(tf.getText());
			
//			rotateVandretKranArmOgSnit(slutVinkel);
			rotateLodretKranArmOgSnit(slutVinkel);
		});
	}
	
	public void tegnKran() {
		tegnXYAxis();
		tilfoejArc();
		tegnKranArm();
		tegnBoelgeStreg();
//		rotateVandretKranArmOgSnit(nuvaerendeVinkel);
		rotateLodretKranArmOgSnit(nuvaerendeVinkel);
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
	
	private void flytArcVandret() {
		double katetB = udrengKatet_b();
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(katetB);
		arc.setRadiusY(katetB);
		arc.setStartAngle(0);
		arc.setLength(nuvaerendeVinkel);
	}
	
	private void flytArcLodret() {
		double katetB;
		if(nuvaerendeVinkel > 45) {
			katetB = katetA;
		} else {
			katetB = udrengKatet_b();
		}
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(katetB);
		arc.setRadiusY(katetB);
		arc.setStartAngle(90 - nuvaerendeVinkel);
		arc.setLength(nuvaerendeVinkel);
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
			if(nuvaerendeVinkel >= 0 && nuvaerendeVinkel <= 12) {
				vinkelVaerdi.setTranslateX(vinkelVaerdi.getX() + 60);
			} else if(nuvaerendeVinkel > 12 && nuvaerendeVinkel <= 20) {
				vinkelVaerdi.setTranslateX(vinkelVaerdi.getX() - 70);
			}
			vinkelVaerdi.setText(String.valueOf(nuvaerendeVinkel) + "°");
			flytArcVandret();
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
			if(nuvaerendeVinkel >= 0 && nuvaerendeVinkel <= 12) {
				vinkelVaerdi.setTranslateX(vinkelVaerdi.getX() + 60);
			} else if(nuvaerendeVinkel > 12 && nuvaerendeVinkel <= 20) {
				vinkelVaerdi.setTranslateX(vinkelVaerdi.getX() - 70);
			}
			vinkelVaerdi.setText(String.valueOf(nuvaerendeVinkel) + "°");
			flytArcLodret();
		}
	}
	
	public Node getNode() {
		return ap;
	}
}
