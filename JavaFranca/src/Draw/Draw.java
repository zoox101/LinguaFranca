package Draw;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK);

		System.out.println("Hit");
		
		ArrayList<ArrayList<GraphObject>> allrows = new ArrayList<ArrayList<GraphObject>>();
		allrows.add(new ArrayList<GraphObject>()); allrows.get(0).add(startobject);
		
		for(int i=0; i<allrows.size(); i++) {
			//Creating the next row
			ArrayList<GraphObject> nextrow = new ArrayList<GraphObject>();
			//Adding the correct objects to the next row
			for(GraphObject object: allrows.get(i)) 
				for(SuperPointer pointer: object.out)
					if(!nextrow.contains(pointer.out) && !contains(allrows, pointer.out))
						nextrow.add(pointer.out);
			//If the row isn't empty, iterate again
			if(!nextrow.isEmpty()) allrows.add(nextrow);
		}
		
		int ydist = DISPLAY/allrows.size();
		for(int i=0; i<allrows.size(); i++) {
			int xdist = DISPLAY/allrows.get(i).size();
			for(int j=0; j<allrows.get(i).size(); j++)
				g.drawOval(ydist*i, xdist*j, 2*RADIUS, 2*RADIUS);
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
	
	//Checks if the graph object is contained in the recursive arraylist
	private boolean contains(ArrayList<ArrayList<GraphObject>> rows, GraphObject object) {
		for(ArrayList<GraphObject> row: rows)
			if(row.contains(object))
				return true;
		return false;
	}

}
