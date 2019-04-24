package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Rect extends Shape {
	
	private Point startRect, endRect;
	
	private int rectWidth, rectHeight; 
	
	//Contructors
	
	Rect () {}
    
	Rect (Point start, Point end) {
		
		startRect = start;
		endRect   = end;
		
	    rectWidth  = Math.abs(endRect.x - startRect.x);
	    rectHeight = Math.abs(endRect.y - startRect.y);
		
	}
	
	Rect (Point start, Point end, boolean solidState, Color color) {
		
		startRect = start;
		endRect   = end;
		
	    rectWidth  = Math.abs(endRect.x - startRect.x);
	    rectHeight = Math.abs(endRect.y - startRect.y);
		
		solid = solidState;
		
		shapeColor = color;
		
	}
	
	//Draw method
	
    public void draw(Graphics g){
		
		int x, y;
		
		g.setColor(shapeColor);

		if(startRect.x > endRect.x)
			x = endRect.x;     //if the rectangle is being drawn towards the left of the applet
		else
			x = startRect.x;   //if the rectangle is being drawn towards the right of the applet
		
	    if(startRect.y > endRect.y)
		    y = endRect.y;    //if the rectangle is being drawn towards the top of the applet
		else
			y = startRect.y; //if the rectangle is being drawn towards the bottom of the applet
		
		if(isSolid())
	       g.fillRect(x,y, rectWidth, rectHeight);
	    else
		   g.drawRect(x,y, rectWidth, rectHeight);
	}	
	
	public void clear(Graphics g){
		System.out.println("korkor");
		g.setColor(Color.white);
		draw(g);
	}
}