package trees;

import estructuras.Nodo;

/**
 * Árbol ABB
 * 
 * @author Pablo
 *
 */
public class Abb extends ITree {

	public Abb() {
		this.root = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	@Override
	public String search(String key) {

		Nodo nodo = root;
		while (nodo != null) {
			if (key.equals(nodo.getKey()))
				return nodo.getValue();
			if (key.compareTo(nodo.getKey()) < 0)
				nodo = nodo.getLeft();
			else
				nodo = nodo.getRight();
		}
		return null;

	}

	@Override
	public void insert(String key, String value) {
		Nodo nuevo = new Nodo(null, key, value);

		if (root == null) {
			size++;
			root = nuevo;
			return;
		}
		Nodo nodo = root;
		while (nodo != null) {

			if (nodo.getKey().compareTo(key) > 0) {
				if (nodo.getRight() == null) {
					nodo.setRight(nuevo);
					nuevo.setParent(nodo);
					size++;
					return;
				}
				nodo = nodo.getRight();

			} else {
				if (nodo.getLeft() == null) {
					nodo.setLeft(nuevo);
					nuevo.setParent(nodo);
					size++;
					return;
				}
				nodo = nodo.getLeft();
			}
		}

	}

	protected Nodo getNodo(String key) {
		Nodo node = root;
		while (node != null) {
			if (key.compareTo(node.getKey()) == 0) {
				return node;
			} else if (key.compareTo(node.getValue()) < 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return null;
	}

	protected Nodo getGreatest(Nodo startingNode) {
		if (startingNode == null)
			return null;

		Nodo greater = startingNode.getRight();
		while (greater != null) {
			Nodo node = greater.getRight();
			if (node != null)
				greater = node;
			else
				break;
		}
		return greater;
	}

	public void delete(String key) {
		Nodo toRemoved = getNodo(key);
		if (toRemoved != null)
			removeNode(toRemoved);
	}

	protected void removeNode(Nodo toRemoved) {
		if (toRemoved != null) {
			Nodo replacementNode = getReplacementNode(toRemoved);
			replaceNodeWithNode(toRemoved, replacementNode);
		}
	}

	protected Nodo getReplacementNode(Nodo nodeToRemoved) {
		Nodo replacement = null;
		if (nodeToRemoved.getLeft() != null && nodeToRemoved.getRight() == null) {
			// Usamos el de la izquierda
			replacement = nodeToRemoved.getLeft();
		} else if (nodeToRemoved.getRight() != null && nodeToRemoved.getLeft() == null) {
			// Usamos el de la derecha
			replacement = nodeToRemoved.getRight();
		} else if (nodeToRemoved.getRight() != null && nodeToRemoved.getLeft() != null) {
			// Dos hijos
			// Usamos el mayor de los menores
			replacement = getGreatest(nodeToRemoved.getLeft());
			if (replacement == null)
				replacement = nodeToRemoved.getLeft();

		}
		return replacement;
	}

	protected void replaceNodeWithNode(Nodo nodeToRemoved, Nodo replacementNode) {
		if (replacementNode != null) {
			// Save for later
			Nodo replacementNodeLesser = replacementNode.getLeft();
			Nodo replacementNodeGreater = replacementNode.getRight();

			// Replace replacementNode's branches with nodeToRemove's branches
			Nodo nodeToRemoveLesser = nodeToRemoved.getLeft();
			if (nodeToRemoveLesser != null && !nodeToRemoveLesser.equals(replacementNode)) {

				replacementNode.setLeft(nodeToRemoveLesser);
				nodeToRemoveLesser.setParent(replacementNode);
			}
			Nodo nodeToRemoveGreater = nodeToRemoved.getRight();
			if (nodeToRemoveGreater != null && !nodeToRemoveGreater.equals(replacementNode)) {

				replacementNode.setRight(nodeToRemoveGreater);
				nodeToRemoveGreater.setParent(replacementNode);

			}

			// Remove link from replacementNode's parent to replacement
			Nodo replacementParent = replacementNode.getParent();
			if (replacementParent != null && !replacementParent.equals(nodeToRemoved)) {
				Nodo replacementParentLesser = replacementParent.getLeft();
				Nodo replacementParentGreater = replacementParent.getRight();
				if (replacementParentLesser != null && replacementParentLesser.equals(replacementNode)) {
					replacementParent.setLeft(replacementNodeGreater);
					if (replacementNodeGreater != null)
						replacementNodeGreater.setParent(replacementParent);
				} else if (replacementParentGreater != null && replacementParentGreater.equals(replacementNode)) {
					replacementParent.setRight(replacementNodeLesser);
					if (replacementNodeLesser != null)
						replacementNodeLesser.setParent(replacementParent);
				}
			}
		}

		// Update the link in the tree from the nodeToRemoved to the
		// replacementNode
		Nodo parent = nodeToRemoved.getParent();
		if (parent == null) {
			// Replacing the root node
			root = replacementNode;
			if (root != null)
				root.setParent(null);
		} else if (parent.getLeft() != null && (parent.getLeft().getKey().compareTo(nodeToRemoved.getKey()) == 0)) {
			parent.setLeft(replacementNode);
			if (replacementNode != null)
				replacementNode.setParent(parent);
		} else if (parent.getRight() != null && (parent.getRight().getKey().compareTo(nodeToRemoved.getKey()) == 0)) {
			parent.setRight(replacementNode);
			if (replacementNode != null)
				replacementNode.setParent(parent);

		}
		size--;
	}

}
