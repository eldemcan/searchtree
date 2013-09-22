package Data;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class Node {

	public int node_id;
	public float heuristics=0;
	public float pathLengthFromRoot=0;
	public float totalcost=0;
	public float astarcost=0;
	public boolean is_discovered=false;
	public Node root_node;
	public ArrayList<Node> visible_nodes = new ArrayList<Node>();

	public Node() {
		super();		
	}

	public Node(int node_id){
		this.node_id=node_id;

	}

	public void addAdjacentNode(Node node){
		visible_nodes.add(node);
	}
	
	public float getDistance(int start,int end){
		pathLengthFromRoot=(float) data.distances[start][end];
		
		return pathLengthFromRoot;
	}

	@Override
	public String toString() {

		String message="node id:"+(node_id+1);
 
		return message;
	}

}
