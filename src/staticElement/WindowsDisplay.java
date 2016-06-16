package staticElement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import finalElement.GameState;
/**
 * 
 *JFrame框架,面板等显示
 *
 */

public class WindowsDisplay extends JFrame implements ActionListener {
	// JFrame框架,面板等显示

	public WindowsDisplay() {
		setBackground(Color.WHITE);
		BorderLayout borderLayout=(BorderLayout) this.getLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		
		setBounds(30, 30, JPanelSize.getScreenWidth()+6,
			JPanelSize.getScreenHeight()+28);
	   // this.setLayout(new FlowLayout());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		//不能够随便改变窗体大小
		setResizable(false);
		setVisible(true);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				GameState.setGameState(GameState.GAMEOVER);
				System.exit(0);
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public WindowsDisplay getWindowsDisPlay() {
		return this;
	}

}
