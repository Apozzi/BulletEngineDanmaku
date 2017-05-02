package Game;


public enum GameKeys {
	DIRECIONAL_CIMA(265),
	DIRECIONAL_BAIXO(264),
	DIRECIONAL_ESQUERDA(263),
	DIRECIONAL_DIREITA(262),
	TECLA_Z(90),
	NOTHING(0);
	
	private final int codeKey;
	
	private GameKeys(int code) {
		codeKey = code;
	}
	
	public boolean isKey(int code) {
		return codeKey == code;
	}

	public int getCodeKey() {
		return codeKey;
	}
	
	public static GameKeys getKeyByValue(int key) {
		for (GameKeys gameKey : GameKeys.values()) {
			  if (key == gameKey.getCodeKey()) {
				  return gameKey;
			  }
		}
		return GameKeys.NOTHING;
		
	}
	
}
