package experimentos;

import java.util.Random;

public class TextPatterns extends Patterns {

	private String text;
	
	public TextPatterns(int numero_de_patrones, String text) {
		super(numero_de_patrones);
		this.text=text;
	}

	@Override
	protected String[] generatePattern(int numero_de_patrones, int l) {
		int text_length = text.length();
		String[] to_return = new String[numero_de_patrones];
		Random randomWord = new Random();
		int a;
		for(int i=0; i<numero_de_patrones;i++) {
			a = randomWord.nextInt(text_length-l+1);
			to_return[i]=text.substring(a,a+l);
		}
		return to_return;
	}

}
