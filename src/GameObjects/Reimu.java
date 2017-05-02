package GameObjects;

import java.awt.image.BufferedImage;

import Models.Player;
import Utils.Draw;
import Utils.TextureLoader;

public class Reimu extends Player{
	
	static BufferedImage image;
	static int textureID;

	public Reimu(float x, float y, float height, float width, float rotation) {
		super(x, y, height, width, rotation);
		if (image == null) {
			image = TextureLoader.loadImage("images/yuyuko.png");
			textureID = TextureLoader.loadTexture(image , true);
		}
	}

	@Override
	public void render() {
		this.drawArea();
		Draw.drawImage(textureID, this.getX(), this.getY(), this.getHeight(), this.getWidth(), this.getRotation());
	}

}
