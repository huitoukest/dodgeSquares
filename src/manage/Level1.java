package manage;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import finalElement.MoveDirection;
import finalElement.RunMode;
import staticElement.SanJiaoXing;
import staticElement.JPanelSize;
import staticElement.WindowsDisplay;

/**
 * Level1 ��һ��
 * 
 * @param uSanJiaoXing
 *            �û�����������
 * @param cSanJiaoXing1
 *            ���Կ���``������1
 * @param cSanJiaoXing2
 *            ���Կ��Ƶ�������2
 * @param sleepTime
 *            �߳�ʱ��
 * @param flages
 *            1��ʾ��ͣ,2��ʾ��Ϸ,����ʾֹͣ
 */
public class Level1 extends Level {
	SanJiaoXing uSanJiaoXing;
	SanJiaoXing cSanJiaoXing1;
	SanJiaoXing cSanJiaoXing2;

	@Override
	public void init() {
		uSanJiaoXing = new SanJiaoXing(500, 300, Color.BLACK, (float) 0.2,
				RunMode.UserMode, MoveDirection.Clockwise);
		setUserBasGraphics(uSanJiaoXing);
		cSanJiaoXing1 = new SanJiaoXing(90, 90, Color.RED, (float) 0.1,
				RunMode.ComputerMode, MoveDirection.AntiClockwise);
		cSanJiaoXing2 = new SanJiaoXing(200, 200, Color.RED, (float) 0.1,
				RunMode.ComputerMode, MoveDirection.AntiClockwise);
	}

	@Override
	public void setComputerBasicGraphics() {
		// TODO Auto-generated method stub
		this.getComputerBasicGraphics().add(0, cSanJiaoXing1);
		this.getComputerBasicGraphics().add(1, cSanJiaoXing2);
	}

	@Override
	public void setUserBasicGraphics() {
		// TODO Auto-generated method stub
		this.setUserBasGraphics(uSanJiaoXing);
	}

}// end Level1
