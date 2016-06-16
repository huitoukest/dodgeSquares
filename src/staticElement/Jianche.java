package staticElement;

import java.util.ArrayList;

/**
 * 活动的实例之间的碰撞检测
 * 检测到用户操控的图形和其他图形碰撞,则改变gameState,同时调用GameOver
 * @param computerBasicGraphics 动态数组
 * @param userBasicGraphics 用户基本图形
 * @param computerGraphicesPengzhuang 电脑图形碰撞
 */
public class Jianche {
	ArrayList<BasicGraphics> computerBasicGraphics; 
	BasicGraphics userBasicGraphics;
	BasicGraphics computerGraphicesPengzhuang;

	/**
	 * Jianche() 调用
	 */
	public Jianche(ArrayList<BasicGraphics> computerBasicGraphics,
			BasicGraphics userBasicGraphics) {
		this.computerBasicGraphics = computerBasicGraphics;
		this.userBasicGraphics = userBasicGraphics;	
	}

/**
 * 将电脑控制的图形中的点与用户控制的图形的点比较是否在对方的图形内图即可
 * @return true 表示碰撞 false 表示没有碰撞
 */
	public boolean isPengzhuang() {
		if (userBasicGraphics.isTouchBorder())
			return true;

		for (int i = 0; i < computerBasicGraphics.size(); i++) {
			BasicGraphics tempBasicGraphics = computerBasicGraphics.get(i);
			if(isTwoYuanPengzhuang(tempBasicGraphics,userBasicGraphics))
			return true;
			ArrayList<MyPoint> array = tempBasicGraphics.getAllVertex();
			for (int j = 0; j < array.size(); j++) { 
				if(userBasicGraphics.isInGraphical(array.get(j)))
				{ computerGraphicesPengzhuang=tempBasicGraphics;
					return true;
				}
				}
		}

		ArrayList<MyPoint> array = userBasicGraphics.getAllVertex();
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < computerBasicGraphics.size(); j++) {
				BasicGraphics tempBasicGraphics = computerBasicGraphics.get(j);
				if (tempBasicGraphics.isInGraphical(array.get(i)))
				{computerGraphicesPengzhuang=tempBasicGraphics;
					return true;
				}
				}
		}

		return false;
	}

	/**
	 * isLastTwoGraphicsPengzhuang() 如果两个图形碰撞，那么返回true;
	 */
	public boolean isLastTwoGraphicsPengzhuang() {// 如果两个图形碰撞，那么返回true;
		if (computerGraphicesPengzhuang == null
				|| userBasicGraphics.isTouchBorder())
			return false;
		ArrayList<MyPoint> array;
		BasicGraphics tempBasicGraphics;

		tempBasicGraphics = computerGraphicesPengzhuang;
		array = tempBasicGraphics.getAllVertex();
		for (int j = 0; j < array.size(); j++) {
			if (userBasicGraphics.isInGraphical(array.get(j)))
				return true;
		}

		array = userBasicGraphics.getAllVertex();
		for (int i = 0; i < array.size(); i++) {
			for (int j = 0; j < computerBasicGraphics.size(); j++) {
				tempBasicGraphics = computerGraphicesPengzhuang;
				if (tempBasicGraphics.isInGraphical(array.get(i)))
					return true;
			}
		}

		return false;
	}
	
/**
 * getComputerGraphicesPengzhuang() 得到电脑图形碰撞
 * @return computerGraphicesPengzhuang
 */
	public BasicGraphics getComputerGraphicesPengzhuang() {
		return computerGraphicesPengzhuang;
	}
	/**
	 * 
	 * @param A 图形1
	 * @param B 图形2
	 * @return 两个图形如果是圆,并且碰撞,返回true
	 */
	public Boolean isTwoYuanPengzhuang(BasicGraphics A,BasicGraphics B)
	{
		if(A.getAllVertex().size()==0||B.getAllVertex().size()==0)
		{
		Double distanceDouble=A.getZhongxingPoint().getDistanceFormPoint(B.getZhongxingPoint());
	    if(distanceDouble-(A.getD()+B.getD())/2<1)
			 return true;		
		}
	  return false;
	}

}
