package staticElement;

import java.awt.Color;
import java.awt.Graphics;

import finalElement.RunMode;

public class SanJiaoXing extends BasicGraphics {

	/**
	 * 该类实现三角形的属性
	 * @param  isFirstSetAllVertex 判断图形点数组是否为空
	 */
	float bili2;
	public SanJiaoXing(int x, int y, Color color, float bili, int runMode,
			int moveDirection) // moveDirection移动方向
	{// 根据中心点坐标以及ScreenSize中的大小用一个比例算出A,B,C三点的坐标
		zhongxingPoint.setX(x);
		zhongxingPoint.setY(y);
		this.bili = bili;
		this.color = color;
		this.runMode = runMode;
		isFirstSetAllVertex = true;
	}
	
	/**
	 * draw() 给Level调用，方便在JPanel中画图
	 * 
	 */
	public void draw(Graphics g) {
		
		int xPoints[] = { allVertex.get(0).getX(), allVertex.get(1).getX(),
				allVertex.get(2).getX() };
		int yPoints[] = { allVertex.get(0).getY(), allVertex.get(1).getY(),
				allVertex.get(2).getY() };		
		g.fillPolygon(xPoints, yPoints, 3);// 填充 Point 结构指定的点数组所定义的多边形的内部。


	}

	/**
	 * isInGraphical() 这个方法判断了一个点是否在三角形的内部
	 * @return true 表示点在三角形内部 false 表示没有在三角形内部
	 */
	public Boolean isInGraphical(MyPoint myPoint) {
		if (allVertex.size() <= 0)
			super.setAllVertex();
		if (myPoint.getX() < allVertex.get(1).getX()
				|| myPoint.getX() > allVertex.get(2).getX()
				|| myPoint.getY() > zhongxingPoint.getY()
				|| myPoint.getY() < allVertex.get(0).getY())
			return false;
		MyPoint tempA = new MyPoint(0, zhongxingPoint.getY()
				- allVertex.get(0).getY());// 以zhongxiangPoint点为原点,建立相对直角坐标系
		// MyPoint tempB=new
		// MyPoint(allVertex.get(1).getX()-zhongxingPoint.getX(), 0);
		MyPoint tempC = new MyPoint(allVertex.get(2).getX()
				- zhongxingPoint.getX(), 0);
		MyPoint tempmyPoint = new MyPoint(myPoint.getX()
				- zhongxingPoint.getX(), zhongxingPoint.getY() - myPoint.getY());

		if (tempmyPoint.getY() > tempA.getY() * 1.0 * tempmyPoint.getX()
				/ tempC.getX() + tempA.getY())
			return false;
		else if (tempmyPoint.getY() > -tempA.getY() * 1.0 * tempmyPoint.getX()
				/ tempC.getX() + tempA.getY())
			return false;
		return true;
	}
	
	@Override
	public void initVertex() {
		// TODO Auto-generated method stub
		// 三边符合5:5:6的比例
		int screenMax = JPanelSize.getScreenHeight() > JPanelSize
				.getScreenWidth() ? JPanelSize.getScreenWidth() : JPanelSize
				.getScreenHeight();
		bili2 = (screenMax * bili) / 6;// 取两者之间小的那个作为比例尺,防止图形过大,超出边界.
		
		if (zhongxingPoint.getX() - bili2 * 3 <= 0)
			zhongxingPoint.setX((int) (bili2 * 3 + 1));
		else if (zhongxingPoint.getX() + bili2 * 3 >= JPanelSize
				.getScreenWidth())
			zhongxingPoint
					.setX((int) (JPanelSize.getScreenWidth() - bili2 * 3 - 1));
		if (zhongxingPoint.getY() - bili2 * 4 <= 0)
			zhongxingPoint.setY((int) (bili2 * 4 + 1));
		else if (zhongxingPoint.getY() >= JPanelSize.getScreenHeight())
			zhongxingPoint.setY(JPanelSize.getScreenHeight() - 1);
		
		
		allVertex.add(0, new MyPoint(zhongxingPoint.getX(),
				(int) (zhongxingPoint.getY() - 4 * bili2)));
		allVertex.add(1,
				new MyPoint((int) (zhongxingPoint.getX() - 3 * bili2),
						zhongxingPoint.getY()));
		allVertex.add(2,
				new MyPoint((int) (zhongxingPoint.getX() + 3 * bili2),
						zhongxingPoint.getY()));
	}
	
	/**
	 * 
	 * setAllVertex() 求得顶点坐标
	 * 
	 * @see staticElement.BasicGraphics#setALLVertex()
	 */
	public void setVertex() {
		
		if (zhongxingPoint.getX() - bili2 * 3 <= 0)
			zhongxingPoint.setX((int) (bili2 * 3 + 1));
		else if (zhongxingPoint.getX() + bili2 * 3 >= JPanelSize
				.getScreenWidth())
			zhongxingPoint
					.setX((int) (JPanelSize.getScreenWidth() - bili2 * 3 - 1));
		if (zhongxingPoint.getY() - bili2 * 4 <= 0)
			zhongxingPoint.setY((int) (bili2 * 4 + 1));
		else if (zhongxingPoint.getY() >= JPanelSize.getScreenHeight())
			zhongxingPoint.setY(JPanelSize.getScreenHeight() - 1);
		
		    allVertex.set(0, new MyPoint(zhongxingPoint.getX(),
					(int) (zhongxingPoint.getY() - 4 * bili2)));
			allVertex.set(1,
					new MyPoint((int) (zhongxingPoint.getX() - 3 * bili2),
							zhongxingPoint.getY()));
			allVertex.set(2,
					new MyPoint((int) (zhongxingPoint.getX() + 3 * bili2),
							zhongxingPoint.getY()));
	}

	@Override
	public Boolean isTouchRightScreen() {

		if (allVertex.get(2).getX() + 1 >= JPanelSize.getScreenWidth())
			return true;
		return false;
	}

	@Override
	public Boolean isTouchLeftScreen() {
	if (allVertex.get(1).getX() - 1 <= 0)
			return true;
	return false;
	}

	@Override
	public Boolean isTouchTopScreen() {
		if (allVertex.get(0).getY() - 1 <= 0)
			return true;		
		return false;
	}

	@Override
	public Boolean isTouchBottomScreen() {
		if (zhongxingPoint.getY() + 1 >= JPanelSize.getScreenHeight())
			return true;
		return false;
	}


}
