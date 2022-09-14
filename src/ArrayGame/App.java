/**
 * @author chenp6
 *
 */
package ArrayGame;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;

public class App {

	static public JFrame frame;
	static Icecream[] icecreams ;
	static int progressingIndex = 0;
	static int[][] orders;
	static boolean pass = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		 Home();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		App.frame = frame;
		

		App.frame.setBounds(100, 100,687,500 );

		App.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	static public void Home() {
		Utils.removePanels();
		JLabel ruleImg = new JLabel();
		
		App.frame.getContentPane().add(ruleImg,BorderLayout.CENTER);

		//只是需要相對位址(Utils)
		ruleImg.setIcon(new ImageIcon(new Utils().getClass().getClassLoader().getResource("img/arrayHome.png")));
		
		JPanel btnContainer = new JPanel();
		btnContainer.setLayout(new GridLayout(0, 2,50, 0));

		JButton play = new JButton("開始遊玩");
		btnContainer.add(play);
		JButton rule = new JButton("如何遊玩");
		btnContainer.add(rule);
		App.frame.getContentPane().add(btnContainer,BorderLayout.SOUTH);

		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();
				App.startGame();
			}
		});
		
		rule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();
				App.Rule1();
			}
		});
		Utils.repaintPanels();

	}
	
	
	
	
	
	static public void startGame() {

		Utils.removePanels();
		JPanel upperPanel = new JPanel();
		App.frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		JTextField timer = new JTextField();
		timer.setText(0 +" sec");
		timer.setEditable(false);
		timer.setColumns(10);
		upperPanel.add(timer);

		TimerThread timerThread = new TimerThread(timer);
		timerThread.start();
		JPanel centerPanel = new JPanel();
		App.frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		

		centerPanel.setLayout(new GridLayout(0, 1, 100, 0));
						JPanel machinePanel = new JPanel();
						machinePanel.setLayout(new GridLayout(2, 0, 0, 0));
						centerPanel.add(machinePanel);
						
						JPanel orderPanel = new JPanel();
						machinePanel.add(orderPanel);
						orderPanel.setLayout(new BorderLayout(0, 0));
						
						JPanel orderInfo = new JPanel();
						orderPanel.add(orderInfo, BorderLayout.CENTER);
						orderInfo.setLayout(new GridLayout(0, 6, 30, 0));
						
						
						
						orders = Utils.makeOrders(5);

						
						icecreams = Utils.makeIcecreams(5);
						for(int i=0;i<icecreams.length;i++) {
							orderInfo.add(icecreams[i]);
						}
						
						
						
						
						JPanel panel = new JPanel();
						orderInfo.add(panel);
						panel.setLayout(null);
						JButton testingBtn = new JButton("聆聽訂單");
						testingBtn.setBounds(1, 41, 85, 133);
						panel.add(testingBtn);

						

						
						JPanel flavorPanel = new JPanel();
						machinePanel.add(flavorPanel);
						flavorPanel.setLayout(new GridLayout(2, 3, 0, 0));
						
						
						for(int i=0;i<5;i++) {
							Utils.makeFlavor(flavorPanel, i);
						}

						JButton comfirm =  new JButton("出餐");
						comfirm.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Circle[] balls = icecreams[progressingIndex].getBalls();
								int[] order = orders[progressingIndex];
								if(balls.length!=order.length) {
									 icecreams[progressingIndex].clearBalls();
								}else {
									boolean temp = true;
									for(int i=0;i<balls.length;i++) {
										if(!((balls[i].getColor()).equals(Icecream.ICECREAM_COLOR[order[i]]))) {
											//order與冰淇淋上的ball比較
											temp = false;
											 icecreams[progressingIndex].clearBalls();
											break;
										}
									}
									if(temp) {
										if(progressingIndex==4) {
											App.pass = true;
										}
										progressingIndex++;
									}
								}
								Utils.repaintPanels();
							}						
						});
						flavorPanel.add(comfirm);
						
						testingBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Utils.playOrder(orders[progressingIndex]);
							}
						});

						Utils.repaintPanels();


	}

	
	static public void Example() {

		Utils.removePanels();
		JPanel upperPanel = new JPanel();
		App.frame.getContentPane().add(upperPanel, BorderLayout.NORTH);
		JLabel timer = new JLabel("此為練習範例不計時");
		timer.setBackground(null);
		timer.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		upperPanel.add(timer);

		JPanel centerPanel = new JPanel();
		App.frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		

		centerPanel.setLayout(new GridLayout(0, 1, 100, 0));
						JPanel machinePanel = new JPanel();
						machinePanel.setLayout(new GridLayout(2, 0, 0, 0));
						centerPanel.add(machinePanel);
						
						JPanel orderPanel = new JPanel();
						machinePanel.add(orderPanel);
						orderPanel.setLayout(new BorderLayout(0, 0));
						
						JPanel orderInfo = new JPanel();
						orderPanel.add(orderInfo, BorderLayout.CENTER);
						orderInfo.setLayout(new GridLayout(0, 6, 30, 0));
						
						
					
						orders = new int[][]{{0,1,2,3,4}};

						icecreams = Utils.makeIcecreams(1);
						orderInfo.add(icecreams[0]);
						for(int i=0;i<4;i++) {
							orderInfo.add(new JLabel(""));
						}
						
						
						
						
						JPanel panel = new JPanel();
						orderInfo.add(panel);
						panel.setLayout(null);
						JButton testingBtn = new JButton("聆聽訂單");
						testingBtn.setBounds(1, 41, 85, 133);
						panel.add(testingBtn);

						

						
						JPanel flavorPanel = new JPanel();
						machinePanel.add(flavorPanel);
						flavorPanel.setLayout(new GridLayout(2, 3, 0, 0));
						
						
						for(int i=0;i<5;i++) {
							Utils.makeFlavor(flavorPanel, i);
						}

						JButton comfirm =  new JButton("出餐");
						comfirm.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Circle[] balls = icecreams[progressingIndex].getBalls();
								int[] order = orders[progressingIndex];
								if(balls.length!=order.length) {
									 icecreams[progressingIndex].clearBalls();
								}else {
									boolean temp = true;
									for(int i=0;i<balls.length;i++) {
										if(!((balls[i].getColor()).equals(Icecream.ICECREAM_COLOR[i]))) {
											//order與冰淇淋上的ball比較
											temp = false;
											 icecreams[progressingIndex].clearBalls();
											break;
										}
									}
									if(temp) {
										Utils.removePanels();
										Utils.repaintPanels();
										startGame();
									}
								}
								Utils.repaintPanels();
							}						
						});
						flavorPanel.add(comfirm);
						
						testingBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Utils.playOrder(orders[progressingIndex]);
							}
						});

						Utils.repaintPanels();


	}

	static public void Rule1() {
		Utils.removePanels();
		JLabel ruleImg = new JLabel();
		
		App.frame.getContentPane().add(ruleImg,BorderLayout.CENTER);

		//只是需要相對位址(Utils)
		ruleImg.setIcon(new ImageIcon(new Utils().getClass().getClassLoader().getResource("img/arrayRule01.png")));
		
		JButton btn = new JButton("下一頁");

		App.frame.getContentPane().add(btn,BorderLayout.SOUTH);

		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();
				App.Rule2();
			}
		});
		Utils.repaintPanels();

	}
	
	
	
	static public void Rule2() {

		Utils.removePanels();
		JLabel ruleImg = new JLabel();
		
		App.frame.getContentPane().add(ruleImg,BorderLayout.CENTER);

		//只是需要相對位址(Utils)
		ruleImg.setIcon(new ImageIcon(new Utils().getClass().getClassLoader().getResource("img/arrayRule02.png")));
		
		JButton btn = new JButton("開始遊玩");

		App.frame.getContentPane().add(btn,BorderLayout.SOUTH);

		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();
				restartGame();
				App.Example();
			}
		});
		Utils.repaintPanels();

	}
	
	
	
	static public void passGame() {
		Utils.removePanels();
		JLabel passImg = new JLabel();
		
		App.frame.getContentPane().add(passImg,BorderLayout.CENTER);

		//只是需要相對位址(Utils)
		passImg.setIcon(new ImageIcon(new Utils().getClass().getClassLoader().getResource("img/pass.png")));
		
		JButton btn = new JButton("再玩一次");

		App.frame.getContentPane().add(btn,BorderLayout.SOUTH);

		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();
				restartGame();

				App.startGame();
			}
		});
		Utils.repaintPanels();

	}
	
	static public void failedGame() {
		Utils.removePanels();
		JLabel passImg = new JLabel();
		
		App.frame.getContentPane().add(passImg,BorderLayout.CENTER);

		//只是需要相對位址(Utils)
		passImg.setIcon(new ImageIcon(new Utils().getClass().getClassLoader().getResource("img/failed.png")));
		
		JButton btn = new JButton("再玩一次");

		App.frame.getContentPane().add(btn,BorderLayout.SOUTH);

		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				Utils.removePanels();
				Utils.repaintPanels();

				restartGame();
				App.startGame();
			}
		});
		Utils.repaintPanels();

	}
	
	
	private static void restartGame() {
		App.progressingIndex = 0;
		App.pass = false;
	}

	
}
