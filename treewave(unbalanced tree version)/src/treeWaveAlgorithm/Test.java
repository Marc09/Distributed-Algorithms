package treeWaveAlgorithm;
//Unbalanced tree version
import java.util.Random;

public class Test {
	
	static Node root = new Node(1);
	static Node n1 = new Node(2);
	static Node n2 = new Node(3);
	static Node n3 = new Node(4);
	static Node n4 = new Node(5);
	static Node n5 = new Node(6);
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
					
					if (node.getParent().nodeRecNumber.get(node).equals(false)){
						System.out.println("     Node " + node.getValue() + " sends a message to the parent Node " + node.getParent().getValue() + " which is also its silent neighbour");
						node.getParent().setParentNodeRecNumber(node, true);
					}else if (node.getParent().nodeRecNumber.get(node).equals(true)){
						System.out.println("     Node " + node.getValue() + " already sent a message to the parent Node " + node.getParent().getValue());
					}
					
					if (node.getParent().recCounter(node.getParent()) == 0){
						if (!node.getParent().getDecide()){
							System.out.println("     Node " + node.getParent().getValue() + " decides");
							node.getParent().setDecide(true);
							System.out.println("     Node " + node.getValue() + " decides as well");
							node.setDecide(true);
						}else {
							System.out.println("     Node " + node.getParent().getValue() + " has decided before");
						}
						
					}
					
				}else{
					if (node.getParent() != null && node.nodeRecNumber.get(node.getParent()).equals(false) && !node.getParent().getDecide()){
						node.getParent().setParentNodeRecNumber(node, true);
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("Parent");
						if (node.getParent().recCounter(node.getParent()) == 0){
							if (!node.getParent().getDecide()){	
								System.out.println("     Node " + node.getParent().getValue() + " decides");
								node.getParent().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
							}else {
								System.out.println("     Node " + node.getParent().getValue() + " has decided before");
							}
						}
					}else if (node.getLeftChild() != null && node.nodeRecNumber.get(node.getLeftChild()).equals(false) && !node.getLeftChild().getDecide()){
						node.getLeftChild().setLeftChildNodeRecNumber(node, true);
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("LeftChild");
						if (node.getLeftChild().recCounter(node.getLeftChild()) == 0){
							if (!node.getLeftChild().getDecide()){
								System.out.println("     Node " + node.getLeftChild().getValue() + " decides");
								node.getLeftChild().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
							}else {
								System.out.println("     Node " + node.getLeftChild().getValue() + " has decided before");
							}
						}
					}else if (node.getRightChild() != null && node.nodeRecNumber.get(node.getRightChild()).equals(false) && !node.getRightChild().getDecide()){
						node.getRightChild().setRightChildNodeRecNumber(node, true);
						System.out.print("     Node " + node.getValue() + " sends a message to the silent neighbour: ");
						System.out.println("RightChild");
						if (node.getRightChild().recCounter(node.getRightChild()) == 0){
							if (!node.getRightChild().getDecide()){
								System.out.println("     Node " + node.getRightChild().getValue() + " decides");
								node.getRightChild().setDecide(true);
								System.out.println("     Node " + node.getValue() + "decides as well");
								node.setDecide(true);
							}else {
								System.out.println("     Node " + node.getRightChild().getValue() + " has decided before");
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
		n2.setRightChild(n3);
		n3.setRightChild(n4);
		n4.setRightChild(n5);
		n5.setRightChild(n6);
		n1.setParent(root);
		n2.setParent(root);
		n3.setParent(n2);
		n4.setParent(n3);
		n5.setParent(n4);
		n6.setParent(n5);
		
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
					System.out.println("ROUND No." + i + ":                   node " + ranNode + " runs the algorithm!");
					System.out.println("EXECUTION No." + execution);
					switch(ranNode){
						default: waveAlgorithm(root);
								 break;
						case 2: waveAlgorithm(n1);
								break;
						case 3: waveAlgorithm(n2);
								break;
						case 4: waveAlgorithm(n3);
								break;
						case 5: waveAlgorithm(n4);
								break;
						case 6: waveAlgorithm(n5);
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
