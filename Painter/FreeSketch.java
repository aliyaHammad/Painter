package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class FreeSketch extends Shape {
	
	private List <Point> freeSketchList;
	
	//Contructors
	
	FreeSketch () {
		freeSketchList = new ArrayList<>();
	}
    
	FreeSketch (List <Point> pointsList, Color color) {
		
		freeSketchList = new ArrayList<>();
		//implement deep copying 
		for(int i=0; i<pointsList.size(); i++)
			freeSketchList.add(pointsList.get(i));
		
		shapeColor = color;
	}
	
	//Draw method
	
    public void draw(Graphics g){
		
		g.setColor(shapeColor);
		for(int i =0; i<freeSketchList.size()-1; i++)
            g.drawLine(freeSketchList.get(i).x, freeSketchList.get(i).y, freeSketchList.get(i+1).x, freeSketchList.get(i+1).y);
	}	
}