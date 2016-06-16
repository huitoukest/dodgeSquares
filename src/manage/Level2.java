package manage;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import finalElement.MoveDirection;
import finalElement.RunMode;
import staticElement.ChangFangXing;
import staticElement.JPanelSize;
import staticElement.WindowsDisplay;
/**
 * Level1 ��һ��
 * @param uSanJiaoXing �û�����������
 * @param cSanJiaoXing1 ���Կ��Ƶ�������1
 * @param cSanJiaoXing2 ���Կ��Ƶ�������2
 * @param sleepTime �߳�ʱ��
 * @param flages 1��ʾ��ͣ,2��ʾ��Ϸ,����ʾֹͣ
 */
public class Level2 extends Level {
	ChangFangXing uChangFangXing;
	ChangFangXing cChangFangXing1;
	ChangFangXing cChangFangXing2;

	Graphics g;
	int sleepTime = 40;
	int flages;

/**
 * Level1()���,ͼ��ʲô�ļ���
 * 
 */
	public Level2() {
		flages = 2;
		WindowsDisplay wd = new WindowsDisplay();
		JPanel jp2 = this;
		jp2.setSize(JPanelSize.getScreenWidth(),JPanelSize.getScreenHeight());
		wd.add(jp2);
		uChangFangXing = new ChangFangXing(500, 300, Color.BLACK, (float) 0.2,
				RunMode.UserMode,MoveDirection.Clockwise);
		setUserBasGraphics(uChangFangXing);
		cChangFangXing1 = new ChangFangXing(90, 90, Color.RED, (float) 0.1,
				RunMode.ComputerMode, MoveDirection.AntiClockwise);
		cChangFangXing2 = new ChangFangXing(200, 200, Color.RED, (float) 0.1,
				RunMode.ComputerMode, MoveDirection.AntiClockwise);

		computerBasicGraphics.add(0, cChangFangXing1);
		computerBasicGraphics.add(1, cChangFangXing2);
		this.setUserBasGraphics(uChangFangXing);
		wd.addKeyListener(this);
		// GameState.setGameState(GameState.GAMEPLAYING);

	}
/**
 * ����ͼ��
 */
	public void paint(Graphics g) {
		
		// this.g=g;
		// g.clearRect(0, 0, JPanelSize.getScreenWidth(),
		// JPanelSize.getScreenHeight());
		// this.setBackground(Color.BLUE);
		g.setColor(Color.cyan);
		g.fillRect(0, 0, JPanelSize.getScreenWidth(),
				JPanelSize.getScreenHeight());
		uChangFangXing.draw(g);
		cChangFangXing1.draw(g);
		cChangFangXing2.draw(g);
        super.draw(g);
	}

	
}// end Level1
