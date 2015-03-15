package treeElectionAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {  
	
    private int value;  
    private boolean rec;  
    private boolean wokenUp;
    private boolean decide;
    private Node parent;
    private Node leftChild;  
    private Node rightChild;
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
    	if (node.getParent() != null && node.wakeUp.get(node.getParent()).equals(true)){
   			wakeUpCallCounter++;
    	}
    	if (node.getLeftChild() != null && node.wakeUp.get(node.getLeftChild()).equals(true)){
   			wakeUpCallCounter++;
   		}
   		if (node.getRightChild() != null && node.wakeUp.get(node.getRightChild()).equals(true)){
   			wakeUpCallCounter++;
    	}
		return wakeUpCallCounter;
    	
    }
    
    public void setParentWakeUpCallCounter(Node n, boolean b){
    	n.getParent().wakeUp.remove(n);
    	n.getParent().wakeUp.put(n, b);
    }
    
    public void setLeftChildWakeUpCallCounter(Node n, boolean b){
    	n.getLeftChild().wakeUp.remove(n);
    	n.getLeftChild().wakeUp.put(n, b);
    }
    
    public void setRightChildWakeUpCallCounter(Node n, boolean b){
    	n.getRightChild().wakeUp.remove(n);
    	n.getRightChild().wakeUp.put(n, b);
    }
    
    public void setParentNodeRecNumber(Node n, boolean b){
    	n.getParent().nodeRecNumber.remove(n);
    	n.getParent().nodeRecNumber.put(n, b);
    }
    
    public void setLeftChildNodeRecNumber(Node n, boolean b){
    	n.getLeftChild().nodeRecNumber.remove(n);
    	n.getLeftChild().nodeRecNumber.put(n, b);
    }
    
    public void setRightChildNodeRecNumber(Node n, boolean b){
    	n.getRightChild().nodeRecNumber.remove(n);
    	n.getRightChild().nodeRecNumber.put(n, b);
    }
    
    public int recCounter(Node node){
    	int counter = 0;  	
   		if (node.getParent() != null && node.nodeRecNumber.get(node.getParent()).equals(false)){
   			counter++;
    	}
    	if (node.getLeftChild() != null && node.nodeRecNumber.get(node.getLeftChild()).equals(false)){
   			counter++;
   		}
   		if (node.getRightChild() != null && node.nodeRecNumber.get(node.getRightChild()).equals(false)){
   			counter++;
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
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
		nodeRecNumber.put(leftChild, false);
		wakeUp.put(leftChild, false);
		neighbours.add(leftChild);
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		nodeRecNumber.put(rightChild, false);
		wakeUp.put(rightChild, false);
		neighbours.add(rightChild);
	}
	
	public boolean isRec() {
		return rec;
	}
	
	public void setRec(boolean rec) {
		this.rec = rec;
	}
 
	public boolean isLeaf(){
		if (this.getLeftChild() == null && this.getRightChild() == null){
			return true;
		}else{
			return false;
		}
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
		nodeRecNumber.put(parent, false);
		wakeUp.put(parent, false);
		neighbours.add(parent);
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