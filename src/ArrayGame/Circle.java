package ArrayGame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Circle extends JPanel{
	private Color color;
	public Circle(Color color){
			this.color = color;
			this.setBackground(new Color(0f,0f,0f,0f));
	}

	public void paint(Graphics g) {
		super.paint(g);  

		int width = (int)(getWidth());
		int height = (int)(getHeight());
//		int centerX =(int)(getWidth()*0.25);
		g.setColor(color);
		g.fillOval(0, 0,width,height);		
	}
	
    @Override
    public boolean equals(Object o) {
    	
    	
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Circle)) {
            return false;
        }
    	
    	
    	if(this.color.equals(((Circle)o).color)) {
    		return true;
    	}
		return false; 
    
    }

    
    public Color getColor() {
		return color;
    }

}


