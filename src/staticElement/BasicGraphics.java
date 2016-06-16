package staticElement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import finalElement.MoveDirection;
import finalElement.RunMode;
import finalElement.Speed;

public abstract class BasicGraphics {
	
	/**
	 * 图形基本属性
	 * @param zhongxingPoint 图形中心点坐标,或者说其他角点是通过此点来确定位置的
	 * @param color 所画图形的颜色
	 * @param runMode 默认电脑操控模式
	 * @param moveDirection 默认移动方向
	 * @param bili 画图用的比例
	 * @param grahics 图形画笔
	 * @param tempStepComputerMoveX 电脑移动时候每一步的幅度的绝对值
	 * @param tempStepComputerMoveY 可以根据基准值算出的
	 * @param baseStepX 移动幅度的X基准值 
	 * @param baseStepY 移动幅度的Y基准值
	 * @param stepUserMoveX 用户控制的时候,移动X步幅度
	 * @param stepUserMoveY 用户控制的时候,移动Y步幅度
	 * @param allVertex 保存确定一个图形的点,除了中心点的所有点
	 * @param moveRandomRang 每次碰触边框之后,随机移动的比例,比例值越大,难度理论上增大
	 * @param isFirstSetAllVertex 是否是初次设置所有顶点
	 * @param R 圆形半径
     * @param D 直径,圆形的外切正方形的边长
	 */

	MyPoint zhongxingPoint;
	Color color;
	int runMode = RunMode.ComputerMode;
	int moveDirection = MoveDirection.AntiClockwise;
	float bili = (float) 0.1;
	Graphics graphics;

	int tempStepComputerMoveX = 4; 
	int tempStepComputerMoveY = 3;
	int baseStepX = 2;
	int baseStepY = 1;

	// int tempStepX,tempStepY;//用来缓存移动的步幅度和速度的计算出的移动距离。

	int stepUserMoveX;
	int stepUserMoveY;
	int StepComputerMoveX;
	int StepComputerMoveY;

	ArrayList<MyPoint> allVertex;
	
	private int moveRandomRang;
	boolean isFirstSetAllVertex;
	
	Integer R=0;
	Integer D=0;
	/**
	 * BasicGraphics() 图形实现方法
	 */

	public BasicGraphics() {
		zhongxingPoint = new MyPoint();
		color = Color.BLACK;
		baseStepX = (int) (JPanelSize.getScreenWidth() * bili * 0.04 + 1);
		baseStepY = (int) (JPanelSize.getScreenHeight() * bili * 0.04 + 1);
		allVertex = new ArrayList<MyPoint>();

		stepUserMoveX = 0;
		stepUserMoveX = 0;

		StepComputerMoveX = 0;
		StepComputerMoveY = 0;
        
		moveRandomRang=1;
		isFirstSetAllVertex=true;
	};

	/**
	 * isInGraphical() 检测一个点是否在图形内部
	 */
	public abstract Boolean isInGraphical(MyPoint myPoint);
	
	/**
	 * 设置图形的一些初始化属性
	 * @param color 此图形的颜色
	 * @param bili 此图形相对于整个框架的比例
	 * @param runMode 此图形的运行方式 
	 * @see finalElemet.RunMode
	 * @param moveDirection 此图形的初始运行方向 
	 * @see finalElemet.MoveDirection
	 */
	public void setInitAttribute(Color color, float bili, int runMode,
			int moveDirection){
		this.bili = bili;
		this.color = color;
		this.runMode = runMode;
		this.moveDirection=moveDirection;
	}
	
	/**
	 * draw() 画图
	 */

	public abstract void draw(Graphics g);
	/**
	 * isTouchBorder() 检测图形是否触碰了屏幕边缘(边框)
	 */

	public boolean isTouchBorder(){
		if (allVertex.size() <= 0)
			setAllVertex();
		if(isTouchTopScreen())
			return true;
		if(isTouchBottomScreen())
			return true;
		if(isTouchRightScreen())
			return true;
		if(isTouchLeftScreen())
			return true;
		return false;
	}; 
	
