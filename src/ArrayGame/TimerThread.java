package ArrayGame;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TimerThread extends Thread{
	JTextField text;
	private int sec = 0;
	TimerThread(JTextField text){
		super();
		this.text = text;
	}
	public void run() {
		while(!App.pass&&sec<=59) {
			try {
				sleep(1000);
				sec++;
				text.setText(sec+" sec");
				text.repaint();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(App.pass) {
			 App.passGame();
		}else {
			App.failedGame();
		}
	}
}
