package Game;

import java.io.IOException;

import com.sun.glass.events.KeyEvent;

import Models.Stage;
import Stages.Stage1;

public class Game {
	private static Stage stage;
	
	public void render() {
		stage = new Stage1();
	}
	
	public void update() {
		stage.update();
	}
	
	public static void setGameActualStage(Stage stage) {
		Game.stage = stage;
		
	}
	
}
