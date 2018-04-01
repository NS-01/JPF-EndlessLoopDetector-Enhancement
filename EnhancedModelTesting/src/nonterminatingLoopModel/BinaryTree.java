package nonterminatingLoopModel;

public class BinaryTree {

	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
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

	public void inOrderTraversal(Node node) {
		// You don't need the while() loop in your inOrderTraversal(). It is a recursive
		// call. It's causing an endless loop.However, you do need something to stop the
		// recursion. You only recurse if the node is not null. Should be if.
		while (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
	}

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
}
