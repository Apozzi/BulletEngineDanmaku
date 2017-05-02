package Models;


public enum StageEnum {
	STAGE_1(1),
	STAGE_2(2),
	STAGE_3(3),
	STAGE_4(4);
	
	private final int codeKey;
	
	private StageEnum(int code) {
		codeKey = code;
	}
	
	public boolean isKey(int code) {
		return codeKey == code;
	}

	public int getCodeKey() {
		return codeKey;
	}
	
	public static StageEnum getKeyByValue(int key) {
		for (StageEnum gameKey : StageEnum.values()) {
			  if (key == gameKey.getCodeKey()) {
				  return gameKey;
			  }
		}
		return null;
		
	}
	
}
