
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;



public class Main
{
	JFrame frame;


	public GamePanel gamepanel;
	
	
	public static void main(String args[])
	{
		Main app = new Main();
		app.go();
	}
	void go()
	{
		frame = new JFrame("���ռ���ҵ�С����");
		frame.setSize(900,630);
		frame.setResizable(false); 
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		gamepanel = new GamePanel();
		frame.getContentPane().add(gamepanel);

		
		frame.setVisible(true);

	}

}
