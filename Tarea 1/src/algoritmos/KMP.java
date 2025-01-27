package algoritmos;

public class KMP extends TextSearcher {

	public KMP() {

	}

	void funcionError(String pattern, int[] lps) {
		int currMaxLPS = 0;
		lps[0] = 0;

		for (int i = 1; i < pattern.length();) {
			numberOfComparations++;

			if (pattern.charAt(i) == pattern.charAt(currMaxLPS)) {
				currMaxLPS++;
				lps[i] = currMaxLPS;
				i++;
			} else {
				if (currMaxLPS != 0) {

					currMaxLPS = lps[currMaxLPS - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
	}

	int kmp(String pattern, String txt) {
		int cantidad = 0;

		int lps[] = new int[pattern.length()];
		funcionError(pattern, lps);

		int txtPos = 0, patternPos = 0;
		while (txtPos < txt.length()) {
			numberOfComparations++;

			if (pattern.charAt(patternPos) == txt.charAt(txtPos)) {
				patternPos++;
				txtPos++;
			}

			if (patternPos == pattern.length()) {
				cantidad++;

				patternPos = lps[patternPos - 1];
			}

			else if (txt.length() > txtPos && pattern.charAt(patternPos) != txt.charAt(txtPos)) {
				if (patternPos != 0) {
					patternPos = lps[patternPos - 1];
				} else
					txtPos++;
			}
		}

		return cantidad;
	}

	@Override
	protected int search(String pattern, String text) {

		int cantidad = 0;

		cantidad = kmp(pattern, text);

		return cantidad;
	}
}