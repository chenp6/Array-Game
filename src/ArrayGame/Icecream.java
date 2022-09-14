package ArrayGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Icecream extends JPanel{
	public static int ICECREAM_RATIO = 4;
	
	public static String[] ICECREAM_NOTE = {"Do","Re","Mi","Fa","Sol"};
	
	
	public static Color[] ICECREAM_COLOR = {
			new Color(254,171,186),
			new Color(255,236,190),
			new Color(249,210,73),
			new Color(148,85,68),
			new Color(84,130,53),
	};
	 
	private Circle[] balls;
	private JPanel ballsPanel= new JPanel();
	private Cone cone = new Cone(false);
	public Icecream(int width) {
		this.setSize(width,width*Icecream.ICECREAM_RATIO);
		
		this.balls = new Circle[] {};
		this.setLayout(new GridLayout(2, 1, 0, 0));
	
		add(ballsPanel);
		add(cone);
		this.ballsPanel.setLayout(new GridLayout(3, 1, 0, 0));

	}
	public Icecream(int width,Circle[] balls){
		this(width);
		this.balls = balls;
	}

	@Override
	public void paint(Graphics g) {
		ballsPanel.removeAll();
		super.paint(g);

		int len = balls.length;
		this.ballsPanel.setLayout(new GridLayout(len, 1, 0, 0));
		
		for(int i=len-1;i>=0;i--) {
			ballsPanel.add(balls[i]);
		}
		Utils.repaintPanel(ballsPanel);
	}
	

	
	public void addBall(Circle ball){
		Circle[] newBalls = new Circle[this.balls.length+1];
		for(int i=0;i<balls.length;i++) {
			newBalls[i] = balls[i];
		}
		newBalls[balls.length] = ball;
		
		balls = newBalls;
		repaint();
	}
	
	
	public void addBalls(Circle[] balls){
		Circle[] newBalls = new Circle[balls.length+this.balls.length];
		for(int i=0;i<this.balls.length;i++) {
			newBalls[i] = this.balls[i];
		}
		
		for(int i=this.balls.length;i<newBalls.length;i++) {
			newBalls[i] = balls[i];
		}
		
		
		this.balls = newBalls;
		repaint();
	}
	
	
	
	public void clearBalls(){
		Circle[] newBalls = new Circle[] {};

		
		balls = newBalls;
		repaint();
	}
	
	
	
	

	public Circle[] getBalls() {
		return this.balls;
	}
}
