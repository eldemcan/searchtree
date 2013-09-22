package GUI;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import Data.*;
import Methods.*;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralSearch.calls requested search methods from GUI 
 * author 120014670
 */
public class GeneralSearch  {

	Graph g = new Graph();
	int method_type;
	int start_point ;
	int end_point;
	JLabel labelcost,labeltime,labelpath,labelexplored;
	JTextArea output;



	/**
	 * Instantiates a new general search.
	 *
	 * @param method the method
	 * @param start the start
	 * @param end the end
	 * @param d the d
	 */
	GeneralSearch(int method,int start,int end,JLabel d ) {
		method_type=method;
		start_point=start;
		end_point=end;
	} // general search

	/**
	 * Instantiates a new general search.
	 *
	 * @param mthd the mthd
	 * @param start the start
	 * @param end the end
	 * @param lblCost the lbl cost
	 * @param lblPath the lbl path
	 * @param lblTime the lbl time
	 * @param textArea the text area
	 * @param lblExporedNodes the lbl expored nodes
	 */
	public GeneralSearch(int mthd, int start, int end, JLabel lblCost,
			JLabel lblPath, JLabel lblTime, JTextArea textArea,
			JLabel lblExporedNodes ) {

		method_type=mthd;
		start_point=start;
		end_point=end;
		labelpath=lblPath;
		labelcost=lblCost;
		labeltime=lblTime;
		labelexplored=lblExporedNodes;
		output=textArea;

	}

	/**
	 * Search.
	 */
	void Search(){

		if (method_type==0){ // breath first
			long startTime = System.currentTimeMillis(); //get starting time
			BreathFirst bf = new BreathFirst(g,output);
			bf.breadthFirstTraversal(start_point, end_point);
			labelpath.setText("Path:"+bf.path);
			labelexplored.setText("Explored:"+bf.explored.toString());
			long endTime = System.currentTimeMillis(); //get finishing time from system
			labeltime.setText("Time:"+(float)(endTime-startTime)*0.001);
			labelcost.setText("Cost:"+bf.explored.size());

		}// if

		else if (method_type==1){ // depth first
			long startTime = System.currentTimeMillis(); //get starting time
			DepthFirst df = new DepthFirst(g,output);
		    df.depthFirst(start_point, end_point);
			labelpath.setText("Path:"+df.path);
			labelexplored.setText("Explored:"+df.explored.toString());
			long endTime = System.currentTimeMillis(); //get finishing time from system
			labeltime.setText("Time:"+(float)(endTime-startTime)*0.001);
			labelcost.setText("Cost:"+df.explored.size());
		}// if

		else if (method_type==2){  // uniform cost 
			long startTime = System.currentTimeMillis(); //get starting time
			UniformCost uni = new UniformCost(g,output,labelcost);
			uni.uniformCostSearch(start_point, end_point);
			labelpath.setText("Path:"+uni.path);
			labelexplored.setText("Explored node number:"+uni.explored.size());
			long endTime = System.currentTimeMillis(); //get finishing time from system
			labeltime.setText("Time:"+(float)(endTime-startTime)*0.001);
		}

		else if (method_type==3){ // Greedy 
			long startTime = System.currentTimeMillis(); //get starting time
			GreedyBestFirst gbf = new GreedyBestFirst(g,output,labelcost);
			gbf.GreedySearch(start_point, end_point);
			labelpath.setText("Path:"+gbf.path);
			labelexplored.setText("Explored:"+gbf.explored.toString());
			long endTime = System.currentTimeMillis(); //get finishing time from system
			labeltime.setText("Time:"+(float)(endTime-startTime)*0.001);
		}

		else if (method_type==4){  // a star 
			long startTime = System.currentTimeMillis(); //get starting time
			AStar astar = new AStar(g,output,labelcost);
			astar.AStarSearch(start_point, end_point);
			labelpath.setText("Path:"+astar.path);
			labelexplored.setText("Explored node number:"+astar.explored.size());
			long endTime = System.currentTimeMillis(); //get finishing time from system
			labeltime.setText("Time:"+(float)(endTime-startTime)*0.001);
		}

	}// end of search method
}
