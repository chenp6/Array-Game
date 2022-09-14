package ArrayGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Cone extends JPanel{

	public Cone(boolean transparent){
		
		if(transparent)
			this.setBackground(new Color(1f,0f,0f,1f));
		
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void paint(Graphics g) {
		super.paint(g);  

		int width =getWidth();
		 int height = getHeight();
		
		int[] Xs = {
				0,
				(int)(width/2),
				width,
		};
		
		int[] Ys = {
				0,
				height,
				0
		};
		g.fillPolygon(Xs , Ys, 3);
		
	}
	


}


