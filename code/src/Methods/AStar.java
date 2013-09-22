package Methods;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import Data.Graph;
import Data.MyPriorityQueue;
import Data.Node;


public class AStar {
	
	public MyPriorityQueue frontier = new MyPriorityQueue();
	public Node rootNode;
	public Graph g;
	public Set<Node> explored = new HashSet<Node>();
	public JTextArea output;
	public String path="";
	public JLabel cost_label;
 
	/**
	 * Instantiates a new a star.
	 *
	 * @param g the g
	 * @param output the output
	 * @param l the l
	 */
	public  AStar(Graph g,JTextArea output,JLabel l) {
		super();
		this.g=g;
		this.output=output;
		cost_label=l;
		output.setText("");
	}

	public boolean AStarSearch(int root_id,int value){

		rootNode=g.all_nodes.get(root_id);
		g.setHeuristics(value); // set heuristic values based on distance to the goal 
		frontier.insert(rootNode,3);
		rootNode.is_discovered=true;
		rootNode.astarcost=0;
		rootNode.totalcost=0;
		 
		while(!frontier.isEmpty()){

			Node n = (Node)frontier.getItem();

			if(n.node_id==value){
				output.append("found it!");
				backtrace(n);
				cost_label.setText("Cost:"+ n.totalcost); 
				return true;
			} // end of if  

			explored.add(n); // add seen nodes 

		 	output.append("total cost for n :" + n.astarcost);

			for(Node adj : n.visible_nodes){  
				
				if(frontier.seachItem(adj)==true && adj.is_discovered==false){
					
					float a_total_cost =n.astarcost+adj.heuristics;
			 
					if(adj.astarcost>=a_total_cost){ // current total cost less than before total cost 
						output.append("current root of "+ (adj.node_id+1) + " is "+(adj.root_node.node_id+1) + " going to be "+(n.node_id+1)+"\n");
						adj.root_node=n;
						adj.totalcost= (float) (n.totalcost+ adj.getDistance(n.node_id,adj.node_id));
						adj.astarcost=adj.totalcost+adj.heuristics;
						output.append("neighbour "+(adj.node_id+1) + " going to be discovered \n");
						adj.is_discovered=true;	
					}
				}// if 

				else if(frontier.seachItem(adj)==false && adj.is_discovered==false){
					output.append("\nfrom "+ (n.node_id+1)+"\n");
					adj.root_node=n;
					adj.totalcost= (float) (n.totalcost+ adj.getDistance(n.node_id,adj.node_id));
					adj.astarcost=adj.totalcost+adj.heuristics; // N&R formula applies 
					output.append("total cost for "+(adj.node_id+1) +" is:"+adj.astarcost+"\n");
					frontier.insert(adj,3);
					n.is_discovered=true;
					
				}
			} // end of for 
					output.append((n.node_id+1) +" going to be ignored \n");
					output.append("\nmy frontier que is :");
					output.append(frontier.array.toString()+"\n");
					
		}//while
					return false ;
	}//end of method

	void backtrace(Node n){
		if(n!=null){
			path=path+"-->"+(n.node_id+1);
			n=n.root_node;
			backtrace(n);
		}
		else{
			path=path+"*";
		}
	}//back trace 
}
