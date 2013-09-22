/*
 * this is a  custom priority queue implementation 
 */
package Data;

import java.util.ArrayList;


public class MyPriorityQueue {
	public ArrayList<Node> array;  // array list structure to simulate queue 

	/**
	 * Instantiates a new my priority queue.
	 */
	public MyPriorityQueue () {
		array = new ArrayList<Node>();
	}

	public boolean isEmpty () {

		if(array.size()==0)
			return true;
		else 
			return false;
	} // isempty

	public void insert (Node item,int type) {

		array.add(item);


		if(type==1)  // sort for uniform cost
			swapWithParent();
		else if(type==2) // sort for  greedy cost 
			swapWithParentGreedy();
		else if(type==3) // sort for a start cost 
			swapWithParentAStar();

	} // insert 

	public Node getItem(){
		Node temp=array.remove(0);
		return temp;
	}

	// search item in arraylist return true if it is exists 
	public boolean seachItem(Node n){
		if(array.contains(n))
			return true;
		else 
			return false;
	}//search item


	/**
	 * Swap with parent.
	 *
	 * @param index the index
	 */
	public void swapWithParent( )
	{
		for(int i=0;i<array.size()-1;i++){
			for(int j=0;j<(array.size()-1)-i;j++){
				if (array.get(j).totalcost > array.get(j + 1).totalcost) {// change to > for ascending sort
					Node start=array.get(j);
					Node end=array.get(j+1);
					array.set(j+1,start);
					array.set(j,end);	
				} // if
			}
		} // for
	} // swap with parent 

	public void  swapWithParentGreedy( ){

		for(int i=0;i<array.size()-1;i++){
			for(int j=0;j<(array.size()-1)-i;j++){
				if (array.get(j).heuristics > array.get(j + 1).heuristics) {// change to > for ascending sort
					Node start=array.get(j);
					Node end=array.get(j+1);
					array.set(j+1,start);
					array.set(j,end);	
				} // if
			}
		} // for
	} // swap with parent 

	public void swapWithParentAStar( ){
		for(int i=0;i<array.size()-1;i++){
			for(int j=0;j<(array.size()-1)-i;j++){
				if (array.get(j).astarcost > array.get(j + 1).astarcost) {// change to > for ascending sort
					Node start=array.get(j);
					Node end=array.get(j+1);
					array.set(j+1,start);
					array.set(j,end);	
				} // if
			}
		} // for
	} // swap with parent 

} // end of class 

