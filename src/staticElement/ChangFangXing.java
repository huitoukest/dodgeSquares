package staticElement;

import java.awt.Color;
import java.awt.Graphics;

import finalElement.RunMode;

public class ChangFangXing extends BasicGraphics {
	
	boolean isFirstSetAllVertex;

	public ChangFangXing(int x, int y, Color color, float bili, int runMode,int moveDirection) {

		// 根据中心点坐标以及ScreenSize中的大小用一个比例算出A,B,C，D三点的坐标

		zhongxingPoint.setX(x);
		zhongxingPoint.setY(y);
		this.bili = bili;
		this.color = color;
		this.runMode = runMode;
		isFirstSetAllVertex=true;

	}
/**
 * 画图形
 */
	public void draw(Graphics g) {
		Color tempColor = g.getColor();
		g.setColor(Color.red);
		setALLVertex();
		if (runMode == RunMode.ComputerMode);
		this.AntiClockwiseMove(zhongxingPoint);
		int xPoints[]={allVertex.get(0).getX(),allVertex.get(1).getX(),allVertex.get(2).getX(),allVertex.get(3).getX()};
		int yPoints[]={allVertex.get(0).getY(),allVertex.get(1).getY(),allVertex.get(2).getY(),allVertex.get(3).getY()};
		g.fillRect(zhongxingPoint.getX()-50, zhongxingPoint.getY()-40, 100, 80); //4  4 分别为长 宽   0 0 x y 坐标
		g.setColor(tempColor);
	}

	public void setALLVertex() {
		
		
		if(isFirstSetAllVertex){
			allVertex.add(0,new MyPoint(zhongxingPoint.getX()-50,zhongxingPoint.getY()-40));
			allVertex.add(1,new MyPoint(zhongxingPoint.getX()+50,zhongxingPoint.getY()-40));
			allVertex.add(2,new MyPoint(zhongxingPoint.getX()-50,zhongxingPoint.getY()+40));
			allVertex.add(3,new MyPoint(zhongxingPoint.getX()+50,zhongxingPoint.getX()+40));
			 isFirstSetAllVertex=false;
		}
		

	}

	public Boolean isInGraphical(MyPoint myPoint) {
		if (allVertex.size() <= 0)
			setALLVertex();
		if(myPoint.getX()>=allVertex.get(0).getX() && myPoint.getX()<=allVertex.get(1).getX() && 
				myPoint.getY()>=allVertex.get(0).getY() && myPoint.getY()<=allVertex.get(3).getY())
		return true;
		else
		return false;
		
		
		
	}

	@Override
	public boolean isTouchBorder() {
		if(allVertex.size()<=0)
			setALLVertex();
		if((allVertex.get(0).getX()>0 && allVertex.get(1).getX()<JPanelSize.getScreenWidth())
				||(allVertex.get(0).getY()>0 && allVertex.get(3).getY()<JPanelSize.getScreenHeight()))
			return true;
		else
		return false;
	}

	public void setStepComputerMove() {
		if (StepComputerMoveX == 0)
			StepComputerMoveX = (int) (Math.random() + 0.5 + tempStepComputerMoveX);
		else if (allVertex.get(1).getX() + 1 >= JPanelSize.getScreenWidth())
			StepComputerMoveX = (int) (-(Math.random() + 0.5 + tempStepComputerMoveX));
		else if (allVertex.get(0).getX() - 1 <= 0)
			StepComputerMoveX = (int) (Math.random() + 0.5 + tempStepComputerMoveX);
		
		if (StepComputerMoveY == 0)
			StepComputerMoveY = (int) (Math.random() + 0.5 + tempStepComputerMoveY);
		else if(allVertex.get(0).getY() - 1 <= 0)
			StepComputerMoveY = (int) (Math.random() + 0.5 + tempStepComputerMoveY);
		else if(allVertex.get(2).getY() - 1 >= JPanelSize.getScreenHeight())
			StepComputerMoveY = (int) (Math.random() + 0.5 + tempStepComputerMoveY);
	}

}
