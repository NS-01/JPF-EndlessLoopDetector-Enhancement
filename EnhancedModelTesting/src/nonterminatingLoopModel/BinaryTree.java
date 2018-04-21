package nonterminatingLoopModel;

/**
 * Problematic Code.
 * 
 * A tree data structure in which each node has at most two children, which are
 * referred to as the left child and the right child.
 * 
 * @author Varsha Ragavendran
 *
 */
public class BinaryTree {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryTree bT = new BinaryTree();
		bT.addNode(50);
		bT.addNode(40);
		bT.addNode(39);
		bT.addNode(42);
		bT.addNode(41);
		bT.addNode(43);
		bT.addNode(55);
		bT.addNode(65);
		bT.addNode(60);
		bT.inOrderTraversal(bT.root);
	}

	// Node Data Structure
	public class Node {
		int data;
		Node leftNode;
		Node rightNode;
		Node parentNode;

		public Node(int d) {
			data = d;
			leftNode = null;
			rightNode = null;
		}
	}

	private Node root = null;

	public void addNode(int d) {
		Node newNode = new Node(d);
		if (root != null) {
			Node futureRoot = root;
			while (true) {
				// going left
				if (newNode.data < futureRoot.data) {
					if (futureRoot.leftNode == null) {
						futureRoot.leftNode = newNode;
						newNode.parentNode = futureRoot;
						break;
					}
					futureRoot = futureRoot.leftNode;

				}
				// going right
				else {
					if (futureRoot.rightNode == null) {
						futureRoot.rightNode = newNode;
						newNode.parentNode = futureRoot;
						break;
					}
					futureRoot = futureRoot.rightNode;
				}

			}

		} else {
			root = newNode;
		}
	}

	// Problematic code
	public void inOrderTraversal(Node node) {
		// You don't need the while() loop in your inOrderTraversal(). It is a recursive
		// call. It's causing an endless loop. You only recurse if the node is not null.
		// Should be if.
		while (node != null) {
			inOrderTraversal(node.leftNode);
			System.out.println(node.data);
			inOrderTraversal(node.rightNode);
		}
	}
}
