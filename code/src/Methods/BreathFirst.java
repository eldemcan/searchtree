package Methods;

import java.awt.LinearGradientPaint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import Data.Graph;
import Data.Node;


public class BreathFirst {

	public Queue<Node> queue = new LinkedList<Node>();
	public Node rootNode;
	public Graph g;
	public String path="";
	public Set<Node> explored = new HashSet<Node>();
	public JTextArea output;

	/**
	 * Instantiates a new breath first.
	 *
	 * @param g the g
	 * @param output the output
	 */
	public BreathFirst(Graph g,JTextArea output) {
		super();
		this.g=g;
		this.output=output;
		output.setText("");
	 
	}

	public boolean breadthFirstTraversal(int root_id,int value){

		rootNode=g.all_nodes.get(root_id); // set root node according to given start point 
		queue.add(rootNode);
		rootNode.is_discovered=true;

		while(!queue.isEmpty()){

			Node n = (Node)queue.poll(); 
			
			explored.add(n); // add to explored nodes for reporting 
		 
			if(n.node_id==value){
				System.out.println("found it!");		 
				backtrace(n);
				return true;
			}
			
			//explore each node and put it to queue  
			for(Node adj : n.visible_nodes){  
				if(!adj.is_discovered){
					adj.root_node=n;
					adj.is_discovered=true;
					queue.add(adj);
					output.append(adj.node_id+":going to be explored\n");
					output.append("root for "+adj.node_id+" is "+n.node_id+"\n");
				}
			}

		}//while
		
		return false ;
	}//end of method

	void backtrace(Node n){

		if(n!=null){

		    path=path+"-->"+(n.node_id+1);
		    System.out.print("-->"+(n.node_id+1));
			n=n.root_node;
			backtrace(n);
		}

		else{
			path=path+"*";
			System.out.println("*");
		}
	}//back trace 
}
