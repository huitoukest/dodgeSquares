package staticElement;

import java.awt.Graphics;

public class GameOver {
/**
 * ʵ����Ϸ�����ʾ,�ɼ�ͳ��,����һ��?�����ȵȹ���.
 * 
 */
	public GameOver(Graphics g)
	{
		g.drawString("Game Over!",(int) (JPanelSize.getScreenWidth()/1.9), (int)(JPanelSize.getScreenHeight()/1.9));
	}
	
	
}
