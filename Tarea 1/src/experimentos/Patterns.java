package experimentos;

import java.util.Hashtable;

public abstract class Patterns {
	
	private Hashtable<Integer, String[]> patterns;
	protected String text;
	public static int l_i=2;
	public static int l_f=2;
	
	public Patterns(int numero_de_patrones, String text){
		this.text=text;
		patterns = new Hashtable<Integer, String[]>();
		String[] pattern;
		for(int l=l_i;l<=l_f;l++){
			pattern = this.generatePattern(numero_de_patrones,(int) Math.pow(2,l)); 
			patterns.put(l, pattern);
		}
	}
	
	protected abstract String[] generatePattern(int numero_de_patrones, int l);

	public String[] getPatternsFromLarge(int l) {
		return patterns.get(l);
		
	}
	
}
