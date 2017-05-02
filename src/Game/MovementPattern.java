package Game;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

import GameConstructor.GameObject;
import GameConstructor.GamePhysicsBody;
import Geometry.QuadraticCurve;
import Models.Stage;
import Models.StageEnum;

public class MovementPattern {
	
	private static Stage stage;
	
	public static void setStage(StageEnum stageEnum) {
		MovementPattern.stage = Stage.getStage(stageEnum);
	}
	

	public static Stage getStage() {
		return stage;
	}

	public static void moveUp(GamePhysicsBody object, long duration) {
		if ((stage.getTimer() < Stage.getTimeAction() + duration)  && isTimeAction()) {
			object.MoveUp();
		}
	}
	
	public static void moveDown(GamePhysicsBody object, long duration) {
		if ((stage.getTimer() < Stage.getTimeAction() + duration) && isTimeAction()) {
			object.MoveDown();
		}
	}
	
	public static void moveLeft(GamePhysicsBody object, long duration) {
		if ((stage.getTimer() < Stage.getTimeAction() + duration)  && isTimeAction()) {
			object.MoveLeft();
		}
	}
	
	public static void moveRight(GamePhysicsBody object, long duration) {
		if ((stage.getTimer() < Stage.getTimeAction() + duration) && isTimeAction()) {
			object.MoveRight();
		}
	}
	
	public static void angularMovement(GamePhysicsBody object,double angle, long duration) {
		if ((stage.getTimer() < Stage.getTimeAction() + duration) && isTimeAction()) {
			double angleRadians = Math.toRadians(angle);
			object.Move(Math.cos(angleRadians) * object.getMovement(), Math.sin(angleRadians) * object.getMovement());
		}
	}
	
	public static void circularMovement(GamePhysicsBody object, long duration, long radius) {
		if ((stage.getTimer() < duration + Stage.getTimeAction()) && isTimeAction()) {
			long timeIterated = stage.getTimer() - Stage.getTimeAction(); 
			double angleSpeed = (Math.PI/radius)* timeIterated;
			object.Move(Math.cos(angleSpeed) * object.getMovement(), Math.sin(angleSpeed) * object.getMovement());
		}
	}
	
	public static void bezierCurveMovement(GamePhysicsBody object, double duration, Point2D.Double pControle, Point2D.Double pfinal) {
		if ((stage.getTimer() < duration + Stage.getTimeAction()) && isTimeAction()) {
			double timeIterated = (stage.getTimer() - Stage.getTimeAction())/duration;
			Point2D.Double pinicial = new Point2D.Double((double) object.getX(), (double) object.getY()); 
			Point2D.Double pControleX = new Point2D.Double(object.getX()+ pControle.getX(), object.getY()+ pControle.getY()); 
			Point2D.Double pfinalX = new Point2D.Double(object.getX()+ pfinal.getX(), object.getY()+ pfinal.getY()); 
			QuadraticCurve curve = new QuadraticCurve(pinicial, pfinalX, pControleX); 
			Point2D.Double point = curve.getBezierPoint(timeIterated);
			Point2D.Double nextPoint = curve.getBezierPoint(timeIterated + 1/duration);
			double deltaX = nextPoint.getX() - point.getX(); 
			double deltaY = nextPoint.getY() - point.getY(); 
			object.Move(deltaX, deltaY);
		}
	}
	
	public static void bezierCurveMovement(GamePhysicsBody object, double duration, double pCx, double pCy, double pFx, double pFy) {
		if ((stage.getTimer() < duration + Stage.getTimeAction()) && isTimeAction()) {
			double timeIterated = (stage.getTimer() - Stage.getTimeAction())/duration;
			Point2D.Double pinicial = new Point2D.Double((double) object.getX(), (double) object.getY()); 
			Point2D.Double pControleX = new Point2D.Double(object.getX()+ pCx, object.getY()+ pCy); 
			Point2D.Double pfinalX = new Point2D.Double(object.getX()+ pFx, object.getY()+ pFy); 
			QuadraticCurve curve = new QuadraticCurve(pinicial, pfinalX, pControleX); 
			Point2D.Double point = curve.getBezierPoint(timeIterated);
			Point2D.Double nextPoint = curve.getBezierPoint(timeIterated + 1/duration);
			double deltaX = nextPoint.getX() - point.getX(); 
			double deltaY = nextPoint.getY() - point.getY(); 
			object.Move(deltaX, deltaY);
		}
	}
	
	public static void setObjectVelocity(GamePhysicsBody object, float velocity) {
		object.setMovement(velocity);
	}
	
	private static boolean isTimeAction() {
		return stage.getTimer() > Stage.getTimeAction();
	}
	
	

}
