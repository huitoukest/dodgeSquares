package finalElement;

public class GameState {
	
	/**
	 * 设置游戏的状态,有运行中(线程运行),暂停(线程挂起),结束(销毁线程)
	 * @param GAMEOVER表示游戏结束，设置为1
	 * @param GAMEPLAYING表示游戏运行中，设置为2
	 * @param GAMESUSPEND表示游戏暂停，设置为3
	 * @param gameState表示当前游戏状态
	 * 
	 */
	
	public final static int GAMEOVER=1;
	public final static int GAMEPLAYING=2;
	public final static int GAMESUSPEND=3;
    public static int gameState=2;
    
    /**
     * getGameState()方法
     * 得到当前游戏状态
     * @return gameState
     */
    
	public static int getGameState() {
		return gameState;
	}
	
	/**
	 * setGameState()方法
	 * 设置游戏状态
	 * 
	 */
	
	public static void setGameState(int gameState) {
		GameState.gameState = gameState;
	}
    

}
