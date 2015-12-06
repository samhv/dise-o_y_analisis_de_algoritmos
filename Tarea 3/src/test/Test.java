package test;

import trees.Abb;
import trees.Avl;
import trees.Splay;

public class Test {

	public static void main(String[] args) {
		Avl arbol = new Avl();
		arbol.insert(6, 6);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");
		arbol.insert(2, 2);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.insert(3, 3);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.insert(7, 7);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.insert(5, 5);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.insert(9, 9);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.delete(2);
		arbol.preOrden(arbol.getRoot(),1);
		System.out.println("===");

		arbol.insert(1, 1);
		arbol.insert(10, 10);
		arbol.delete(9);
		arbol.insert(8, 8);



		
		arbol.preOrden(arbol.getRoot(),1);

	}

}
