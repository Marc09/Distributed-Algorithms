package treeWaveAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {  
	
    private int value;  
    private boolean rec;  
    private boolean decide;
    HashMap<Node, Boolean> nodeRecNumber; 
    ArrayList<Node> neighbours;
    
    public Node(int value){
    	this.value = value;
    	decide = false;
    	nodeRecNumber = new HashMap<Node, Boolean>();
    	neighbours = new ArrayList<Node>();
    }
    
    public void setNodeRecCounter(Node n, boolean b){
    	n.neighbours.get(0).nodeRecNumber.remove(n);
    	n.neighbours.get(0).nodeRecNumber.put(n, b);
    }
    
    public void setNodeRecCounter(int i, Node n, boolean b){
    	n.neighbours.get(i).nodeRecNumber.remove(n);
    	n.neighbours.get(i).nodeRecNumber.put(n, b);
    }
    
    public int recCounter(Node node){
    	int counter = 0; 
    	for (int i = 0; i < node.neighbours.size(); i++){
    		if (node.nodeRecNumber.get(node.neighbours.get(i)).equals(false)){
    			counter++;
    		}
    	}
    	return counter;
    }
    
    public boolean getRec(){
    	return rec;
    }
    
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void addNeighbours(Node neigh){
		this.neighbours.add(neigh);
		this.nodeRecNumber.put(neigh, false);
	}
	
	public boolean isRec() {
		return rec;
	}
	
	public void setRec(boolean rec) {
		this.rec = rec;
	}
 
	public boolean isLeaf(){
		if (this.neighbours.size() == 1){
			return true;
		}else{
			return false;
		}
	}

	public boolean getDecide() {
		return decide;
	}

	public void setDecide(boolean decide) {
		this.decide = decide;
	}
	
}