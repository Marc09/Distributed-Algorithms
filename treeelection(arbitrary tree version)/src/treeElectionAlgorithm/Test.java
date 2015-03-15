package treeElectionAlgorithm;

//arbitrary tree

import java.util.Random;

public class Test {
	
	static Node root = new Node(5);
	static Node n1 = new Node(1);
	static Node n2 = new Node(4);
	static Node n3 = new Node(6);
	static Node n4 = new Node(2);
	static Node n5 = new Node(3);
	static Node n6 = new Node(7);
	
	static void preElectionWakeUp(Node node){
		
		if(!node.getWokenUp()){
			for (int i = 0; i < node.neighbours.size(); i++){
				if (node.neighbours.get(i).wakeUp.get(node).equals(false)){
					node.neighbours.get(i).setWakeUpCallCounter(i, node, true);
					System.out.println("     Node " + node.getValue() + " sends a wake up call to " + node.neighbours.get(i).getValue());
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
			
			System.out.println("     Node " + node.getValue() + " has decided before");
			
		}else if (!node.getDecide()){
			
			int counter = node.recCounter(node);
			System.out.println("     Node " + node.getValue() + " has " + counter + " node(s) whose rec is false");
			if (node.recCounter(node) > 1){
				System.out.println("     Node " + node.getValue() + " can do nothing now");
			}
		
			if (counter == 1){
				
				if (node.isLeaf()){
					
					if (node.neighbours.get(0).nodeRecNumber.get(node).equals(false)){
						node.neighbours.get(0).setNodeRecCounter(node, true);
						System.out.println("     Node " + node.getValue() + " sends a message to the parent Node " + node.neighbours.get(0).getValue() + " which is also its silent neighbour");
						if (node.neighbours.get(0).victor.get(0) > node.victor.get(0)){
							node.neighbours.get(0).victor.clear();
							node.neighbours.get(0).victor.add(node.victor.get(0));
						}else {
							node.victor.clear();
							node.victor.add(node.neighbours.get(0).victor.get(0));
						}
					}else if(node.neighbours.get(0).nodeRecNumber.get(node).equals(true)){
						System.out.println("     Node " + node.getValue() + " already sent a message to the parent Node " + node.neighbours.get(0).getValue() + " which is also its silent neighbour");
					}
					
					if (node.neighbours.get(0).recCounter(node.neighbours.get(0)) == 0){
						if (!node.neighbours.get(0).getDecide()){
							System.out.println("     Node " + node.neighbours.get(0).getValue() + " decides");
							node.neighbours.get(0).setDecide(true);
							System.out.println("     Node " + node.getValue() + " decides as well");
							node.setDecide(true);
							System.out.println("     Node " + node.victor.get(0) + " is the victor!");
						}
					}
					
				}else {
					for (int i = 0; i < node.neighbours.size(); i++){
						if (node.neighbours.get(i).equals(true)){
							System.out.println("     Node " + node.getValue() + " already sent a message to the Node " + node.neighbours.get(i).getValue() + " which is also its silent neighbour");
						}else if (node.nodeRecNumber.get(node.neighbours.get(i)).equals(false)){
							node.neighbours.get(i).setNodeRecCounter(i, node, true);
							System.out.println("     Node " + node.getValue() + " sends a message to the Node " + node.neighbours.get(i).getValue() + " which is also its silent neighbour");
							if (node.neighbours.get(i).victor.get(0) > node.victor.get(0)){
								node.neighbours.get(i).victor.clear();
								node.neighbours.get(i).victor.add(node.victor.get(0));
							}else {
								node.victor.clear();
								node.victor.add(node.neighbours.get(i).victor.get(0));
							}

							if (node.neighbours.get(i).recCounter(node.neighbours.get(i)) == 0 && !node.neighbours.get(i).getDecide()){
								System.out.println("     Node " + node.neighbours.get(i).getValue() + " decides");
								node.neighbours.get(i).setDecide(true);
								System.out.println("     Node " + node.getValue() + " decides as well");
								node.setDecide(true);
								System.out.println("     Node " + node.victor.get(0) + " is the victor!");
							}
						}
						
					}
					
				}
			}
		}
}
	
	public static void main(String[] args){
		
		root.addNeighbours(n1);
		root.addNeighbours(n2);
		root.addNeighbours(n3);
		n1.addNeighbours(root);
		n2.addNeighbours(root);
		n2.addNeighbours(n4);
		n2.addNeighbours(n5);
		n3.addNeighbours(root);
		n3.addNeighbours(n6);
		n4.addNeighbours(n2);
		n5.addNeighbours(n2);
		n6.addNeighbours(n3);
		
		Node[] node = {root, n1, n2, n3, n4, n5, n6};
		int execution = 0;
		for (int i = 1; i <= 700; i++){
			int r = 0;
			Random randomRProcesses = new Random();
			r = 1 + randomRProcesses.nextInt(7);
			for (int j = 0; j < r; j++){
				Random ran = new Random();
				int ranNode = 1 + ran.nextInt(7);
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
						case 1: preElectionWakeUp(n1);
								if(!(n1.wakeUpCounter(n1) < n1.neighbours.size())){
									waveAlgorithm(n1);
									break;
								}else {
									break;
								}
						case 4: preElectionWakeUp(n2);
								if (!(n2.wakeUpCounter(n2) < n2.neighbours.size())){
									waveAlgorithm(n2);
									break;
								}else {
									break;
								}
						case 6: preElectionWakeUp(n3);
								if (!(n3.wakeUpCounter(n3) < n3.neighbours.size())){
									waveAlgorithm(n3);
									break;
								}else {
									break;
								}
						case 2: preElectionWakeUp(n4);
								if (!(n4.wakeUpCounter(n4) < n4.neighbours.size())){
									waveAlgorithm(n4);
									break;
								}else {
									break;
								}
						case 3: preElectionWakeUp(n5);
								if (!(n5.wakeUpCounter(n5) < n5.neighbours.size())){
									waveAlgorithm(n5);
									break;
								}else {
									break;
								}
						case 7: preElectionWakeUp(n6);
								if (!(n6.wakeUpCounter(n6) < n6.neighbours.size())){
									waveAlgorithm(n6);
									break;
								}else {
									break;
								}
					}
			}
		}
		System.out.println("The following nodes decide this time: ");
		for (int i = 0; i < 7; i++){
			if (node[i].getDecide()){
				for (int j = 0; j < 7; j++){
					if (j != i){
						node[j].victor.clear();
						node[j].victor.add(node[i].victor.get(0));
					}
				}
				System.out.println("Node: " + node[i].getValue());
			}
		}
		System.out.println("Everybody knows who is the victor!");
		for (int i = 0; i < 7; i++){
			System.out.println("Node " + node[i].getValue() +" knows " + node[i].victor.get(0) + " is the victor!");
		}
	}
}
