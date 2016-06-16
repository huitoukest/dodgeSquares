package manage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import finalElement.GameState;
import finalElement.RunMode;
import staticElement.BasicGraphics;
import staticElement.GameOver;
import staticElement.JPanelSize;
import staticElement.Jianche;
import staticElement.MyPoint;
import staticElement.WindowsDisplay;

/**
 * @param upPressed
 *            按下键盘上键
 * @param downPressed
 *            按下键盘下键
 * @param leftPressed
 *            按下键盘左键
 * @param rightPressed
 *            按下键盘右键
 * @param userBasGraphics
 *            设定用户操控的那个图形,在运行前必须执行
 * @param cpmputerBasicGraphics
 *            当前由电脑操控的图形
 * @param isMousePressed
 *            按下鼠标
 * @param mouseStart
 *            鼠标点位置
 * @param jianche
 *            检测类实例
 * @param thread
 *            线程
 * @param thread2
 *            线程
 * @param sleepTime
 *            刷新显示界面的间隔时间
 */
public abstract class Level extends JPanel implements KeyListener,
		MouseListener, MouseMotionListener {

	private Boolean upPressed, downPressed, leftPressed, rightPressed;
	private BasicGraphics userBasGraphics;
	private ArrayList<BasicGraphics> computerBasicGraphics;
	private Boolean isMousePressed;
	private MyPoint mouseStart;
	private Jianche jianche;
	private int sleepTime = 40;
	private Thread thread, thread2;
	private	int flages;
	private Color backGroundColor;

	/**
	 * Level() 关卡设置
	 */
	public Level() {
		upPressed = false;
		downPressed = false;
		leftPressed = false;
		rightPressed = false;
		isMousePressed = false;
		mouseStart = new MyPoint();
		computerBasicGraphics = new ArrayList<BasicGraphics>();

		// this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		MyTask1 mytask = new MyTask1();
		thread = new Thread(mytask);
		thread.start();// 刷新界面

		MyTask2 mytask2 = new MyTask2();
		thread2 = new Thread(mytask2);
		thread2.start();// 检测碰撞
		backGroundColor = Color.BLUE;
		setDisplayWindows();

	}

	/**
	 * 让子类重写此方法,来初始化需要加入面板的图形信息
	 */
	public abstract void init();

	/**
	 * paint()重画
	 */
	public void paint(Graphics g) {
		// 绘画出背景
		g.setColor(backGroundColor);
		g.fillRect(0, 0, JPanelSize.getScreenWidth(),
				JPanelSize.getScreenHeight());
		
		userBasGraphics.setAllVertex();
		g.setColor(userBasGraphics.getColor());
		userBasGraphics.draw(g);
		
		// 画出电脑控制的图形
		for (BasicGraphics bGraphics : computerBasicGraphics) {			
			
			bGraphics.AntiClockwiseMove(bGraphics.getZhongxingPoint());// AntiClockwiseMove逆时针
			bGraphics.setAllVertex();
			g.setColor(bGraphics.getColor());			
			bGraphics.draw(g);
		}

	}

	/**
	 * draw()画图
	 * 
	 */
	public void draw(Graphics g) {
		// 检测游戏状态
		// 画出相应状态下游戏中的画面（游戏中就不画，暂停和停止。。画出。。。）
		// 键盘监听，暂停，开始，
		// if(jianche!=null) 在游戏停止有重绘(检测两个，重绘一个)碰撞图形的位置
		if (GameState.gameState == GameState.GAMEOVER) {
			drawBack(g);
			new GameOver(g);
		}
	}

	/**
	 * 设置好显示的界面
	 */
	public void setDisplayWindows() {
		flages = 2;
		WindowsDisplay wd = new WindowsDisplay();
		JPanel jp1 = this;
		jp1.setSize(JPanelSize.getScreenWidth(), JPanelSize.getScreenHeight());
		wd.add(jp1);
		init();
		setComputerBasicGraphics();
		setUserBasicGraphics();
		wd.addKeyListener(this);
	}

	/**
	 * 设置电脑控制的移动图形
	 */
	public abstract void setComputerBasicGraphics();

	/**
	 * 设置用户控制的移动图形
	 */
	public abstract void setUserBasicGraphics();

	/**
	 * 
	 * drawBack() 画出相碰时的前一次
	 */
	private void drawBack(Graphics g) {
		if (jianche.isLastTwoGraphicsPengzhuang()) {
			// int tempx;
			// int tempy;

			// tempx = tempZX.getX();
			// tempy = tempZX.getY();
			MyPoint tempZX;
			BasicGraphics computerGraphicesPengzhuang;
			while (jianche.isLastTwoGraphicsPengzhuang()) {

				computerGraphicesPengzhuang = jianche
						.getComputerGraphicesPengzhuang();
				tempZX = computerGraphicesPengzhuang.getZhongxingPoint();

				computerGraphicesPengzhuang.setAllVertex();

				if (computerGraphicesPengzhuang.getStepComputerMoveX() > 0) {
					tempZX.setX(tempZX.getX() - 1);
					computerGraphicesPengzhuang.setZhongxingPoint(tempZX);
				} else {
					tempZX.setX(tempZX.getX() + 1);
					computerGraphicesPengzhuang.setZhongxingPoint(tempZX);
				}

				// tempx = tempZX.getX();
				if (jianche.isLastTwoGraphicsPengzhuang()) {
					if (computerGraphicesPengzhuang.getStepComputerMoveY() > 0) {
						tempZX.setY(tempZX.getY() - 1);
						computerGraphicesPengzhuang.setZhongxingPoint(tempZX);
					} else {
						tempZX.setY(tempZX.getY() + 1);
						computerGraphicesPengzhuang.setZhongxingPoint(tempZX);
					}
					// tempy = tempZX.getY();
				}
				// System.out.println("Level DrwaBack!");
			}// end while
			g.fillRect(0, 0, JPanelSize.getScreenWidth(),
					JPanelSize.getScreenHeight());
			// computerGraphicesPengzhuang.draw(g);
			userBasGraphics.draw(g);
			for (int k = 0; k < computerBasicGraphics.size(); k++) {
				computerBasicGraphics.get(k).draw(g);
			}

		}// end if
	}

	public BasicGraphics getUserBasGraphics() {
		return userBasGraphics;
	}

	public void setAllGraphics() {
		jianche = new Jianche(computerBasicGraphics, userBasGraphics);
	}

	public void setUserBasGraphics(BasicGraphics userBasGraphics) {
		this.userBasGraphics = userBasGraphics;
		setAllGraphics();
	}

	public ArrayList<BasicGraphics> getComputerBasicGraphics() {
		return computerBasicGraphics;
	}

	public void setComputerBasicGraphics(
			ArrayList<BasicGraphics> computerBasicGraphics) {
		this.computerBasicGraphics = computerBasicGraphics;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		isMousePressed = false;
		// mouseStart=new MyPoint(e.getX(), e.getY());

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (isMousePressed == false
				&& userBasGraphics
						.isInGraphical(new MyPoint(e.getX(), e.getY())))
			isMousePressed = true;
		if (isMousePressed && userBasGraphics.getRunMode() == RunMode.UserMode) {
			// MyPoint mouseEnd= new MyPoint(e.getX(), e.getY());
			MyPoint zhongXingPoint = userBasGraphics.getZhongxingPoint();
			zhongXingPoint.setX(zhongXingPoint.getX()
					+ (e.getX() - mouseStart.getX()));
			zhongXingPoint.setY(zhongXingPoint.getY()
					+ (e.getY() - mouseStart.getY()));
			// mouseEnd.printPoint("mouseend");
			// mouseStart.printPoint("moseStart");
			// zhongXingPoint.printPoint("zhongxingpoint");
			// userBasGraphics.setZhongxingPoint(zhongXingPoint);
			// sanJiaoXing.setZhongxingPoint(mouseEnd);
			// System.out.println("鼠标点击的坐标X:"+e.getX()+"  Y:"+e.getY());
			mouseStart = new MyPoint(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseStart = new MyPoint(e.getX(), e.getY());
		if (userBasGraphics.isInGraphical(mouseStart))
			// sanJiaoXing.setZhongxingPoint(zhongXingPoint);
			isMousePressed = true;
	}

	/**
	 * keyPressed() 键盘按下监听
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		}

		if (upPressed == true && downPressed == false)
			userBasGraphics.moveUp();
		if (downPressed == true && upPressed == false)
			userBasGraphics.moveDown();
		if (leftPressed == true && rightPressed == false)
			userBasGraphics.moveLeft();
		if (rightPressed == true && leftPressed == false)
			userBasGraphics.moveRight();
	}

	/**
	 * keyReleased() 键盘释放监听
	 */
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		}
	}

	/**
	 * MyTask1 线程1,线程1主要是用来重绘前台画面
	 * 
	 */
	class MyTask1 implements Runnable {

		@Override
		public void run() {

			// TODO Auto-generated method stub
			while ((GameState.gameState == GameState.GAMEPLAYING)
					|| (GameState.gameState == GameState.GAMESUSPEND)) {
				// System.out.println("flages"+flages);
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (GameState.GAMEPLAYING == GameState.getGameState())
					repaint();

				// System.out.println("2循环调用paint()方法");

			}
		}// end runnable
	}// end MyTask1

	/**
	 * MyTask2 线程2,线程2是用来检测是否碰撞
	 * 
	 */
	class MyTask2 implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while ((GameState.gameState == GameState.GAMEPLAYING)
					|| (GameState.gameState == GameState.GAMESUSPEND)) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (jianche != null && jianche.isPengzhuang()) {
					GameState.setGameState(GameState.GAMEOVER);
					repaint();
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Boolean getUpPressed() {
		return upPressed;
	}

	public void setUpPressed(Boolean upPressed) {
		this.upPressed = upPressed;
	}

	public Boolean getDownPressed() {
		return downPressed;
	}

	public void setDownPressed(Boolean downPressed) {
		this.downPressed = downPressed;
	}

	public Boolean getLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(Boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public Boolean getRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(Boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public Boolean getIsMousePressed() {
		return isMousePressed;
	}

	public void setIsMousePressed(Boolean isMousePressed) {
		this.isMousePressed = isMousePressed;
	}

	public MyPoint getMouseStart() {
		return mouseStart;
	}

	public void setMouseStart(MyPoint mouseStart) {
		this.mouseStart = mouseStart;
	}

	public Jianche getJianche() {
		return jianche;
	}

	public void setJianche(Jianche jianche) {
		this.jianche = jianche;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Thread getThread2() {
		return thread2;
	}

	public void setThread2(Thread thread2) {
		this.thread2 = thread2;
	}

	public int getFlages() {
		return flages;
	}

	public void setFlages(int flages) {
		this.flages = flages;
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

}
