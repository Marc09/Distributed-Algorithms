package treeWaveAlgorithm;
//Arbitrary Tree version

import java.util.Random;


public class Test {
	
	static Node root = new Node(5);
	static Node n1 = new Node(1);
	static Node n2 = new Node(4);
	static Node n3 = new Node(6);
	static Node n4 = new Node(2);
	static Node n5 = new Node(3);
	static Node n6 = new Node(7);
	
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
					if (node.neighbours.get(0).nodeRecNumber.get(node).equals(false)){
						node.neighbours.get(0).setNodeRecCounter(node, true);
						System.out.println("     Node " + node.getValue() + " sends a message to the parent Node " + node.neighbours.get(0).getValue() + " which is also its silent neighbour");
					}
					
					if (node.neighbours.get(0).recCounter(node.neighbours.get(0)) == 0){
						if (!node.neighbours.get(0).getDecide()){
							System.out.println("     Node " + node.neighbours.get(0).getValue() + " decides");
							node.neighbours.get(0).setDecide(true);
							System.out.println("     Node " + node.getValue() + " decides as well");
							node.setDecide(true);
						}
					}
					
				}else{
					for (int i = 0; i < node.neighbours.size(); i++){
						if (node.nodeRecNumber.get(node.neighbours.get(i)).equals(false)){
							node.neighbours.get(i).setNodeRecCounter(i, node, true);
							System.out.println("     Node " + node.getValue() + " sends a message to Node " + node.neighbours.get(i).getValue() + " which is also its silent neighbour");
							if (node.neighbours.get(i).recCounter(node.neighbours.get(i)) == 0 && !node.neighbours.get(i).getDecide()){
								System.out.println("     Node " + node.neighbours.get(i).getValue() + " decides");
								node.neighbours.get(i).setDecide(true);
								System.out.println("     Node " + node.getValue() + " decides as well");
								node.setDecide(true);
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
					System.out.println("ROUND " + i + ":                   Node " + ranNode + " runs ");
					System.out.println("EXECUTION No." + execution);
					switch(ranNode){
						default: waveAlgorithm(root);
								 break;
						case 1: waveAlgorithm(n1);
								break;
						case 4: waveAlgorithm(n2);
								break;
						case 6: waveAlgorithm(n3);
								break;
						case 2: waveAlgorithm(n4);
								break;
						case 3: waveAlgorithm(n5);
								break;
						case 7: waveAlgorithm(n6);
								break;
				}
			}
		}
		System.out.println("The following nodes decide this time: ");
		for (int i = 0; i < 7; i++){
			if (node[i].getDecide()){
				System.out.println("Node: " + node[i].getValue());
			}
		}
	}
}
