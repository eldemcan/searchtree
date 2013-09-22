/*
 * 
 */
package Data;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

import Methods.AStar;



public class Graph {

	public ArrayList<Node> all_nodes =new ArrayList<Node>();

	/**
	 * Instantiates a new graph.
	 */
	public Graph(){

		initializeAllNodes();

		Node temp_node=null;

		for(int i=0;i<data.distances.length;i++){
			temp_node=all_nodes.get(i); 			
			for(int j=0;j<data.visibility.length;j++){
				if(data.visibility[i][j]==1){
					Node temp_node_adj = all_nodes.get(j);   
					temp_node.addAdjacentNode(temp_node_adj); 
				}//if 
			}//for 			  
		}//for
	}//graph

	void initializeAllNodes(){

		for(int i=0;i<data.distances.length;i++){
			Node temp=new Node(i);
			all_nodes.add(temp);
		}
	}

	void showStructure(){

		for(int i=0;i<all_nodes.size();i++){
			System.out.println(all_nodes.get(i).toString());
		}
	}

	public void setHeuristics(int goalNode) {
		
		for (int i = 0; i <data.distances.length; i++) {
			all_nodes.get(i).heuristics=(float) data.distances[i][goalNode];
		}
	}
	
	public float getDistance(int start , int end ){		
		return (float)data.distances[start][end];
	}

}
