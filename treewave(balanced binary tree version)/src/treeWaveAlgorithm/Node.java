package treeWaveAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {  
	
    private int value;  
    private boolean rec;  
    private boolean decide;
    private Node parent;
    private Node leftChild;  
    private Node rightChild;
    HashMap<Node, Boolean> nodeRecNumber; 
    ArrayList<Node> neighbours = new ArrayList<Node>();
    
    public Node(int value){
    	this.value = value;
    	decide = false;
    	nodeRecNumber = new HashMap<Node, Boolean>();
    	neighbours = new ArrayList<Node>();
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
		neighbours.add(leftChild);
		nodeRecNumber.put(leftChild, false);
	}
	
	public Node getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		neighbours.add(rightChild);
		nodeRecNumber.put(rightChild, false);
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
		neighbours.add(parent);
		nodeRecNumber.put(parent, false);
	}

	public boolean getDecide() {
		return decide;
	}

	public void setDecide(boolean decide) {
		this.decide = decide;
	}
	
}