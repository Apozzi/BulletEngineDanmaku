package Models;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import Game.GameKeys;
import Game.KeyPressListener;
import Game.MovementPattern;
import Game.Render;
import GameConstructor.GamePhysicsBody;
import GameObjects.Bullet;
import Utils.BulletRecicler;
import Utils.Draw;
import Utils.ObjectRecicler;
import Utils.TextureLoader;

public abstract class Boss extends GamePhysicsBody{
	
	private BulletRecicler bullets;
	private float totalLive = 9000;
	private float live;

	public Boss(float x, float y, float height, float width, float rotation) {
		super(x, y, height, width, rotation);
		bullets = new BulletRecicler(60);
		live = totalLive;
		this.setCheckCollision(false);
	}
	
	public void setLive(int live) {
		this.totalLive = live;
		this.live = live;
	}
	
	public abstract void render();
	
	public void interact() {
		bulletCollision();
	}
	
	public void renderLiveBar() {
		float border = 70;
		float size = 10;
		float liveBarStatus = ((live/totalLive) * (Render.getScreenY() - 240));
		Draw.drawRect(border, border/5,liveBarStatus, size, 0);
	}
	
	private void bulletCollision() {
		BulletRecicler bullets = Player.getBullets();
		Bullet[] bulletsArray = bullets.getObjects();
		for (int i = 0; i < bullets.getLength(); i++) {
			if (this.collide(bulletsArray[i])) {
				bulletsArray[i].setX(-100);
				if (live != 0) { 
					live--;
				}
			}
		}
	}

}
