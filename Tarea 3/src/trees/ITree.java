package trees;

import estructuras.Nodo;

public abstract class ITree {

	protected Nodo root;
	protected int size;

	public abstract String search(String key);

	public abstract Nodo insert(String key, String value);

	public abstract Nodo delete(String key);

	public abstract int size();

	protected enum Posicion {
		Izquierda, Derecha
	}

	protected void rotateLeft(Nodo node) {
		Posicion parentPosition = null;
		Nodo parent = node.getParent();
		if (parent != null) {
			if (node.equals(parent.getLeft())) {
				// Menor
				parentPosition = Posicion.Izquierda;
			} else {
				// Mayor
				parentPosition = Posicion.Derecha;
			}
		}

		Nodo greater = node.getRight();
		node.setRight(null);
		Nodo lesser = greater.getLeft();

		greater.setLeft(node);
		node.setParent(greater);

		node.setRight(lesser);
		
		if (lesser != null)
			lesser.setParent(node);

		if (parent != null && parentPosition != null) {
			if (parentPosition == Posicion.Izquierda) {
				parent.setLeft(greater);
			} else {
				parent.setRight(greater);
			}
			greater.setParent(parent);
		} else {
			root = greater;
			greater.setParent(null);
		}
	}

	protected void rotateRight(Nodo node) {
		Posicion parentPosition = null;
		Nodo parent = node.getParent();
		if (parent != null) {
			if (node.equals(parent.getLeft())) {
				// Menor
				parentPosition = Posicion.Izquierda;
			} else {
				// Mayor
				parentPosition = Posicion.Derecha;
			}
		}

		Nodo lesser = node.getLeft();
		node.setLeft(null);
		Nodo greater = lesser.getRight();

		lesser.setRight(node);
		node.setParent(lesser);

		node.setLeft(greater);
		if (greater != null)
			greater.setParent(node);

		if (parent != null && parentPosition != null) {
			if (parentPosition == Posicion.Izquierda) {
				parent.setLeft(lesser);
			} else {
				parent.setRight(lesser);
			}
			lesser.setParent(parent);
		} else {
			root = lesser;
			lesser.setParent(null);
		}
	}

}
