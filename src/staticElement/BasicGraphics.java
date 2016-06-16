package staticElement;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import finalElement.MoveDirection;
import finalElement.RunMode;
import finalElement.Speed;

public abstract class BasicGraphics {
	
	/**
	 * ͼ�λ�������
	 * @param zhongxingPoint ͼ�����ĵ�����,����˵�����ǵ���ͨ���˵���ȷ��λ�õ�
	 * @param color ����ͼ�ε���ɫ
	 * @param runMode Ĭ�ϵ��Բٿ�ģʽ
	 * @param moveDirection Ĭ���ƶ�����
	 * @param bili ��ͼ�õı���
	 * @param grahics ͼ�λ���
	 * @param tempStepComputerMoveX �����ƶ�ʱ��ÿһ���ķ��ȵľ���ֵ
	 * @param tempStepComputerMoveY ���Ը��ݻ�׼ֵ�����
	 * @param baseStepX �ƶ����ȵ�X��׼ֵ 
	 * @param baseStepY �ƶ����ȵ�Y��׼ֵ
	 * @param stepUserMoveX �û����Ƶ�ʱ��,�ƶ�X������
	 * @param stepUserMoveY �û����Ƶ�ʱ��,�ƶ�Y������
	 * @param allVertex ����ȷ��һ��ͼ�εĵ�,�������ĵ�����е�
	 * @param moveRandomRang ÿ�������߿�֮��,����ƶ��ı���,����ֵԽ��,�Ѷ�����������
	 * @param isFirstSetAllVertex �Ƿ��ǳ����������ж���
	 * @param R Բ�ΰ뾶
     * @param D ֱ��,Բ�ε����������εı߳�
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

	// int tempStepX,tempStepY;//���������ƶ��Ĳ����Ⱥ��ٶȵļ�������ƶ����롣

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
	 * BasicGraphics() ͼ��ʵ�ַ���
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
	 * isInGraphical() ���һ�����Ƿ���ͼ���ڲ�
	 */
	public abstract Boolean isInGraphical(MyPoint myPoint);
	
	/**
	 * ����ͼ�ε�һЩ��ʼ������
	 * @param color ��ͼ�ε���ɫ
	 * @param bili ��ͼ�������������ܵı���
	 * @param runMode ��ͼ�ε����з�ʽ 
	 * @see finalElemet.RunMode
	 * @param moveDirection ��ͼ�εĳ�ʼ���з��� 
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
	 * draw() ��ͼ
	 */

	public abstract void draw(Graphics g);
	/**
	 * isTouchBorder() ���ͼ���Ƿ�������Ļ��Ե(�߿�)
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
	 * ��ʼ�����еĶ���
	 */
	public abstract void initVertex();
	
	/**
	 * setVertex() ͨ�����ĵ�,�������������,��Ҫ�Ǹ����ิд
	 */

	protected abstract void setVertex();
	/**
	 * ����������õķ���,����Ҳ��ʵʱȷ�����ж���
	 */
	public final void setAllVertex(){
		if(isFirstSetAllVertex)
		{	initVertex();
		    isFirstSetAllVertex=false;
		}
		setVertex();
	}
	
	/**
	 * setStepComputerMove() ͨ��tempComputerMoveX��������ʵ��ʹ�õĲ���ʵ��
	 * ʵ���Ͼ�����ͼ�������߿�֮��,����������ƶ�����������
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
	 * @return �Ƿ��������ұ߱߿�
	 */
	public abstract Boolean isTouchRightScreen();
	/***
	 * 
	 * @return �Ƿ���������߱߿�
	 */
	public abstract Boolean isTouchLeftScreen();
	/***
	 * 
	 * @return �Ƿ��������ϱ߱߿�
	 */
	public abstract Boolean isTouchTopScreen();
	/***
	 * 
	 * @return �Ƿ��������±߱߿�
	 */
	public abstract Boolean isTouchBottomScreen();
	
	/**
	 * updateStep() �����ٶȼ���,���²�����
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
 * ClockwiseMove() �����˳ʱ��
 */
	public void ClockwiseMove(MyPoint p)
	{
		updateStep();
		p.setX(p.getX() + StepComputerMoveX);
		p.setY(p.getY() + StepComputerMoveY);
	}
	/**
	 * AntiClockwiseMove() �����˳ʱ��
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
 * @return ����ƶ��ı���,����ֵԽ��,�Ѷ�����������
 */
	public int getMoveRandomRang() {
		return moveRandomRang;
	}
   
	/**
	 * 
	 * @param moveRandomRang
	 *����ƶ��ı���,����ֵԽ��,�Ѷ�����������
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
