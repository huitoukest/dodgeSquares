package finalElement;

public class GameState {
	
	/**
	 * ������Ϸ��״̬,��������(�߳�����),��ͣ(�̹߳���),����(�����߳�)
	 * @param GAMEOVER��ʾ��Ϸ����������Ϊ1
	 * @param GAMEPLAYING��ʾ��Ϸ�����У�����Ϊ2
	 * @param GAMESUSPEND��ʾ��Ϸ��ͣ������Ϊ3
	 * @param gameState��ʾ��ǰ��Ϸ״̬
	 * 
	 */
	
	public final static int GAMEOVER=1;
	public final static int GAMEPLAYING=2;
	public final static int GAMESUSPEND=3;
    public static int gameState=2;
    
    /**
     * getGameState()����
     * �õ���ǰ��Ϸ״̬
     * @return gameState
     */
    
	public static int getGameState() {
		return gameState;
	}
	
	/**
	 * setGameState()����
	 * ������Ϸ״̬
	 * 
	 */
	
	public static void setGameState(int gameState) {
		GameState.gameState = gameState;
	}
    

}
