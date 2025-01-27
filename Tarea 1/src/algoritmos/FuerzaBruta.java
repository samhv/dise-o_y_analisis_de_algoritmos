package algoritmos;

public class FuerzaBruta extends TextSearcher {

	public FuerzaBruta() {

	}

	@Override
	protected int search(String pattern, String text) {

		int cantidad = 0;
		int m = text.length();
		int n = pattern.length();
		int punteroGlobal = 0;

		while (punteroGlobal + (n - 1) < m) {

			for (int indexInPattern = 0; indexInPattern < n; indexInPattern++) {
				this.numberOfComparations++;
				int indexInText = punteroGlobal + indexInPattern;
				if (indexInText >= m)
					break;
				char x = text.charAt(indexInText);
				char y = pattern.charAt(indexInPattern);

				if (x != y) {
					punteroGlobal++;
					break;
				} else {
					if (indexInPattern == (n - 1)) {
						cantidad++;
						punteroGlobal++;
						break;
					}
				}
			}
		}

		if (this.numberOfComparations == 0)
			return 0;

		return cantidad;
	}

}