	/**
	 * 初始化所有的顶点
	 */
	public abstract void initVertex();
	
	/**
	 * setVertex() 通过中心点,求出所有其它点,主要是给子类复写
	 */

	protected abstract void setVertex();
	/**
	 * 给其他类调用的方法,功能也是实时确定所有顶点
	 */
	public final void setAllVertex(){
		if(isFirstSetAllVertex)
		{	initVertex();
		    isFirstSetAllVertex=false;
		}
		setVertex();
	}
	
	/**
	 * setStepComputerMove() 通过tempComputerMoveX将电脑中实际使用的步幅实现
	 * 实际上就是在图形碰触边框之后,调整方向和移动步幅的问题
	 */

	public void setStepComputerMove()
	{
		if (isTouchRightScreen())
			StepComputerMoveX = (int) (-(Math.random()*getMoveRandomRang() + tempStepComputerMoveX));
		else if (isTouchLeftScreen())
			StepComputerMoveX = (int) (Math.random()*getMoveRandomRang() + tempStepComputerMoveX);
		else if (StepComputerMoveX == 0)
			StepComputerMoveX = (int) (Math.random()*getMoveRandomRang() + tempStepComputerMoveX);

		if (isTouchBottomScreen())
			StepComputerMoveY = (int) -(Math.random()*getMoveRandomRang() + tempStepComputerMoveY);
		else if (isTouchTopScreen())
			StepComputerMoveY = (int) (Math.random()*getMoveRandomRang() + tempStepComputerMoveY);
		else if (StepComputerMoveY == 0)
			StepComputerMoveY = (int) (Math.random()*getMoveRandomRang() + tempStepComputerMoveY);
	};
	
	/***
	 * 
	 * @return 是否碰触了右边边框
	 */
	public abstract Boolean isTouchRightScreen();
	/***
	 * 
	 * @return 是否碰触了左边边框
	 */
	public abstract Boolean isTouchLeftScreen();
	/***
	 * 
	 * @return 是否碰触了上边边框
	 */
	public abstract Boolean isTouchTopScreen();
	/***
	 * 
	 * @return 是否碰触了下边边框
	 */
	public abstract Boolean isTouchBottomScreen();
	
	/**
	 * updateStep() 更加速度级别,更新步幅度
	 */

	public void updateStep() {
		tempStepComputerMoveX = (int) (baseStepX * (1 + Speed.getSpeed() / 2.5));
		tempStepComputerMoveY = (int) (baseStepY * (1 + Speed.getSpeed() / 2.5));
		// System.out.println("tempStepComputerMoveY:"+tempStepComputerMoveY+"tempStepComputerMoveX:"+tempStepComputerMoveX);
		if (runMode == RunMode.UserMode) {
			stepUserMoveX = tempStepComputerMoveX * 2;
			stepUserMoveY = tempStepComputerMoveY * 2;
		}
		setStepComputerMove();
	}
/**
 * ClockwiseMove() 如果是顺时针
 */
	public void ClockwiseMove(MyPoint p)
	{
		updateStep();
		p.setX(p.getX() + StepComputerMoveX);
		p.setY(p.getY() + StepComputerMoveY);
	}
	/**
	 * AntiClockwiseMove() 如果是顺时针
	 */
	public void AntiClockwiseMove(MyPoint p)
	{
		updateStep();
		// p.printPoint("PX");
		// p.printPoint("PY");
		// System.out.println("stepcomputermoveY:"+StepComputerMoveY+"stepcomputermoveX:"+StepComputerMoveX);
		p.setX(p.getX() + StepComputerMoveX);
		p.setY(p.getY() + StepComputerMoveY);
	}

	public void setGraphcis(Graphics g) {
		this.graphics = g;

	}

	public int getRunMode() {
		return runMode;
	}

	public void setRunMode(int runMode) {
		this.runMode = runMode;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public MyPoint getZhongxingPoint() {
		return zhongxingPoint;
	}

	public void setZhongxingPoint(MyPoint zhongxingPoint) {
		this.zhongxingPoint = zhongxingPoint;
	}

	public int getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}

