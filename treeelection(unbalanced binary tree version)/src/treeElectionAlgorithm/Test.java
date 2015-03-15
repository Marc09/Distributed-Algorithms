package treeElectionAlgorithm;

//unbalanced binary tree

import java.util.Random;

public class Test {
	
	static Node root = new Node(6);
	static Node n1 = new Node(2);
	static Node n2 = new Node(7);
	static Node n3 = new Node(1);
	static Node n4 = new Node(3);
	static Node n5 = new Node(4);
	static Node n6 = new Node(5);
	static Node n7 = new Node(8);
	
	static void preElectionWakeUp(Node node){
		if (!node.getWokenUp()){
			if (!node.isLeaf()){
				if (node.getParent() != null && node.getParent().wakeUp.get(node) == false){
					System.out.println("     Node " + node.getValue() + " sends a wake up call to its parent Node " + node.getParent().getValue());
					node.getParent().setParentWakeUpCallCounter(node, true);
				}else if (node.getParent() != null && node.getParent().wakeUp.get(node).equals(true)){
					System.out.println("     Node " + node.getValue() + " already sent a wake up call to the parent Node " + node.getParent().getValue());
				}
		
				if (node.getLeftChild() != null && node.getLeftChild().wakeUp.get(node) == false){
					System.out.println("     Node " + node.getValue() + " sends a wake up call to its left child Node " + node.getLeftChild().getValue());
					node.getLeftChild().setLeftChildWakeUpCallCounter(node, true);
				}else if (node.getLeftChild() != null && node.getLeftChild().wakeUp.get(node).equals(true)){
					System.out.println("     Node " + node.getValue() + " already sent a wake up call to the left child Node " + node.getLeftChild().getValue());
				}
				
				if (node.getRightChild() != null && node.getRightChild().wakeUp.get(node) == false){
					System.out.println("     Node " + node.getValue() + " sends a wake up call to its right child Node " + node.getRightChild().getValue());
					node.getRightChild().setRightChildWakeUpCallCounter(node, true);
				}else if (node.getRightChild() != null && node.getRightChild().wakeUp.get(node).equals(true)){
					System.out.println("     Node " + node.getValue() + " already sent a wake up call to the right child Node " + node.getRightChild().getValue());
				}
				
			}else {
				if (node.getParent() != null && node.getParent().wakeUp.get(node) == false){
					System.out.println("     Node " + node.getValue() + " sends a wake up call to its parent Node " + node.getParent().getValue());
					node.getParent().setParentWakeUpCallCounter(node, true);
				}else if (node.getParent() != null && node.getParent().wakeUp.get(node).equals(true)){
					System.out.println("     Node " + node.getValue() + " already sent a wake up call to the parent Node " + node.getParent().getValue());
				}
			}
		
			if (node.wakeUpCounter(node) == node.neighbours.size()){
				node.setWokenUp(true);
				System.out.println("     Node " + node.getValue() + " has woken up!");
			}
		}else{
			System.out.println("     Node " + node.getValue() + " has woken up!");
		}
	}
	
	static void waveAlgorithm(Node node){
		
		if (node.getDecide()){
			
			System.out.println("     Node " + node.getValue() + " has decided");
			
		}else if (!node.getDecide()){
			
			int counter = node.recCounter(node);
			System.out.println("     Node " + node.getValue() + " has " + counter + " node(s) whose rec is false");
			if (node.recCounter(node) > 1){
				System.out.println("     Node " + node.getValue() + " can do nothing now");
			}
		
			if (node.recCounter(node) == 1){
				
				if (node.isLeaf()){
					
					if (node.getParent().nodeRecNumber.get(node).equals(false)){
						System.out.println("     Node " + node.getValue() + " sends a message to the parent Node " + node.getParent().getValue() + " which is also its silent neighbour");
						node.getParent().setParentNodeRecNumber(node, true);
						if (node.getParent().victor.get(0) > node.victor.get(0)){
							node.getParent().victor.clear();
							node.getParent().victor.add(node.victor.get(0));
						}else {
							node.victor.clear();
							node.victor.add(node.getParent().victor.get(0));
						}
					}else if (node.getParent().nodeRecNumber.get(node).equals(true)){
						System.out.println("     Node " + node.getValue() + " already sent a message to the parent Node " + node.getParent().getValue());
					}
					
					if (node.getParent().recCounter(node.getParent()) == 0){
						if (!node.getParent().getDecide()){
							System.out.println("     Node " + node.getParent().getValue() + " decides");
							node.getParent().setDecide(true);
							System.out.println("     Node " + node.getValue() + " decides as well");
							node.setDecide(true);
							System.out.println("     Node " + node.victor.get(0) + " is the victor!");
						}else {
							System.out.println("     Node " + node.getParent().getValue() + " has decided before");
						}						
					}
					
				}else{
					if (node.getParent() != null && node.nodeRecNumber.get(node.getParent()).equals(false) && !node.getParent().getDecide()){
						node.getParent().setParentNodeRecNumber(node, true);
						if (node.getParent().victor.get(0) > node.victor.get(0)){
							node.getParent().victor.clear();
							node.getParent().victor.add(node.victor.get(0));
						}else {
							node.victor.clear();
							node.victor.add(node.getParent().victor.get(0));
						}
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("Parent");
						if (node.getParent().recCounter(node.getParent()) == 0){
							if (!node.getParent().getDecide()){	
								if (!node.getParent().getDecide()){
								System.out.println("     Node " + node.getParent().getValue() + " decides");
								node.getParent().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
								System.out.println("     Node " + node.victor.get(0) + " is the victor!");
							}else {
								System.out.println("     Node " + node.getParent().getValue() + " has decided before");
							}
						}
					}else if (node.getLeftChild() != null && node.nodeRecNumber.get(node.getLeftChild()).equals(false) && !node.getLeftChild().getDecide()){
						node.getLeftChild().setLeftChildNodeRecNumber(node, true);
						if (node.getLeftChild().victor.get(0) > node.victor.get(0)){
							node.getLeftChild().victor.clear();
							node.getLeftChild().victor.add(node.victor.get(0));
						}else {
							node.victor.clear();
							node.victor.add(node.getLeftChild().victor.get(0));
						}
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("LeftChild");
						if (node.getRightChild() != null && node.getLeftChild().recCounter(node.getLeftChild()) == 0){
							if (!node.getLeftChild().getDecide()){
								System.out.println("     Node " + node.getLeftChild().getValue() + " decides");
								node.getLeftChild().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
								System.out.println("     Node " + node.victor.get(0) + " is the victor!");
							}else {
								System.out.println("     Node " + node.getLeftChild().getValue() + " has decided before");
							}
						}
					}else if (node.getRightChild() != null && node.nodeRecNumber.get(node.getRightChild()).equals(false) && !node.getRightChild().getDecide()){
						node.getRightChild().setRightChildNodeRecNumber(node, true);
						if (node.getRightChild().victor.get(0) > node.victor.get(0)){
							node.getRightChild().victor.clear();
							node.getRightChild().victor.add(node.victor.get(0));
						}else {
							node.victor.clear();
							node.victor.add(node.getRightChild().victor.get(0));
						}
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("RightChild");
						if (node.getRightChild().recCounter(node.getRightChild()) == 0){
							if (!node.getRightChild().getDecide()){
								System.out.println("     Node " + node.getRightChild().getValue() + " decides");
								node.getRightChild().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
								System.out.println("     Node " + node.victor.get(0) + " is the victor!");
							}else {
								System.out.println("     Node " + node.getRightChild().getValue() + " has decided before");
							}
						}
					}
				}
			}
		}
	}
}
	
