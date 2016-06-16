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
 *            ���¼����ϼ�
 * @param downPressed
 *            ���¼����¼�
 * @param leftPressed
 *            ���¼������
 * @param rightPressed
 *            ���¼����Ҽ�
 * @param userBasGraphics
 *            �趨�û��ٿص��Ǹ�ͼ��,������ǰ����ִ��
 * @param cpmputerBasicGraphics
 *            ��ǰ�ɵ��Բٿص�ͼ��
 * @param isMousePressed
 *            �������
 * @param mouseStart
 *            ����λ��
 * @param jianche
 *            �����ʵ��
 * @param thread
 *            �߳�
 * @param thread2
 *            �߳�
 * @param sleepTime
 *            ˢ����ʾ����ļ��ʱ��
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
	 * Level() �ؿ�����
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
		thread.start();// ˢ�½���

		MyTask2 mytask2 = new MyTask2();
		thread2 = new Thread(mytask2);
		thread2.start();// �����ײ
		backGroundColor = Color.BLUE;
		setDisplayWindows();

	}

	/**
	 * ��������д�˷���,����ʼ����Ҫ��������ͼ����Ϣ
	 */
	public abstract void init();

	/**
	 * paint()�ػ�
	 */
	public void paint(Graphics g) {
		// �滭������
		g.setColor(backGroundColor);
		g.fillRect(0, 0, JPanelSize.getScreenWidth(),
				JPanelSize.getScreenHeight());
		
		userBasGraphics.setAllVertex();
		g.setColor(userBasGraphics.getColor());
		userBasGraphics.draw(g);
		
		// �������Կ��Ƶ�ͼ��
		for (BasicGraphics bGraphics : computerBasicGraphics) {			
			
			bGraphics.AntiClockwiseMove(bGraphics.getZhongxingPoint());// AntiClockwiseMove��ʱ��
			bGraphics.setAllVertex();
			g.setColor(bGraphics.getColor());			
			bGraphics.draw(g);
		}

	}

	/**
	 * draw()��ͼ
	 * 
	 */
	public void draw(Graphics g) {
		// �����Ϸ״̬
		// ������Ӧ״̬����Ϸ�еĻ��棨��Ϸ�оͲ�������ͣ��ֹͣ����������������
		// ���̼�������ͣ����ʼ��
		// if(jianche!=null) ����Ϸֹͣ���ػ�(����������ػ�һ��)��ײͼ�ε�λ��
		if (GameState.gameState == GameState.GAMEOVER) {
			drawBack(g);
			new GameOver(g);
		}
	}

	/**
	 * ���ú���ʾ�Ľ���
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
	 * ���õ��Կ��Ƶ��ƶ�ͼ��
	 */
	public abstract void setComputerBasicGraphics();

	/**
	 * �����û����Ƶ��ƶ�ͼ��
	 */
	public abstract void setUserBasicGraphics();

	/**
	 * 
	 * drawBack() ��������ʱ��ǰһ��
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
			// System.out.println("�����������X:"+e.getX()+"  Y:"+e.getY());
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
	 * keyPressed() ���̰��¼���
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
	 * keyReleased() �����ͷż���
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
	 * MyTask1 �߳�1,�߳�1��Ҫ�������ػ�ǰ̨����
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

				// System.out.println("2ѭ������paint()����");

			}
		}// end runnable
	}// end MyTask1

	/**
	 * MyTask2 �߳�2,�߳�2����������Ƿ���ײ
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