	public float getBili() {
		return bili;
	}

	public void setBili(float bili) {
		this.bili = bili;
	}

	public void moveLeft() {
		if (runMode == RunMode.UserMode) {
			updateStep();
			zhongxingPoint.setX(zhongxingPoint.getX() - stepUserMoveX);
		}
	}

	public void moveRight() {
		if (runMode == RunMode.UserMode) {
			updateStep();
			zhongxingPoint.setX(zhongxingPoint.getX() + stepUserMoveX);
		}

	}

	public void moveUp() { 
		if (runMode == RunMode.UserMode) {
			updateStep();
			zhongxingPoint.setY(zhongxingPoint.getY() - stepUserMoveX);
		}
	}

	public void moveDown() {
		if (runMode == RunMode.UserMode) {
			updateStep();
			zhongxingPoint.setY(zhongxingPoint.getY() + stepUserMoveX);
		}

	}

	public int getTempStepX() {
		return baseStepX;
	}

	public void setTempStepX(int tempStepX) {
		this.baseStepX = tempStepX;
	}

	public int getTempStepY() {
		return baseStepY;
	}

	public void setTempStepY(int tempStepY) {
		this.baseStepY = tempStepY;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public ArrayList<MyPoint> getAllVertex() {
		return allVertex;
	}

	public void setAllVertex(ArrayList<MyPoint> allVertex) {
		this.allVertex = allVertex;
	}

	public int getStepComputerMoveX() {
		return StepComputerMoveX;
	}

	public void setStepComputerMoveX(int stepComputerMoveX) {
		StepComputerMoveX = stepComputerMoveX;
	}

	public int getStepComputerMoveY() {
		return StepComputerMoveY;
	}

	public void setStepComputerMoveY(int stepComputerMoveY) {
		StepComputerMoveY = stepComputerMoveY;
	}

	public int getTempStepComputerMoveX() {
		return tempStepComputerMoveX;
	}

	public void setTempStepComputerMoveX(int tempStepComputerMoveX) {
		this.tempStepComputerMoveX = tempStepComputerMoveX;
	}

	public int getTempStepComputerMoveY() {
		return tempStepComputerMoveY;
	}

	public void setTempStepComputerMoveY(int tempStepComputerMoveY) {
		this.tempStepComputerMoveY = tempStepComputerMoveY;
	}

	public int getBaseStepX() {
		return baseStepX;
	}

	public void setBaseStepX(int baseStepX) {
		this.baseStepX = baseStepX;
	}

	public int getBaseStepY() {
		return baseStepY;
	}

	public void setBaseStepY(int baseStepY) {
		this.baseStepY = baseStepY;
	}

	public int getStepUserMoveX() {
		return stepUserMoveX;
	}

	public void setStepUserMoveX(int stepUserMoveX) {
		this.stepUserMoveX = stepUserMoveX;
	}

	public int getStepUserMoveY() {
		return stepUserMoveY;
	}

	public void setStepUserMoveY(int stepUserMoveY) {
		this.stepUserMoveY = stepUserMoveY;
	}
/**
 * 
 * @return 随机移动的比例,比例值越大,难度理论上增大
 */
	public int getMoveRandomRang() {
		return moveRandomRang;
	}
   
	/**
	 * 
	 * @param moveRandomRang
	 *随机移动的比例,比例值越大,难度理论上增大
	 */
	public void setMoveRandomRang(int moveRandomRang) {
		this.moveRandomRang = moveRandomRang;
	}

	public boolean isFirstSetAllVertex() {
		return isFirstSetAllVertex;
	}

	public void setFirstSetAllVertex(boolean isFirstSetAllVertex) {
		this.isFirstSetAllVertex = isFirstSetAllVertex;
	}

	public Integer getR() {
		return R;
	}

	public void setR(Integer r) {
		R = r;
	}

	public Integer getD() {
		return D;
	}

	public void setD(Integer d) {
		D = d;
	}

}
