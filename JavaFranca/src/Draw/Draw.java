package Draw;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import GraphStructure.GraphObject;
import GraphStructure.Relation;
import GraphStructure.SuperPointer;

public class Draw extends JPanel {
	private static final long serialVersionUID = 2786164773919222989L;
	
	public static int DISPLAY = 1000;
	public static int RADIUS = 10;
	
	public GraphObject startobject;
	
	public Draw(GraphObject object) {
		
		super();
		JFrame frame = new JFrame();
		frame.add(this); frame.setVisible(true); frame.setSize(DISPLAY, DISPLAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.startobject = object;
		
	}
	
protected void paintComponent(Graphics g, Relation relation, int rows) {
		
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		ArrayList<GraphObject> currentrow = new ArrayList<GraphObject>();
		ArrayList<GraphObject> hit = new ArrayList<GraphObject>();
		currentrow.add(startobject); hit.add(startobject);
		
		for(int i=0; i<rows; i++) {
			
			ArrayList<GraphObject> nextrow = new ArrayList<GraphObject>();
			
			for(GraphObject object: currentrow) 
				for(SuperPointer pointer: object.out)
					if(!nextrow.contains(pointer.out) && !hit.contains(pointer.out)) {
						nextrow.add(pointer.out);
						hit.add(pointer.out);
					}
			
			int size = currentrow.size();
			
			
		}
		
		
		/*
		if(graph != null) {
			for(int i=0; i<graph.size(); i++) {
				Point point = graph.get(i);
				g.drawOval((int)(point.x*SCALE), (int)(point.y*SCALE), 2*RADIUS, 2*RADIUS);
				g.drawString(String.valueOf(i), (int)(point.x*SCALE), (int)(point.y*SCALE));
			}
		}
		
		if(path != null && graph != null) {
			for(int i=0; i<path.size(); i++) {
				Point p1 = graph.get(path.get(i));
				Point p2 = graph.get(path.get((i+1)%graph.size()));
				g.drawLine((int)(p1.x*SCALE)+RADIUS, (int)(p1.y*SCALE)+RADIUS, (int)(p2.x*SCALE)+RADIUS, (int)(p2.y*SCALE)+RADIUS);
			}
		}
		
		if(search != null) {
			g.setColor(Color.BLUE);
			for(int i=0; i<search.matrix.size(); i++) {
				Point p1 = graph.get(i);
				Point p2 = graph.get(search.matrix.get(i).get(0));
				g.drawLine((int)(p1.x*SCALE)+RADIUS, (int)(p1.y*SCALE)+RADIUS, (int)(p2.x*SCALE)+RADIUS, (int)(p2.y*SCALE)+RADIUS);
			}
			
		}
		*/
}

}
