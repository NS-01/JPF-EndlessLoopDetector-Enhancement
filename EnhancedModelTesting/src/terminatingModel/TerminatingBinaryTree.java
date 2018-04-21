package terminatingModel;

/**
 * Correct Code.
 * 
 * A tree data structure in which each node has at most two children, which are
 * referred to as the left child and the right child.
 * 
 * @author Varsha Ragavendran
 *
 */
public class TerminatingBinaryTree {
	/**
	 * Main method used for executing/verifying jpf config file
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TerminatingBinaryTree binaryTree = new TerminatingBinaryTree();
		binaryTree.add(50);
		binaryTree.add(40);
		binaryTree.add(39);
		binaryTree.add(42);
		binaryTree.add(41);
		binaryTree.add(43);
		binaryTree.add(55);
		binaryTree.add(65);
		binaryTree.add(60);
		binaryTree.inOrderTraversal(binaryTree.root);
	}

	// Node Data Structure
	public class Node {
		int data;
		Node left;
		Node right;
		Node parent;

		public Node(int d) {
			data = d;
			left = null;
			right = null;
		}
	}

	Node root = null;

	public void add(int d) {
		Node newNode = new Node(d);
		if (root != null) {

			Node futureParent = root;
			while (true) {
				if (newNode.data < futureParent.data) // going left
				{
					if (futureParent.left == null) {
						futureParent.left = newNode;
						newNode.parent = futureParent;
						break;
					}
					futureParent = futureParent.left;

				} else {
					if (futureParent.right == null) {
						futureParent.right = newNode;
						newNode.parent = futureParent;
						break;
					}
					futureParent = futureParent.right;
				}

			}

		} else {
			root = newNode;
		}
	}

	// correct version
	public void inOrderTraversal(Node node) {
		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
	}

}
