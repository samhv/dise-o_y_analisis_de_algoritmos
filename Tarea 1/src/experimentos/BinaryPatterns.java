package experimentos;

import java.util.Random;

public class BinaryPatterns extends Patterns {

	public BinaryPatterns(int numero_de_patrones) {
		super(numero_de_patrones);
	}

	@Override
	protected String[] generatePattern(int numero_de_patrones, int l) {
		String[] to_return = new String[numero_de_patrones];
		Random randomWord = new Random();
		StringBuilder x = new StringBuilder();
		for(int i=0; i<numero_de_patrones;i++) {
			x = new StringBuilder();
			for(int j=0;j<l;j++) {
				x.append(Integer.toString(randomWord.nextInt(2)));
			}
			to_return[i]=x.toString();
		}
		return to_return;
	}

}
