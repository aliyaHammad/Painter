package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Circ extends Shape{
	
	private Point startCirc, endCirc;
	
	private int circWidth, circHeight; 
	
	//Contructors
	
	Circ () {}
    
	Circ (Point start, Point end) {
		
		startCirc = start;
		endCirc   = end;
		
	    circWidth  = Math.abs(endCirc.x - startCirc.x);
	    circHeight = Math.abs(endCirc.y - startCirc.y);
		
	}
	
	Circ (Point start, Point end, boolean solidState, Color color) {
		
		startCirc = start;
		endCirc   = end;
		
	    circWidth  = Math.abs(endCirc.x - startCirc.x);
	    circHeight = Math.abs(endCirc.y - startCirc.y);

		solid = solidState;
		
		shapeColor = color;
		
	}
	
	public void draw(Graphics g){
		
		int x, y;
		
		g.setColor(shapeColor);

		if(startCirc.x > endCirc.x)
			x = endCirc.x;     //if the circle is being drawn towards the left of the applet
		else
			x = startCirc.x;   //if the circle is being drawn towards the right of the applet
		
	    if(startCirc.y > endCirc.y)
		    y = endCirc.y;    //if the circle is being drawn towards the top of the applet
		else
			y = startCirc.y; //if the circle is being drawn towards the bottom of the applet
		
		if(isSolid())
	       g.fillArc(x,y, circWidth, circHeight, 180, 360);
	    else
		   g.drawArc(x,y, circWidth, circHeight, 180, 360);
		
        
	}	
		
		
	public void clear(Graphics g){
		shapeColor = Color.white;
		draw(g);
	}
}