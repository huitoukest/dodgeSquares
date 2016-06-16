package finalElement;

public class RunMode {
	
	/**
	 * 决定当前图形是电脑操控还是用户操控
	 * @param ComputerMode为电脑，UserMode为用户
	 */
	
	
public final static int ComputerMode=1;
public final static int UserMode=2;	
public static void  printTheMode(int runMode){
	if(runMode==2) System.out.println("UserMode");
	else if(runMode==1) System.out.println("ComputerMode");
	else System.out.println("errorMode");
}
}
