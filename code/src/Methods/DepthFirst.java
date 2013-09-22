package Methods;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.swing.JTextArea;

import Data.Graph;
import Data.Node;


// TODO: Auto-generated Javadoc
/**
 * The Class DepthFirst. includes depth first implementation 
 */
public class DepthFirst {
	public Stack<Node> stack = new  Stack<Node>();
	public Node rootNode;
	public Graph g;
	public String path="";
	public Set<Node> explored = new HashSet<Node>();
	private boolean is_true = false;
	public JTextArea output;

	/**
	 * Instantiates a new depth first.gets graph from GUI 
	 *
	 * @param g the g
	 */
	public DepthFirst(Graph g,JTextArea output) {
		super();
		this.g=g;
		this.output=output;
		this.output.setText("");

	}
	
	/**
	 * Depth first.
	 *
	 * @param root_id the root_id
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean depthFirst(int root_id,int value){

		rootNode=g.all_nodes.get(root_id);
		stack.push(rootNode);
		rootNode.is_discovered=true;

		if (!stack.isEmpty() && !is_true){

			Node n = (Node)stack.pop();

			explored.add(n);  // add to explored nodes 

			if(n.node_id==value){  // Extent to neighbors 
				System.out.println("found it!");		 
				backtrace(n); // run recursive backtrack algorithm
				is_true = true;
			}

			// expand neigbours to deepest level using recursive function 
			for(Node adj : n.visible_nodes){  
				if(!adj.is_discovered ){
					adj.is_discovered=true; // label as discovered
					output.append(adj.node_id+" going to expanded\n");
					adj.root_node=n;
					stack.push(adj);
					depthFirst(adj.node_id, value); // call function recursively 
				}				
			}// if is discovered 
		}// for 
		//if
		return is_true;
	}//end of method


	/**
	 * Backtrace.
	 *
	 * @param n the n
	 */
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
