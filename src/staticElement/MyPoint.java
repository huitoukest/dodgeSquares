package staticElement;
/**
 * 
 * @author tingfeng
 * 代表的实际上就是坐标系中的一个点
 */
public class MyPoint {
	/**
	 * @param x 点x坐标
	 * @param y 点y坐标
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
	 * @return 返回此点到另外一点的距离
	 */
	public Double getDistanceFormPoint(MyPoint point)
	{
		return Math.sqrt(((x-point.getX())*(x-point.getX())+(y-point.getY())*(y-point.getY())));
	}

	/**
	 * 
	 * @param title
	 * 打印出改点的信息
	 */
	public void printPoint(String title) {
		System.out.println(title + ":X=" + x + "  Y=" + y);

	}
	public void printPoint() {
		printPoint("");
    }
}
