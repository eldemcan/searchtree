package Methods;

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



public class GreedyBestFirst {

	public MyPriorityQueue frontier = new MyPriorityQueue();
	public Node rootNode;
	public Graph g;
	public float total_cost;
	public Set<Node> explored = new HashSet<Node>();
	public JTextArea output;
	public String path="";
	public JLabel cost_label;


	public  GreedyBestFirst(Graph g,JTextArea output,JLabel l) {
		super();
		this.g=g;
		this.output=output;
		cost_label=l;
		output.setText("");
	}

	public boolean GreedySearch(int root_id,int value){

		rootNode=g.all_nodes.get(root_id); 
		frontier.insert(rootNode,2); // insert and sort 
		rootNode.is_discovered=true;
		g.setHeuristics(value); // set heuristics for all nodes in graph 
		

		while(!frontier.isEmpty()){

			Node n = (Node)frontier.getItem();
			explored.add(n);
			
			if(n.node_id==value){
				output.append(("found it!\n"));
				backtrace(n);
				cost_label.setText("Cost:"+total_cost);
				return true;
			} // end of if  

			for(Node adj : n.visible_nodes){  
				if(frontier.seachItem(adj)==false && adj.is_discovered==false){
					output.append("from "+ (n.node_id+1)+"\n");
					output.append("heuristics for "+(adj.node_id+1) + " is " + adj.heuristics+"\n");
					adj.root_node=n;
					frontier.insert(adj,2);
					n.is_discovered=true;
				}
			} // end of for check adj 
			output.append((n.node_id+1) +" going to be ignored\n");
			output.append((frontier.array.get(0).node_id+1) +" going to be choosen\n");
			output.append("my frontier que is :");
			output.append(frontier.array.toString()+"\n");
		}//while
		return false ;
	}//end of method
	 
	void backtrace(Node n){

		if(n!=null){
			path=path+"-->"+(n.node_id+1);
			if(n.root_node!=null)
			total_cost=(float) (total_cost+g.getDistance(n.node_id,n.root_node.node_id));
			n=n.root_node;
			backtrace(n);
		}//
		else{
			path=path+"*";
		}
	}//back trace 

}// end of uniform cost 