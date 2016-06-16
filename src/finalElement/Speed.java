package finalElement;

public class Speed {
	/**
	 * 
	 * @param speed 方块移动的速度
	 */
	private static int speed = 1;// 默认速度为1

	/**
	 * getSpeed() 得到当前速度
	 * @return speed
	 */
	public static int getSpeed() {
		return speed;
	}

	/**
	 * setSpeed() 设置当前速度
	 *
	 */
	public static void setSpeed(int s) {
		speed = s;
	}

}
