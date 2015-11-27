package estructuras;

public class Nodo {

	private String key;
	private String value;
	private Nodo left;
	private Nodo right;
	private Nodo parent;

	public Nodo(Nodo parent, String key, String value) {
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
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

}
