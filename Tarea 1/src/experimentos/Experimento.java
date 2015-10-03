package experimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import algoritmos.TextSearcher;

public class Experimento {

	public Experimento(TextSearcher algoritm, Patterns the_patterns, String text, PrintWriter autput_file, String file_path) {
		int matchs;
		String to_print; 
		for ( int l = Patterns.l_i; l <= Patterns.l_f; l++){
			String[] patterns = the_patterns.getPatternsFromLarge(l);
			for ( String pattern : patterns){
				matchs = algoritm.doSearch(pattern, text);
				to_print = file_path + "\t" + matchs + "\t" + algoritm.getNumberOFComparations() + "\t" + algoritm.getTimeOfExecutionInNano() + "\t" + algoritm.getTimeOfExecutionInMill() + "\t" + algoritm.getTimeOfExecutionInDate();
				autput_file.println(to_print);
				System.out.println(to_print);
			}
		}
	}

}
