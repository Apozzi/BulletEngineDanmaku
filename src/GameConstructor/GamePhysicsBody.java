package GameConstructor;

import java.util.ArrayList;

import Geometry.Line;
import Utils.ObjectShape;

public abstract class GamePhysicsBody extends GameObject{
	
	private static ArrayList<GamePhysicsBody> allPhysicalBody = new ArrayList<>(); 
	
	private boolean isVariableAcceleration = false;
	private float accelerationX;
	private float accelerationY;
	private float friction = 20;
	private boolean active = true;
	private double angle;

	private boolean checkCollision = true;
	private boolean angularMovement = false;

	public GamePhysicsBody(float x, float y, float height, float width) {
		super(x, y, height, width);
		allPhysicalBody.add(this);
	}
	
	public GamePhysicsBody(float x, float y, float height, float width, float rotation) {
		super(x, y, height, width, rotation);
		allPhysicalBody.add(this);
	}
	
	public GamePhysicsBody(ObjectShape shape, float x, float y, float height, float width) {
		super(shape, x, y, height, width);
		allPhysicalBody.add(this);
	}
	
	@Override
	public void MoveRight() {
		x += getMovement();
		areaTranslate(getMovement(), 0);
		if (this.isCollided()) {
			x -= getMovement();
			areaTranslate(-getMovement(), 0);
			accelerationX = -friction;
			if (isVariableAcceleration) {
				cineticForceMovement();
			}
		} else {
			if (isVariableAcceleration) {
				accelerationX++;
				cineticForceMovement();
			}
		}
		this.angle = 0;
	}
	
	@Override
	public void MoveLeft() {
		x -= getMovement();
		areaTranslate(-getMovement(), 0);
		if (this.isCollided()) {
			x += getMovement();
			areaTranslate(getMovement(), 0);
			accelerationX = -friction;
			if (isVariableAcceleration) {
				cineticForceMovement();
			}
		} else {
			if (isVariableAcceleration) {
				accelerationX--;
				cineticForceMovement();
			}
		}
		this.angle = Math.PI;
	}
	
	@Override
	public void MoveDown() {
		y -= getMovement();
		areaTranslate(0 , -getMovement());
		if (this.isCollided()) {
			y += getMovement();
			areaTranslate(0, getMovement());
			accelerationY = friction;
			if (isVariableAcceleration) {
				cineticForceMovement();
			}
		} else {
			if (isVariableAcceleration) {
				accelerationY--;
				cineticForceMovement();
			}
		}
		this.angle = (3*Math.PI)/2;
	}
	
	@Override
	public void MoveUp() {
		y += getMovement();
		areaTranslate(0 , getMovement());
		if (this.isCollided()) {
			y -= getMovement();
			areaTranslate(0, -getMovement());
			accelerationY = -friction;
			if (isVariableAcceleration) {
				cineticForceMovement();
			}
		} else {
			if (isVariableAcceleration) {
				accelerationY++;
				cineticForceMovement();
			}
		}
		this.angle = Math.PI/2;
	}
	
	public void Move(double movx,double movy) {
		if (isActive()) {
			double oldX = x;
			double oldY = y;
			if (angularMovement) {
				movx = movx*Math.cos(angle);
				movy = movy*Math.sin(angle);
			}
			y += movy;
			x += movx;
			areaTranslate(movx , movy);
			if (this.isCollided()) {
				y -= movy;
				x -= movx;
				areaTranslate(-movx, -movy);
			}
			if (!angularMovement) {
				this.angle = Line.getAngleFromXY(oldX, oldY, x, y);
			}
			render();
		}
	}
	
	
	public void doNothing() {
		if (isVariableAcceleration) {
			cineticForceMovement();
			accelerationY = accelerationY/(1+(0.005f * friction));
			accelerationX = accelerationX/(1+(0.005f * friction));
		}
		render();
	}
	
	public void angularAceleration(double acceleration) {
		if (isVariableAcceleration) {
			accelerationX += Math.cos(angle)*acceleration;
			accelerationY += Math.sin(angle)*acceleration;
			cineticForceMovement();
		}
	}
	
	private void cineticForceMovement() {
		y += accelerationY/friction;
		x += accelerationX/friction;
		areaTranslate(accelerationX/friction, accelerationY/friction);
	}
	
	public boolean isVariableAcceleration() {
		return isVariableAcceleration;
	}

	public void setVariableAcceleration(boolean isVariableAcceleration) {
		this.isVariableAcceleration = isVariableAcceleration;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public boolean isCheckCollision() {
		return checkCollision;
	}

	public void setCheckCollision(boolean checkCollision) {
		this.checkCollision = checkCollision;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}
	
	public void setZeroAcceleration() {
		accelerationX = 0;
		accelerationY = 0;
	}
	
	public void setAngularMovement(boolean angularMovement) {
		this.angularMovement = angularMovement;
	}

	private boolean isCollided() {		
		if (!checkCollision) {
			return false;
		}
		for (GamePhysicsBody gamePhysicsBody : allPhysicalBody) {
			if ((!this.equals(gamePhysicsBody)) && this.collide(gamePhysicsBody)) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
