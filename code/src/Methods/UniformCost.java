package Methods;
import java.awt.Adjustable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import Data.Graph;
import Data.MyPriorityQueue;
import Data.Node;


// TODO: Auto-generated Javadoc
/**
 * The Class UniformCost.This calss is implementing uniform cost algorithm 
 */
public class UniformCost {

	public MyPriorityQueue frontier = new MyPriorityQueue();  // create custom priority queue 
	public Node rootNode;
	public Graph g;
	public Set<Node> explored = new HashSet<Node>();
	public JTextArea output;
	public String path="";
	public JLabel cost_label;

	/**
	 * Instantiates a new uniform cost.
	 *
	 * @param g the g
	 * @param output the output
	 * @param l the l
	 */
	public UniformCost(Graph g,JTextArea output,JLabel l) {
		super();
		this.g=g;
		this.output=output;
		cost_label=l;
		output.setText("");

	}

	/**
	 * Uniform cost search.
	 *
	 * @param root_id the root_id
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean uniformCostSearch(int root_id,int value){

		rootNode=g.all_nodes.get(root_id);
		frontier.insert(rootNode,1);
		rootNode.is_discovered=true;
		rootNode.totalcost=0;
		
		while(!frontier.isEmpty()){

			Node n = (Node)frontier.getItem();

			if(n.node_id==value){
				output.append("found it!\n");
				backtrace(n);
				cost_label.setText("total cost is :" +  n.totalcost  + "\n");
				output.append("Cost :"+ n.totalcost+"\n");
				return true;
			} // end of if  

			explored.add(n); // add seen nodes 

			output.append("total cost for n :" + n.totalcost+"\n");

			for(Node adj : n.visible_nodes){  
				if(frontier.seachItem(adj)==true && adj.is_discovered==false){

					if(adj.totalcost>=(n.totalcost+g.getDistance(n.node_id,adj.node_id))){ // current total cost less than before total cost 
						output.append("current root of "+ (adj.node_id+1) + "is "+(adj.root_node.node_id+1) + " going to be "+(n.node_id+1)+"\n");
						adj.root_node=n;
						adj.totalcost= (float) (n.totalcost+ adj.getDistance(n.node_id,adj.node_id));
						output.append("neighbour "+(adj.node_id+1) + " going to be discovered\n");
						adj.is_discovered=true;
					}
				}// if 

				else if(frontier.seachItem(adj)==false && adj.is_discovered==false){
					output.append("from "+ (n.node_id+1)+"\n");
					adj.root_node=n;
					adj.totalcost= (float) (n.totalcost+ g.getDistance(n.node_id,adj.node_id));
					output.append("total cost for "+(adj.node_id+1) +" is:"+adj.totalcost+"\n");
					frontier.insert(adj,1);
					n.is_discovered=true;
				}
			} // end of for 
					output.append((n.node_id+1) +" going to be ignored\n");
					output.append("\nmy frontier que is :");
					output.append(frontier.array.toString()+"\n");
		}//while
		return false ;
	}//end of method

	/**
	 * Backtrace.
	 *
	 * @param n the n
	 */
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

}// end of uniform cost 