	public static void main(String[] args){
		
		root.setLeftChild(n1);
		root.setRightChild(n2);
		n1.setLeftChild(n3);
		n1.setRightChild(n4);
		n4.setRightChild(n5);
		n5.setRightChild(n6);
		n2.setRightChild(n7);
		
		n1.setParent(root);
		n2.setParent(root);
		n3.setParent(n1);
		n4.setParent(n1);
		n5.setParent(n4);
		n6.setParent(n5);
		n7.setParent(n2);
		
		Node[] node = {root, n1, n2, n3, n4, n5, n6, n7};
		int execution = 0;
		for (int i = 1; i <= 800; i++){
			int r = 0;
			Random randomRProcesses = new Random();
			r = 1 + randomRProcesses.nextInt(8);
			for (int j = 0; j < r; j++){
				Random ran = new Random();
				int ranNode = 1 + ran.nextInt(8);
				execution++;
					System.out.println("ROUND No." + i + ":                   Node " + ranNode + " runs!");
					System.out.println("EXECUTION No." + execution);
					switch(ranNode){
						default: preElectionWakeUp(root);
								 if (!(root.wakeUpCounter(root) < root.neighbours.size())){
									 waveAlgorithm(root);
									 break;
								 } else{
									 break;
								 }
						case 2: preElectionWakeUp(n1);
								if(!(n1.wakeUpCounter(n1) < n1.neighbours.size())){
									waveAlgorithm(n1);
									break;
								}else {
									break;
								}
						case 7: preElectionWakeUp(n2);
								if (!(n2.wakeUpCounter(n2) < n2.neighbours.size())){
									waveAlgorithm(n2);
									break;
								}else {
									break;
								}
						case 1: preElectionWakeUp(n3);
								if (!(n3.wakeUpCounter(n3) < n3.neighbours.size())){
									waveAlgorithm(n3);
									break;
								}else {
									break;
								}
						case 3: preElectionWakeUp(n4);
								if (!(n4.wakeUpCounter(n4) < n4.neighbours.size())){
									waveAlgorithm(n4);
									break;
								}else {
									break;
								}
						case 4: preElectionWakeUp(n5);
								//execution++;
								if (!(n5.wakeUpCounter(n5) < n5.neighbours.size())){
									waveAlgorithm(n5);
									break;
								}else {
									break;
								}
						case 5: preElectionWakeUp(n6);
								//execution++;
								if (!(n6.wakeUpCounter(n6) < n6.neighbours.size())){
									waveAlgorithm(n6);
									break;
								}else {
									break;
								}
						case 8: preElectionWakeUp(n7);
								//execution++;
								if (!(n7.wakeUpCounter(n7) < n7.neighbours.size())){
									waveAlgorithm(n7);
									break;
								}else {
									break;
								}
					}
			}
		}
		System.out.println("The following nodes decide this time: ");
		for (int i = 0; i < 8; i++){
			if (node[i].getDecide()){
				for (int j = 0; j < 8; j++){
					if (j != i){
						node[j].victor.clear();
						node[j].victor.add(node[i].victor.get(0));
					}
				}
				System.out.println("Node: " + node[i].getValue());
			}
		}
		System.out.println("Everybody knows who is the victor!");
		for (int i = 0; i < 8; i++){
			System.out.println("Node " + node[i].getValue() +" knows " + node[i].victor.get(0) + " is the victor!");
		}
	}
}
