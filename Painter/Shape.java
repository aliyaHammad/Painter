package painterpackage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


abstract class Shape{
	
	private static char activeShape = 'c'; //Set the default shape as a circle
	
	private static List <Shape> shapesList = new ArrayList <>();
	
	protected boolean solid;
	
	protected Color shapeColor;
	
	//Add and Delete shapes from shapeslist
	
	public static void addShape(Shape shape)
	{
		shapesList.add(shape);
	}
	
	public static void popShape()
	{
		int size = shapesList.size();
		if(size > 0)
			shapesList.remove(shapesList.size()-1);
	}
	
	public static Shape getShape(int index)
	{
	    return shapesList.get(index);
	}
	
	public static int getShapesNumber()
	{
	    return shapesList.size();
	}
	
	//Active shape getters and setters to check which shape button is clicked
	
	public static char getActiveShape()
	{
		return activeShape;
	}
	
	public static void setActiveShape(char shapeChar)
	{
	    activeShape = shapeChar;
	}
	
	//check if solid button is clicked
	
	public boolean isSolid()
	{
		return solid;
	}
		
	//absrract draw method to be overriden by all inherited shapes
	
	public abstract void draw(Graphics g);
	
	public abstract void clear(Graphics g);
	
}