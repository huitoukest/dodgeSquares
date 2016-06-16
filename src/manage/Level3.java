package manage;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import finalElement.MoveDirection;
import finalElement.RunMode;
import staticElement.JPanelSize;
import staticElement.SanJiaoXing;
import staticElement.WindowsDisplay;
import staticElement.Yuan;

/**
 * 
 * @author tingfeng
 * 本类主要用来测试圆形图形的运行是否正常
 */
public class Level3 extends Level{
Yuan yuan;
Yuan yuan2;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		yuan=new Yuan(300, 400, 50, Color.RED,(float) 0.1, RunMode.ComputerMode, MoveDirection.AntiClockwise);
		yuan2=new Yuan(200, 200, 50, Color.BLACK,(float) 0.2, RunMode.UserMode, MoveDirection.AntiClockwise);
	}

	@Override
	public void setComputerBasicGraphics() {
		// TODO Auto-generated method stub
		this.getComputerBasicGraphics().add(0,yuan);
	}

	@Override
	public void setUserBasicGraphics() {
		// TODO Auto-generated method stub
		this.setUserBasGraphics(yuan2);
	}

}
