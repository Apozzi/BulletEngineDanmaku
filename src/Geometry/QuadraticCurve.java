package Geometry;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class QuadraticCurve {
	private Point2D.Double pinitial;
	private Point2D.Double pfinal;
	private Point2D.Double pcontrol;
	
	private Line linit;
	private Line lfinal;
	
	public QuadraticCurve(Double pinitial, Double pfinal, Double pcontrol) {
		super();
		this.pinitial = pinitial;
		this.pfinal = pfinal;
		this.pcontrol = pcontrol;
		
		linit = new Line(pinitial, pcontrol);
		lfinal = new Line(pcontrol, pfinal);
	}
	
	public Point2D.Double getBezierPoint(double t) {
		Point2D.Double binit = linit.getBezierPoint(t);
		Point2D.Double bend = lfinal.getBezierPoint(t);
		Line lcontrol = new Line(binit, bend);
		return lcontrol.getBezierPoint(t);
	}
	
	

}
