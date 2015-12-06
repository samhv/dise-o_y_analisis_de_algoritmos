package estructuras;

public class Nodo {
	protected int height = 1;
	protected int key;
	protected int value;
	protected Nodo left;
	protected Nodo right;
	protected Nodo parent;

	public Nodo(Nodo parent, int key, int value) {
		this.parent = parent;
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public Nodo getParent() {
		return parent;
	}

	public void setParent(Nodo parent) {
		this.parent = parent;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Nodo getLeft() {
		return left;
	}

	public void setLeft(Nodo left) {
		this.left = left;
	}

	public Nodo getRight() {
		return right;
	}

	public void setRight(Nodo right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	protected boolean isLeaf() {
		return ((left == null) && (right == null));
	}

	public void updateHeight() {
		int lesserHeight = 0;
		int greaterHeight = 0;
		if (left != null) {
			Nodo lesserAVLNode = left;
			lesserHeight = lesserAVLNode.height;
		}
		if (right != null) {
			Nodo greaterAVLNode = right;
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
			Nodo lesserAVLNode = left;
			lesserHeight = lesserAVLNode.height;
		}
		if (right != null) {
			Nodo greaterAVLNode = right;
			greaterHeight = greaterAVLNode.height;
		}
		return greaterHeight - lesserHeight;
	}

}
