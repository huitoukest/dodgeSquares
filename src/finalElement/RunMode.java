package finalElement;

public class RunMode {
	
	/**
	 * ������ǰͼ���ǵ��Բٿػ����û��ٿ�
	 * @param ComputerModeΪ���ԣ�UserModeΪ�û�
	 */
	
	
public final static int ComputerMode=1;
public final static int UserMode=2;	
public static void  printTheMode(int runMode){
	if(runMode==2) System.out.println("UserMode");
	else if(runMode==1) System.out.println("ComputerMode");
	else System.out.println("errorMode");
}
}
