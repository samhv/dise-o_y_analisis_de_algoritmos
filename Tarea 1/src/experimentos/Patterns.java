package experimentos;

import java.time.Duration;
import java.time.Instant;
import java.util.Hashtable;

public abstract class Patterns {
	
	private Hashtable<Integer, String[]> patterns;
	public static int l_i=2;
	public static int l_f=7;
	
	public Patterns(int numero_de_patrones){
		patterns = new Hashtable<Integer, String[]>();
		String[] pattern;
		for(int l=l_i;l<=l_f;l++){
			pattern = this.generatePattern(numero_de_patrones,l); 
			patterns.put(l, pattern);
		}
	}
	
	protected abstract String[] generatePattern(int numero_de_patrones, int l);

	public String[] getPatternsFromLarge(int l) {
		return patterns.get(l);
		
	}
	
}
