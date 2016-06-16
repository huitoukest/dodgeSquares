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
 * Level1 第一关
 * 
 * @param uSanJiaoXing
 *            用户控制三角形
 * @param cSanJiaoXing1
 *            电脑控制``三角形1
 * @param cSanJiaoXing2
 *            电脑控制的三角形2
 * @param sleepTime
 *            线程时间
 * @param flages
 *            1表示暂停,2表示游戏,三表示停止
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
