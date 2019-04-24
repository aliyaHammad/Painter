package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Line extends Shape {
	
	private Point startLine, endLine;
	
	//Contructors
	
	Line () {}
    
	Line (Point start, Point end) {
		
		startLine = start;
		endLine   = end;
	}
	
	Line (Point start, Point end, Color color) {
		
		startLine = start;
		endLine   = end;
		
		shapeColor = color;		
	}
	
	//Draw method
	
    public void draw(Graphics g){
		
		g.setColor(shapeColor);
        g.drawLine(startLine.x,startLine.y, endLine.x, endLine.y);
	}	
	
		
	public  void clear(Graphics g){
		System.out.println("korkor");
		g.setColor(Color.white);
		draw(g);
	}
}