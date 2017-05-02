package Stages;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import Game.BulletPatterns;
import Game.MovementPattern;
import GameObjects.Alice;
import GameObjects.Bullet;
import GameObjects.BulletTypeEnum;
import GameObjects.Reimu;
import Models.Stage;
import Models.StageEnum;
import Utils.Draw;
import Utils.TextureLoader;

public class Stage1 extends Stage{
	static BufferedImage image = TextureLoader.loadImage("stage1.jpg");
	Reimu reimu;
	Alice alice;

	public Stage1() {
		super(StageEnum.STAGE_1);
	}

	public void init() {
	    setBackgroundID(TextureLoader.loadTexture(image, true));
		MovementPattern.setStage(StageEnum.STAGE_1);
		reimu = new Reimu(50f, 50f, 45f, 65f, 0f);
		alice = new Alice(300f, 300f, 40f, 65f, 0f);
	}

	public void script() {
		
	
		Draw.drawRectVBO();
		reimu.render();
		alice.render();
		reimu.actionsByKeyPress();
		MovementPattern.setObjectVelocity(alice, 5f);
		MovementPattern.bezierCurveMovement(alice, 150.0, -100, -100, -200, 0);
		Stage.setTimeAction(150);
		MovementPattern.bezierCurveMovement(alice, 150.0, 400, 400, 300, 100);
		
		BulletPatterns.setCharacter(alice);
		BulletPatterns.setBulletType(BulletTypeEnum.YELLOW_CIRCLE);
		Bullet bullet = BulletPatterns.randomBulletsCircularPattern(10);
		bullet.setMovement(4f);
		Bullet bullet2 = BulletPatterns.randomBulletsCircularPattern(10);
		bullet2.setMovement(3f);
		Bullet bullet3 = BulletPatterns.randomBulletsCircularPattern(10);
		bullet3.setMovement(1f);
		Draw.drawRectVBO();
	}

}
