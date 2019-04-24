package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Eraser extends Shape {
	
	//private Point startErase;
	
	private Point start;
	private int width = 20, height = 20; //fixed for now, size choosing feature night be added
	
	
	Eraser(){}
	
	Eraser(Point start){
		this.start = start;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.white); //set color to white so that rect acts as a an araser
		g.fillRect(start.x, start.y, width, height);
	}	
}