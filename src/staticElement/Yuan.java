package staticElement;

import java.awt.Color;
import java.awt.Graphics;

import finalElement.RunMode;
/**
 * 
 * @author tingfeng
 * @param R Բ�ΰ뾶
 * @param D ֱ��,Բ�ε����������εı߳�
 * @param startPoint Բ�ε����������ε����Ͻǵ�
 * Բ��,��Ҫע����������startPointʵ���ϲ�����Բ�ε����ĵ�
 * ����Բ�ε����������ε����Ͻǵ�
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
	 * ���ݵĳ�ʼ��,��Ҫ�����Բ�ε����ĵ�Ͱ뾶
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
		//���һ��Բ��
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
