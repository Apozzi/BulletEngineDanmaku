package Game;

import GameConstructor.GameObject;
import GameObjects.Bullet;
import GameObjects.BulletTypeEnum;
import Utils.BulletRecicler;

public class BulletPatterns {
	private static BulletRecicler bullets  = new BulletRecicler(1200);
	private static GameObject character;
	private static double centerX;
	private static double centerY;
	private static float bulletSize = 20;
	private static BulletTypeEnum bulletType;
	
	public static void setCharacter(GameObject attacker) {
		BulletPatterns.character = attacker;
		centerX = character.getX() + character.getWidth()/2;
		centerY = character.getY() + character.getHeight()/2;
	}
	
	public static Bullet randomBulletsCircularPattern(double circularSpawnDistance) {
		double randomAngle = Math.random()*360;
		float spawnX = (float)(centerX + (circularSpawnDistance*Math.cos(randomAngle)));
		float spawnY = (float)(centerY + (circularSpawnDistance*Math.sin(randomAngle)));
		Bullet bullet = bullets.getNewObject(spawnX,spawnY,bulletSize,bulletSize,0,bulletType);
		bullet.setAngle(randomAngle);
		
		Bullet[] bulletsArray = bullets.getObjects(); 
		for (int i = 0; i < bullets.getLength(); i++) {
			Bullet actualBullet = bulletsArray[i];
			bulletsArray[i].Move(Math.cos(actualBullet.getAngle())*actualBullet.getMovement(),Math.sin(actualBullet.getAngle())*actualBullet.getMovement());
			bulletsArray[i].render();
		}
		
		return bullet;
	}

	public static float getBulletSize() {
		return bulletSize;
	}

	public static void setBulletSize(float bulletSize) {
		BulletPatterns.bulletSize = bulletSize;
	}

	public static BulletTypeEnum getBulletType() {
		return bulletType;
	}

	public static void setBulletType(BulletTypeEnum bulletType) {
		BulletPatterns.bulletType = bulletType;
	}
	
	

}
