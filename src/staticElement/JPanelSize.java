package staticElement;

public class JPanelSize {
	/**
	 * 游戏界面大小
	 * @param ScreenWidth 界面宽度
	 * @param ScreenHeigh 界面高度
	 */
	private static int ScreenWidth = 800;
	private static int ScreenHeight = 500;

	public JPanelSize() {// 构造方法内获得当前屏幕的长河宽

	}
    
	/**
	 * 
	 * @return 屏幕宽度,也就是X坐标
	 */
	public static int getScreenWidth() {
		return ScreenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		ScreenWidth = screenWidth;
	}

	/**
	 * 
	 * @return 屏幕高度,也就是Y坐标
	 */
	public static int getScreenHeight() {
		return ScreenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		ScreenHeight = screenHeight;
	}



}
