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
		String x;
		for(int i=0; i<numero_de_patrones;i++) {
			x="";
			for(int j=0;j<l;j++) {
				x += Integer.toString(randomWord.nextInt(2));
			}
			to_return[i]=x;
		}
		return to_return;
	}

}
