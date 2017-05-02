package Models;

import java.util.ArrayList;

import Game.Render;
import Utils.Draw;

public abstract class Stage {
	
	private int backgroundID = 0;
	private boolean inicialized = false;
	private static ArrayList<Stage> allStages = new ArrayList<>();
	private StageEnum stageCode;
	private long timer = 0;
	private static long timeAction = 0;
	
	private static long initialTime = 1;
	private static long updateTime = 1;
	
	public Stage(StageEnum stageCode){
		allStages.add(this);
		this.stageCode = stageCode;
	}

	public static Stage getStage(StageEnum stageEnum) {
		for (Stage stage : allStages) {
			if (stage.stageCode.equals(stageEnum)) {
				return stage;
			}
		}
		return null;
	}
	
	public boolean isInicialized() {
		return inicialized;
	}

	public long getTimer() {
		return timer;
	}

	public abstract void init();
	public void update() {
		initialTime = System.nanoTime();
		if (!inicialized) {
			init();
		}
		for (Stage stage : allStages) {
			if (stage.isInicialized() && !stage.equals(this)) {
			  try {
				throw new Exception("Nao é possivel inicializar um Stage sem sair dele!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.inicialized = true;
		timer++;
		if (backgroundID != 0) {
			Draw.drawImage(backgroundID, 0, 0, Render.getScreenX(), Render.getScreenY(), 0);
		}
		setTimeAction(0);
		script();
		System.out.println(getUpdateTime());
		updateTime = System.nanoTime() - initialTime;
	}
	public abstract void script();
	
	public static long getUpdateTime() {
		return updateTime;
	}

	public int getBackgroundID() {
		return backgroundID;
	}

	public void setBackgroundID(int backgroundID) {
		this.backgroundID = backgroundID;
	}

	public static long getTimeAction() {
		return timeAction;
	}

	public static void setTimeAction(long timeAction) {
		Stage.timeAction = timeAction;
	}
	
}
