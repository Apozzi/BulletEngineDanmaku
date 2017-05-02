package GameObjects;

import java.awt.image.BufferedImage;

import org.lwjgl.opengl.GL11;

import Game.MovementPattern;
import Game.Render;
import GameConstructor.GameObject;
import GameConstructor.GamePhysicsBody;
import Utils.Draw;
import Utils.TextureLoader;

public class Bullet extends GamePhysicsBody{
	
	static BufferedImage image;
	static int[] textureID;
	private BulletTypeEnum bulletType;
	
	static {
		textureID = new int[2];
		String picture = "";
		picture = "images/bullet.png";
		image = TextureLoader.loadImage(picture);
		textureID[BulletTypeEnum.RED_DIRECTIONAL.getNumber()] = TextureLoader.loadTexture(image, false);
		picture = "images/bullet2.png";
		image = TextureLoader.loadImage(picture);
		textureID[BulletTypeEnum.YELLOW_CIRCLE.getNumber()] = TextureLoader.loadTexture(image, false);
	}

	public Bullet(float x, float y, float height, float width, float rotation, BulletTypeEnum bulletType) {
		super(x, y, height, width, rotation);
		this.bulletType = bulletType;
		this.setCheckCollision(false);
	}
	
	public void updateBullet() {
		setRectangularArea(this.getX(), this.getY(), this.getHeight(), this.getWidth());
	}

	@Override
	public void render() {
		if (this.isOffScreen()) {
			this.setActive(true);
			this.drawArea();
			this.setRotation((float)Math.toDegrees(this.getAngle()));
			Draw.drawImageCenteredRotation(textureID[bulletType.getNumber()], this.getX(), this.getY(), this.getHeight(), this.getWidth(), this.getRotation()-90);
		} else {
			this.setActive(false);
		}
		
	}
	
	public static boolean bulletDelay(int delay) {
		return !(MovementPattern.getStage().getTimer() % delay >  delay/2);
	}

}
