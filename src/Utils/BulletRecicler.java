package Utils;

import java.lang.reflect.Array;

import GameConstructor.GameObject;
import GameObjects.Bullet;
import GameObjects.BulletTypeEnum;

public class BulletRecicler {
	private Bullet[] objects;
	private int length = 0;
	private int size = 0;

	public BulletRecicler(int size) {
		super();
		this.size = size;
		objects = new Bullet[size+2];
	}

	public Bullet getNewObject(float x, float y, float height, float width, float rotation, BulletTypeEnum bulletType) {
		length++;
		if (length > size) {
			int position = length % size;
			objects[position].setX(x);
			objects[position].setY(y);
			objects[position].setZeroAcceleration();
			objects[position].updateBullet();
			return objects[position]; 
		} else {
		    objects[length-1] =  new Bullet(x,y,height,width,rotation, bulletType);
			return objects[length-1];
		}
	}

	public Bullet[] getObjects() {
		return objects;
	}
	
	public int getLength() {
		if (length > size) {
			return size;
		} else {
		    return length;
		}
	}
	
}
