package staticElement;

import java.awt.Color;
import java.awt.Graphics;

import finalElement.RunMode;
/**
 * 
 * @author tingfeng
 * @param R 圆形半径
 * @param D 直径,圆形的外切正方形的边长
 * @param startPoint 圆形的外切正方形的左上角点
 * 圆形,需要注意的是这里的startPoint实际上并不是圆形的中心点
 * 而是圆形的外切正方形的左上角点
 * 
 */
public class Yuan extends BasicGraphics{

	
	MyPoint startPoint;
	public Yuan(MyPoint startPoint,Integer D)
	{
		this.startPoint=startPoint;
		this.D=D;
		init();
	}
	
	public Yuan(Integer X,Integer Y,Integer D,Color color, float bili, int runMode,
			int moveDirection)
	{
		this.startPoint=new MyPoint(X, Y);
		this.D=D;
		this.bili = bili;
		this.color = color;
		this.runMode = runMode;
		this.moveDirection=moveDirection;
		init();
	}
	
	/**
	 * 数据的初始化,主要是算出圆形的中心点和半径
	 */
	public void init()
	{
	   	R=D/2;
	   	zhongxingPoint.setX(startPoint.getX()+R);
	   	zhongxingPoint.setY(startPoint.getY()+R);
	}
	
	
	
	@Override
	public Boolean isInGraphical(MyPoint myPoint) {
		// TODO Auto-generated method stub
		if(zhongxingPoint.getDistanceFormPoint(myPoint)-R>0.01)
		return false;
		return true;
	}

	@Override
	public void draw(Graphics g) {
		//填充一个圆形
		g.fillOval(zhongxingPoint.getX()-R, zhongxingPoint.getY()-R, D, D);		
	}


	@Override
	public Boolean isTouchRightScreen() {
		if(zhongxingPoint.getX()+R>=JPanelSize.getScreenWidth())
		return true;
		return false;
		
	}

	@Override
	public Boolean isTouchLeftScreen() {
		if(zhongxingPoint.getX()-R<=0)
		return true;
		return false;
	}

	@Override
	public Boolean isTouchTopScreen() {
		if(zhongxingPoint.getY()-R<=0)
			return true;
		return false;
	}

	@Override
	public Boolean isTouchBottomScreen() {
		if(zhongxingPoint.getY()+R>=JPanelSize.getScreenHeight())
		return true;
		return false;
	}
	@Override
	public void initVertex() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void setVertex() {
		// TODO Auto-generated method stub
		
	}

}
