package Models;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import Game.GameKeys;
import Game.KeyPressListener;
import Game.MovementPattern;
import GameConstructor.GamePhysicsBody;
import GameObjects.Bullet;
import GameObjects.BulletTypeEnum;
import Utils.BulletRecicler;
import Utils.Draw;
import Utils.ObjectRecicler;
import Utils.TextureLoader;

public abstract class Player extends GamePhysicsBody{
	
	private static BulletRecicler bullets;

	public Player(float x, float y, float height, float width, float rotation) {
		super(x, y, height, width, rotation);
		bullets = new BulletRecicler(30);
		this.setCheckCollision(false);
	}
	
	public abstract void render();
	
	public void actionsByKeyPress() {
		moveByKeyPress();
		shotByKeyPress();
		bulletMovement();
	}
	
	public void shotByKeyPress() {
		int delay = 10;
		boolean reload = Bullet.bulletDelay(delay);
		if (KeyPressListener.isKeyDown(GameKeys.TECLA_Z.getCodeKey()) && !reload) {
			float bulletHeight = 20;
			float bulletWidth = 20;
			float x = this.getX()+(this.getWidth()/2)-(bulletWidth/2);
			float y = this.getY()+this.getHeight()-(bulletHeight/2)+ 20;
			Bullet bullet = bullets.getNewObject(x,y,bulletHeight,bulletWidth,0,BulletTypeEnum.RED_DIRECTIONAL);
			bullet.setMovement(6);
			bullet.setVariableAcceleration(true);
			bullet.setFriction(5);
		}
	}
	public void bulletMovement() {
		Bullet[] bulletsArray = bullets.getObjects(); 
		for (int i = 0; i < bullets.getLength(); i++) {
			bulletsArray[i].MoveUp();
			bulletsArray[i].render();
		}
	}
	
	
	
	public void moveByKeyPress() {
		boolean keyPress = false;
		if (KeyPressListener.isKeyDown(GameKeys.DIRECIONAL_DIREITA.getCodeKey())) {
			this.MoveRight();
			keyPress = true;
		}
		if (KeyPressListener.isKeyDown(GameKeys.DIRECIONAL_ESQUERDA.getCodeKey())) {
			this.MoveLeft();
			keyPress = true;
		}
		if (KeyPressListener.isKeyDown(GameKeys.DIRECIONAL_CIMA.getCodeKey())) {
			this.MoveUp();
			keyPress = true;
		}
		if (KeyPressListener.isKeyDown(GameKeys.DIRECIONAL_BAIXO.getCodeKey())) {
			this.MoveDown();
			keyPress = true;
		}
		if (!keyPress) {
			this.doNothing();
		}
		this.render();
	}

	public static BulletRecicler getBullets() {
		return bullets;
	}
}
