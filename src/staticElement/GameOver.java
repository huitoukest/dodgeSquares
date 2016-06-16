package staticElement;

import java.awt.Graphics;

public class GameOver {
/**
 * 实现游戏完毕显示,成绩统计,再来一盘?结束等等功能.
 * 
 */
	public GameOver(Graphics g)
	{
		g.drawString("Game Over!",(int) (JPanelSize.getScreenWidth()/1.9), (int)(JPanelSize.getScreenHeight()/1.9));
	}
	
	
}
