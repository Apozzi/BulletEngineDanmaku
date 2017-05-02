package GameConstructor;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;

import Game.Render;
import Models.Stage;
import Utils.Draw;
import Utils.ObjectShape;


public abstract class  GameObject {
	
	float initialX;
	float initialY;
    float x;
	float y;
	float height;
	float width;
	float movement;
	float rotation;
	ObjectShape shape;
	Shape area;
	
	
	public GameObject(float x, float y, float height, float width, float rotation) {
		super();
		this.x = x;
		this.y = y;
		this.initialX = x;
		this.initialY = y;
		this.height = height;
		this.width = width;
		this.rotation = rotation;
		this.movement = 6f;
		this.shape = ObjectShape.COMPLEXRECTANGLE;
		this.area = setRectangularArea(x, y, height, width, rotation);

	}
	
	public GameObject(float x, float y, float height, float width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.rotation = 0f;
		this.movement = 3f;
		this.shape = ObjectShape.RECTANGLE;
		this.area = setRectangularArea(x, y, height, width);

	}
	
	public GameObject(ObjectShape shape, float x, float y, float height, float width) {
		super();
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.rotation = 0f;
		this.movement = 3f;
		if (shape.equals(ObjectShape.ELLIPSE))
			this.area = setEllipseArea(x, y, height, width);
		if (shape.equals(ObjectShape.RECTANGLE))
			this.area = setRectangularArea(x, y, height, width);
		

	}
	
	public void toElipse() {
		this.area = setEllipseArea(x, y, height, width);
	}
	
	public void translateX(float mov) {
		x += mov;
		areaTranslate(mov, 0);
		render();
	}
	
	public void translateY(float mov) {
		y += mov;
		areaTranslate(0, mov);
		render();
	}
	
	public void MoveRight() {
		x += getMovement();
		areaTranslate(getMovement(), 0);
		render();
	}
	
	public void MoveLeft() {
		x -= getMovement();
		areaTranslate(-getMovement(), 0);
		render();
	}
	
	
	public void MoveUp() {
		y -= getMovement();
		areaTranslate(0 , -getMovement());
		render();
	}
	
	public void MoveDown() {
		y += getMovement();
		areaTranslate(0 , getMovement());
		render();
	}
	
	public Shape setRectangularArea(float x, float y, float height, float width, float rotation){
		 Point point1,point2,point3,point4;
		 point1 = new Point((int) x,(int) y);
	     point2 = new Point((int) (x+ width),(int) y);
	     point3 = new Point((int) (x+ width),(int) (y+ height));
	     point4 = new Point((int) x,(int) (y+ height));
	     this.area = setRectangularArea(x, y, height, width);
	     areaRotation(rotation, width/2 - x/2, height/2 - y/2);
	     return area;
	}
	
	public Shape setRectangularArea(float x, float y, float height, float width){
	     area = new Area(new Rectangle2D.Float(x, y, width, height));
	     return area;
	}
	
	public Shape setEllipseArea(float x, float y, float height, float width){
		 area = new Area(new Ellipse2D.Float(x, y, width, height));
	     return area;
	}
	
	
	public Shape getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public abstract void render();
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getMovement() {
		return movement;
	}

	public void setMovement(float movement) {
		this.movement = movement;
	}

	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public boolean collide(GameObject object) {
		Area interceptArea = new Area(this.area);
		interceptArea.intersect(new Area(object.getArea())); 
		return !interceptArea.isEmpty();
		
	}

	void areaRotation(double rotation ,double centerX ,double centerY) {
		AffineTransform transform = new AffineTransform();
	    transform.rotate(Math.toRadians(rotation),centerX, centerY);
	    this.area = transform.createTransformedShape(this.area);
		drawArea();
	}
	
	void areaTranslate(double x, double y) {
		Area areaTransform = new Area(area); 
		AffineTransform transform = new AffineTransform();
		transform.translate(x/2, y/2);
		areaTransform.transform(transform);
		this.area = areaTransform.createTransformedArea(transform);
		drawArea();
	}
	
	public boolean isOffScreen() {
		return !((this.getX() < -50 || this.getX() > Render.getScreenX()) || (this.getY() < -50 || this.getX() > Render.getScreenY()));
	}
	
	public void drawArea() {
		if (Render.isCollisionDebugging()) {
				Area a = new Area(area);
			    PathIterator iterator = a.getPathIterator(null);
			    float[] floats = new float[6];
			    Polygon polygon = new Polygon();
			    while (!iterator.isDone()) {
			        int type = iterator.currentSegment(floats);
			        int x = (int) floats[0];
			        int y = (int) floats[1];
			        if(type != PathIterator.SEG_CLOSE) {
			            polygon.addPoint(x, y);
			            Draw.drawCircle(x,y,10);
			        }
			        iterator.next();
			}
	
			
		}
	}
	


}
