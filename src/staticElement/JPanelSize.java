package staticElement;

public class JPanelSize {
	/**
	 * ��Ϸ�����С
	 * @param ScreenWidth ������
	 * @param ScreenHeigh ����߶�
	 */
	private static int ScreenWidth = 800;
	private static int ScreenHeight = 500;

	public JPanelSize() {// ���췽���ڻ�õ�ǰ��Ļ�ĳ��ӿ�

	}
    
	/**
	 * 
	 * @return ��Ļ���,Ҳ����X����
	 */
	public static int getScreenWidth() {
		return ScreenWidth;
	}

	public static void setScreenWidth(int screenWidth) {
		ScreenWidth = screenWidth;
	}

	/**
	 * 
	 * @return ��Ļ�߶�,Ҳ����Y����
	 */
	public static int getScreenHeight() {
		return ScreenHeight;
	}

	public static void setScreenHeight(int screenHeight) {
		ScreenHeight = screenHeight;
	}



}
