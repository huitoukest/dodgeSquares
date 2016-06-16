package staticElement;

import java.util.ArrayList;

/**
 * ���ʵ��֮�����ײ���
 * ��⵽�û��ٿص�ͼ�κ�����ͼ����ײ,��ı�gameState,ͬʱ����GameOver
 * @param computerBasicGraphics ��̬����
 * @param userBasicGraphics �û�����ͼ��
 * @param computerGraphicesPengzhuang ����ͼ����ײ
 */
public class Jianche {
	ArrayList<BasicGraphics> computerBasicGraphics; 
	BasicGraphics userBasicGraphics;
	BasicGraphics computerGraphicesPengzhuang;

	/**
	 * Jianche() ����
	 */
	public Jianche(ArrayList<BasicGraphics> computerBasicGraphics,
			BasicGraphics userBasicGraphics) {
		this.computerBasicGraphics = computerBasicGraphics;
		this.userBasicGraphics = userBasicGraphics;	
	}

/**
 * �����Կ��Ƶ�ͼ���еĵ����û����Ƶ�ͼ�εĵ�Ƚ��Ƿ��ڶԷ���ͼ����ͼ����
 * @return true ��ʾ��ײ false ��ʾû����ײ
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
	 * isLastTwoGraphicsPengzhuang() �������ͼ����ײ����ô����true;
	 */
	public boolean isLastTwoGraphicsPengzhuang() {// �������ͼ����ײ����ô����true;
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
 * getComputerGraphicesPengzhuang() �õ�����ͼ����ײ
 * @return computerGraphicesPengzhuang
 */
	public BasicGraphics getComputerGraphicesPengzhuang() {
		return computerGraphicesPengzhuang;
	}
	/**
	 * 
	 * @param A ͼ��1
	 * @param B ͼ��2
	 * @return ����ͼ�������Բ,������ײ,����true
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
