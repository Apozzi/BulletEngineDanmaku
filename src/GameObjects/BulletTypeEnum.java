package GameObjects;

import Models.StageEnum;

public enum BulletTypeEnum {
	RED_DIRECTIONAL(0),
	YELLOW_CIRCLE(1);
	
	
	private final int code;
	
	private BulletTypeEnum(int code) {
		this.code = code;
	}

	public int getNumber() {
		return code;
	}
	
}
