package treeElectionAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Node {  
	
    private int value;  
    private boolean rec;  
    private boolean wokenUp;
    private boolean decide;
    HashMap<Node, Boolean> nodeRecNumber;   
    HashMap<Node, Boolean> wakeUp;
    ArrayList<Node> neighbours;
    ArrayList<Integer> victor;
    
    public Node(int value){
    	this.value = value;
    	decide = false;
    	setWokenUp(false);
    	victor = new ArrayList<Integer>();
    	victor.add(value);
    	neighbours = new ArrayList<Node>();
    	wakeUp = new HashMap<Node, Boolean>();
    	nodeRecNumber = new HashMap<Node, Boolean>();
    }
    
    public int wakeUpCounter(Node node){
    	int wakeUpCallCounter = 0;
    	Iterator<Boolean> it = node.wakeUp.values().iterator();
    	while(it.hasNext())
    	{
    		if (it.next().equals(true)){
    			wakeUpCallCounter++;
    		}
    	}
		return wakeUpCallCounter;
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
    
    public void setWakeUpCallCounter(int i, Node n, boolean b){
    	n.neighbours.get(i).wakeUp.remove(n);
    	n.neighbours.get(i).wakeUp.put(n, b);
    }

    public void setNodeRecCounter(Node n, boolean b){
    	n.neighbours.get(0).nodeRecNumber.remove(n);
    	n.neighbours.get(0).nodeRecNumber.put(n, b);
    }
    
    public void setNodeRecCounter(int i, Node n, boolean b){
    	n.neighbours.get(i).nodeRecNumber.remove(n);
    	n.neighbours.get(i).nodeRecNumber.put(n, b);
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
		this.wakeUp.put(neigh, false);
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

	public boolean getWokenUp() {
		return wokenUp;
	}

	public void setWokenUp(boolean wokenUp) {
		this.wokenUp = wokenUp;
	}
	
}