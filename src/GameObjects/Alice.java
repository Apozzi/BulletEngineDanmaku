package GameObjects;

import java.awt.image.BufferedImage;

import Models.Boss;
import Models.Player;
import Utils.Draw;
import Utils.TextureLoader;

public class Alice extends Boss{
	
	static BufferedImage image;
	static int textureID;

	public Alice(float x, float y, float height, float width, float rotation) {
		super(x, y, height, width, rotation);
		if (image == null) {
			image = TextureLoader.loadImage("images/alice.png");
			textureID = TextureLoader.loadTexture(image, true);
			this.setCheckCollision(false);
		}
	}

	@Override
	public void render() {
		this.interact();
		this.renderLiveBar();
		this.drawArea();
		Draw.drawImage(textureID, this.getX(), this.getY(), this.getHeight(), this.getWidth(), this.getRotation());
	}

}
