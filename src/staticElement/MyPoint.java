package staticElement;
/**
 * 
 * @author tingfeng
 * �����ʵ���Ͼ�������ϵ�е�һ����
 */
public class MyPoint {
	/**
	 * @param x ��x����
	 * @param y ��y����
	 */
	private int x = 0, y = 0;

	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public MyPoint() {

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	};

	/**
	 * 
	 * @param point
	 * @return ���ش˵㵽����һ��ľ���
	 */
	public Double getDistanceFormPoint(MyPoint point)
	{
		return Math.sqrt(((x-point.getX())*(x-point.getX())+(y-point.getY())*(y-point.getY())));
	}

	/**
	 * 
	 * @param title
	 * ��ӡ���ĵ����Ϣ
	 */
	public void printPoint(String title) {
		System.out.println(title + ":X=" + x + "  Y=" + y);

	}
	public void printPoint() {
		printPoint("");
    }
}
