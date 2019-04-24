package painterpackage;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Painter extends Applet{
	
	private Point start = null, end= null, current=null; 
	
	private List<Point> pointsList = new ArrayList<>();
	
	private boolean isDragged = false, solid = false;
	
	private Shape draggedShape = null, eraserIndicator = null;
	
	private Color color = Color.black;


	public void init() {
		
		Graphics gr = getGraphics();
		
		//Add Cirlce button and define its functionality when clicked 
		
		Button circButton = new Button("Circle");
		
		circButton.addActionListener(new ActionListener (){
			
			public void actionPerformed(ActionEvent ev){
				
				Shape.setActiveShape('c');
			}
		});
		
		add(circButton);
		
		//Add Rectangle button and define its functionality when clicked 
		
		Button rectButton = new Button("Rectangle");
		
		rectButton.addActionListener(new ActionListener (){
			
			public void actionPerformed(ActionEvent ev){
				
				Shape.setActiveShape('r');
			}
		});
		
		add(rectButton);
	
		//Add Line button and define its functionality when clicked 
		
		Button lineButton = new Button("Line");
		
		lineButton.addActionListener(new ActionListener (){
			
			public void actionPerformed(ActionEvent ev){
				
				Shape.setActiveShape('l');
			}
		});
		
		add(lineButton);
		
		//Add Free Sketch button and define its functionality when clicked 
		
		Button freeSketchButton = new Button("Free Sketch");
		
		freeSketchButton.addActionListener(new ActionListener (){
			
			public void actionPerformed(ActionEvent ev){
				
				Shape.setActiveShape('f');
			}
		});
		
		add(freeSketchButton);
		
		//Add Eraser button and define its functionality when clicked 
	
		Button eraseButton = new Button("Eraser");
	
		eraseButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
			
				Shape.setActiveShape('e');
			}
		});
	
		add(eraseButton);
	
		//Add Undo button and define its functionality when clicked 
	
		Button undoButton = new Button("Undo");
	
		undoButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
				Shape.popShape(); //remove last element drawn
				repaint();
			}
		});
	
		add(undoButton);
		
		//Add Solid check box and define its functionality when clicked 
	
		Checkbox solidCheckBox = new Checkbox("Solid");
	
		solidCheckBox.addItemListener(new ItemListener (){
		
			public void itemStateChanged(ItemEvent e){

				if(e.getStateChange()==ItemEvent.SELECTED)
						solid = true;
					else
						solid = false;
			}
		});
	
		add(solidCheckBox);
		
		
	    //Add Colors
		
		//Add Red Color button and define its functionality when clicked 
	
		Button redButton = new Button("RED");
	
		redButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
			
				color = Color.red;
			}
		});
	
		add(redButton);
		
		//Add Green Color button and define its functionality when clicked 
	
		Button greenButton = new Button("Green");
	
		greenButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
			
				color = Color.green;
			}
		});
	
		add(greenButton);
		
		//Add Blue Color button and define its functionality when clicked 
	
		Button blueButton = new Button("Blue");
	
		blueButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
			
				color = Color.blue;
			}
		});
	
		add(blueButton);
		
		//Add black Color button and define its functionality when clicked 
	
		Button blackButton = new Button("Black");
	
		blackButton.addActionListener(new ActionListener (){
		
			public void actionPerformed(ActionEvent ev){
			
				color = Color.black;
			}
		});
	
		add(blackButton);
	
		//Add Mouse events
		
		this.addMouseListener(new MouseAdapter (){
			
			public void mousePressed(MouseEvent e){
				   start = e.getPoint(); //capture start point
			}
			
			public void mouseReleased(MouseEvent e){
				
				//user can erase by simply pressing and relesing the mouse, while other shapes are only drawn and saved if dragged then released
				
			    if(isDragged == true || Shape.getActiveShape() == 'e'){		

				   end = e.getPoint();	//capture end point
				   pointsList.add(end);
				   
				   Shape savedShape = createShape();
				   
				   Shape.addShape(savedShape); //create and add shape to be saved after releasing the mouse 
				   repaint();

			   
				   isDragged = false;
				   pointsList.clear();//end of free sketch
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter (){
			
			public void mouseDragged(MouseEvent e){ 	
				end = e.getPoint();
				pointsList.add(end);
				draggedShape = createShape(); //capture the dragged shape which is not saved and paint it temporarily
				
				if(Shape.getActiveShape() == 'e') //if eraser is active save every dragged shape 
					Shape.addShape(draggedShape);
				
				repaint();	


				isDragged = true; 
			}
		
		
		    public void mouseMoved(MouseEvent e){ 
				current = e.getPoint();				
				if(Shape.getActiveShape() == 'e'){ //capture the mose location to show a white rectangle that indicates eraser is active
					eraserIndicator = new Eraser(current);
					eraserIndicator.draw(gr);
					repaint();	
				}

			}
		});
		
	}
		public void paint(Graphics g) {
			
			if(start != null && end != null)
			{			
				for(int index=0; index<Shape.getShapesNumber();index++){
					   Shape.getShape(index).draw(g); //draw every shape that has ever drawn and saved
				}			
				
				if(isDragged == true)
					draggedShape.draw(g); //draw dragged shape only while dragging
				//When using undo, if this condition wasn't checked the last shape would be still be drawn even after being pooped out of the array
				//because it was still captured as the last dragged shape and drawn, thus it's necessary to check that only saved elements are drawn after dragging is done
			}
			
			//current point is the current locatin of the mouse when moved and there a white rect is drawn to indicate eraser is active
			if(eraserIndicator != null)
			{
				eraserIndicator.draw(g);
			}
	    }
		
		public void update(Graphics g) 
        {      
          paint(g); 
        } 
	
	
    public Shape createShape()
	{
		 Shape shape = null;
		 switch(Shape.getActiveShape()){
			 
			case 'r':
				shape = new Rect(start, end, solid, color);	
				break;
			
			case 'f':
				shape = new FreeSketch(pointsList, color);
                break;
			
			//the end point carries the current location of the mouse when dragging and thus is the start of a new white rect to be drawn to simulate the eraser
			case 'e':
				shape = new Eraser(end); 
                break;	
					
		    case 'c':
				shape = new Circ(start, end, solid, color);
                break;
			
			case 'l':
				shape = new Line(start, end, color);
                break;
		 }
		 
		 return shape;
	
    }
}