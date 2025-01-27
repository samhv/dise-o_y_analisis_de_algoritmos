package trees;

import estructuras.Nodo;

/**
 * Árbol AVL
 * 
 * @author Pablo
 *
 */
public class Avl extends Abb {

	private enum Balance {
		LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
	}

	public Nodo insert(int key, int value) {
		Nodo nodeToReturn = super.insert(key, value);
		Nodo nodeAdded = nodeToReturn;

		while (nodeAdded != null) {
			nodeAdded.updateHeight();
			balanceAfterInsert(nodeAdded);
			nodeAdded = nodeAdded.getParent();

		}

		return nodeToReturn;
	}

	public Nodo delete(int key) {
		// Find node to remove
		Nodo nodeToRemoved = this.getNodo(key);
		if (nodeToRemoved != null) {
			// Find the replacement node
			Nodo replacementNode = this.getReplacementNode(nodeToRemoved);

			// Find the parent of the replacement node to re-factor the
			// height/balance of the tree
			Nodo nodeToRefactor = null;
			if (replacementNode != null)

				nodeToRefactor = replacementNode.getParent();
			if (nodeToRefactor == null)

				nodeToRefactor = nodeToRemoved.getParent();
			if (nodeToRefactor != null && nodeToRefactor.equals(nodeToRemoved))

				nodeToRefactor = replacementNode;

			// Replace the node
			replaceNodeWithNode(nodeToRemoved, replacementNode);

			// Re-balance the tree all the way up the tree
			if (nodeToRefactor != null) {
				while (nodeToRefactor != null) {
					nodeToRefactor.updateHeight();
					balanceAfterDelete(nodeToRefactor);

					nodeToRefactor = nodeToRefactor.getParent();
				}
			}
		}

		return nodeToRemoved;

	}

	private void balanceAfterInsert(Nodo node) {
		int balanceFactor = node.getBalanceFactor();
		if (balanceFactor > 1 || balanceFactor < -1) {
			Nodo parent = null;
			Nodo child = null;
			Balance balance = null;
			if (balanceFactor < 0) {
				parent = node.getLeft();
				balanceFactor = parent.getBalanceFactor();
				if (balanceFactor < 0) {

					child = parent.getLeft();
					balance = Balance.LEFT_LEFT;
				} else {

					child = parent.getRight();
					balance = Balance.LEFT_RIGHT;
				}
			} else {

				parent = node.getRight();
				balanceFactor = parent.getBalanceFactor();
				if (balanceFactor < 0) {

					child = parent.getLeft();
					balance = Balance.RIGHT_LEFT;
				} else {

					child = parent.getRight();
					balance = Balance.RIGHT_RIGHT;
				}
			}

			if (balance == Balance.LEFT_RIGHT) {
				// Left-Right (Left rotation, right rotation)
				rotateLeft(parent);
				rotateRight(node);
			} else if (balance == Balance.RIGHT_LEFT) {
				// Right-Left (Right rotation, left rotation)
				rotateRight(parent);
				rotateLeft(node);
			} else if (balance == Balance.LEFT_LEFT) {
				// Left-Left (Right rotation)
				rotateRight(node);
			} else {
				// Right-Right (Left rotation)
				rotateLeft(node);
			}

			node.updateHeight(); // New child node
			child.updateHeight(); // New child node
			parent.updateHeight(); // New Parent node
		}
	}

	private void balanceAfterDelete(Nodo node) {
		int balanceFactor = node.getBalanceFactor();
		if (balanceFactor == -2 || balanceFactor == 2) {
			if (balanceFactor == -2) {
				Nodo ll = node.getLeft().getLeft();
				int lesser = (ll != null) ? ll.getHeight() : 0;
				Nodo lr = node.getLeft().getRight();
				int greater = (lr != null) ? lr.getHeight() : 0;
				if (lesser >= greater) {
					rotateRight(node);
					node.updateHeight();
					if (node.getParent() != null)
						(node.getParent()).updateHeight();
				} else {
					rotateLeft(node.getLeft());
					rotateRight(node);

					Nodo p = node.getParent();
					if (p.getLeft() != null)
						(p.getLeft()).updateHeight();
					if (p.getRight() != null)
						(p.getRight()).updateHeight();
					p.updateHeight();
				}
			} else if (balanceFactor == 2) {
				Nodo rr = node.getRight().getRight();
				int greater = (rr != null) ? rr.getHeight() : 0;
				Nodo rl = node.getRight().getLeft();
				int lesser = (rl != null) ? rl.getHeight() : 0;
				if (greater >= lesser) {
					rotateLeft(node);
					node.updateHeight();
					if (node.getParent() != null)
						(node.getParent()).updateHeight();
				} else {
					rotateRight(node.getRight());
					rotateLeft(node);

					Nodo p = node.getParent();
					if (p.getLeft() != null)
						(p.getLeft()).updateHeight();
					if (p.getRight() != null)
						(p.getRight()).updateHeight();
					p.updateHeight();
				}
			}
		}
	}

}
