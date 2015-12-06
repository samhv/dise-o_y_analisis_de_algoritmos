package trees;

import estructuras.Nodo;

public abstract class ITree {

	protected Nodo root;
	protected int size;

	public abstract int search(int key);

	public abstract Nodo insert(int key, int value);

	public abstract Nodo delete(int key);

	public abstract int size();

	protected enum Posicion {
		Izquierda, Derecha
	}
	
	public Nodo getRoot(){
		return root;
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

	public void preOrden(Nodo a, int profundidad) {
		for(int i =0;i < profundidad;i++){
			System.out.print("-");
		}
		System.out.println(a.getValue());

		if (a.getLeft() != null)
			preOrden(a.getLeft(),profundidad +1); /* Subárbol izquierdo */
		if (a.getRight()!=null)
			preOrden(a.getRight(),profundidad +1); /* Subárbol derecho */
	}

}
