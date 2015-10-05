package experimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import algoritmos.BoyerMooreV2;
import algoritmos.FuerzaBruta;
import algoritmos.KMP;
import algoritmos.TextSearcher;

public class Main {
	public static void main(String[] args) throws IOException, URISyntaxException { 
		
		String alfabeto_binario_path = "AlfabetoBinario";
		String adn_real_path = "ADNReal";
		String adn_sintético = "ADNSintetico";
		String lenguaje_natural_real_path = "InglesReal";
		String lenguaje_natural_sintetico_path = "InglesSintetico";
		
		TextSearcher fuerza_bruta = new FuerzaBruta();;
		TextSearcher kmp = new KMP();
		TextSearcher bm = new BoyerMooreV2();
		
		String fuerza_bruta_output_path = "FuerzaBrutaOuput";
		String kmp_output_path = "KMPOutput";
		String bm_output_path = "BMOutput";
		
		PrintWriter[] outputs = {
				new PrintWriter(fuerza_bruta_output_path, "UTF-8"),
				new PrintWriter(kmp_output_path, "UTF-8"),
				new PrintWriter(bm_output_path, "UTF-8")
		};
		
		TextSearcher[] algoritms = {
				fuerza_bruta,
				kmp,
				bm
		};
		
		String[] files = { 
				alfabeto_binario_path, 
				adn_real_path, 
				adn_sintético, 
				lenguaje_natural_real_path, 
				lenguaje_natural_sintetico_path 
		};
		
		int numero_de_patrones = 1;
		Integer[] patterns = { 
				0, 
				1,
				1,
				1,
				1
		};
		
		Hashtable<String, Integer> patternsHash = new Hashtable<String, Integer>();
		for(int i=0;i<files.length;i++){
			patternsHash.put(files[i], patterns[i]);
		}
		
		Hashtable<TextSearcher, PrintWriter> outputs_path_hash = new Hashtable<TextSearcher, PrintWriter>();
		for(int i=0;i<algoritms.length;i++){
			outputs_path_hash.put(algoritms[i], outputs[i]);
		}
		
		Integer pattern;
		PrintWriter output_file;
		String text;
		for (String file_path : files) {
			text = getText(file_path); 
			for (TextSearcher algoritm : algoritms){
				System.out.println(file_path + " " + algoritm.toString());
				pattern = patternsHash.get(file_path);
				output_file = outputs_path_hash.get(algoritm);
				Experimento e = new Experimento(algoritm,text,output_file,file_path,pattern,5);
				e.go();
			}
		}
		
		for (PrintWriter output : outputs){
			output.close();
		}
	
	}
	
	public static String getText(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
		
}
