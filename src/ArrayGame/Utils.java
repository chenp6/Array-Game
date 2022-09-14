package ArrayGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Utils {

	static void makeFlavor(JPanel flavorPanel,int i) {
		JButton btn = new JButton(Icecream.ICECREAM_NOTE[i]);
		btn.setBackground(Icecream.ICECREAM_COLOR[i]);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.icecreams[App.progressingIndex].addBall(new Circle(Icecream.ICECREAM_COLOR[i]));
				int index = i%7;
				int group = 60+(int)(i/7)*12;
				MusicPlayer player = new MusicPlayer(group+MusicPlayer.NOTES[index]);
				player.start();
			}
		});
		flavorPanel.add(btn);
		Utils.repaintPanel(flavorPanel);
	}
	
	static Icecream[] makeIcecreams(int cnt) {
		Icecream[] icecreams = new Icecream[cnt];
		for(int i=0;i<cnt;i++) {
			icecreams[i] =  new Icecream(100);
		}
		return icecreams;
	}
	
	
	static int[][] makeOrders(int len) {
		int[][] orders = new int[len][];
		int[] ballCnt = setBallCount(len);
		
		for(int i=0;i<len;i++) {
			orders[i] = setFlavorIndex(ballCnt[i]);
		}
		return orders;
	}
		
	static private  int[] setBallCount(int len) {//len : 冰淇淋根數
		int[] ballCnt = new int[len];

		for(int i=0;i<ballCnt.length;i++) {
			Random rand = new Random();
				int num = (i+1)+rand.nextInt(2);
				ballCnt[i] = num;
		}
		return ballCnt;
	}
	
	
	static private  int[] setFlavorIndex(int ballCnt) { //設定該根冰淇淋的球數 //ballCnt:該根冰淇淋球數
		int[] balls = new int[ballCnt];
		Random rand = new Random();

		for(int i=0;i<ballCnt;i++) {
			int num = rand.nextInt(Icecream.ICECREAM_COLOR.length);

			balls[i] =num;
		}
		return balls;
	}
	

	
	static void playOrder(int[] list) {
		for(int i=0;i<list.length;i++) {
			int index = list[i]%7;
			int group = 60+(int)(list[i]/7)*12;
			MusicPlayer player = new MusicPlayer(group+MusicPlayer.NOTES[index]);
			player.start();	
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	static void removePanels() {
		int len = App.frame.getContentPane().getComponentCount();
		for(int i=0;i<len;i++)
			App.frame.getContentPane().remove(0);
	}
	
	static void repaintPanels() {
		App.frame.getContentPane().revalidate();
		App.frame.getContentPane().repaint();
	}
	
	
	static void repaintPanel(JPanel panel) {
		panel.revalidate();
		panel.repaint();
	}


}
