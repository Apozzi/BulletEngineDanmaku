package Geometry;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Line {
	private Point2D.Double pinitial;
	private Point2D.Double pfinal;
	
	public Line(Double pinitial, Double pfinal) {
		super();
		this.pinitial = pinitial;
		this.pfinal = pfinal;
	}
	
	public double angle() {
		double deltaX = pfinal.getX() - pinitial.getX();
		double deltaY = pfinal.getY() - pinitial.getY();
		return Math.atan2(deltaY, deltaX);
	}
	
	public static double getAngleFromXY(double oldX,double oldY,double x,double y) {
		double deltaX = x - oldX;
		double deltaY = y - oldY;
		return Math.atan2(deltaY, deltaX);
	}
	
	public Point2D.Double getBezierPoint(double t) {
		double deltaX = pfinal.getX() - pinitial.getX();
		double deltaY = pfinal.getY() - pinitial.getY();
		return new Point2D.Double(pinitial.getX() + (deltaX*t),pinitial.getY() + (deltaY*t));
	}
	
	public Point2D.Double getPinitial() {
		return pinitial;
	}
	public void setPinitial(Point2D.Double pinitial) {
		this.pinitial = pinitial;
	}
	public Point2D.Double getPfinal() {
		return pfinal;
	}
	public void setPfinal(Point2D.Double pfinal) {
		this.pfinal = pfinal;
	}

	
	
}
