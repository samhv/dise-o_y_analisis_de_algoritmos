package estructuras;

public class NodoAVL extends Nodo {

	protected int height = 1;

	public NodoAVL(Nodo parent, String key, String value) {

		super(parent, key, value);

	}
	
	public int getHeight(){
		return height;
	}
	
	protected boolean isLeaf() {
		return ((left == null) && (right == null));
	}

	public void updateHeight() {
		int lesserHeight = 0;
		int greaterHeight = 0;
		if (left != null) {
			NodoAVL lesserAVLNode = (NodoAVL) left;
			lesserHeight = lesserAVLNode.height;
		}
		if (right != null) {
			NodoAVL greaterAVLNode = (NodoAVL) right;
			greaterHeight = greaterAVLNode.height;
		}

		if (lesserHeight > greaterHeight) {
			height = lesserHeight + 1;
		} else {
			height = greaterHeight + 1;
		}
	}

	public int getBalanceFactor() {
		int lesserHeight = 0;
		int greaterHeight = 0;
		if (left != null) {
			NodoAVL lesserAVLNode = (NodoAVL) left;
			lesserHeight = lesserAVLNode.height;
		}
		if (right != null) {
			NodoAVL greaterAVLNode = (NodoAVL) right;
			greaterHeight = greaterAVLNode.height;
		}
		return greaterHeight - lesserHeight;
	}
}
