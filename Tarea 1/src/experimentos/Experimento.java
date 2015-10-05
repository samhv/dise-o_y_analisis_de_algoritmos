package experimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import algoritmos.TextSearcher;

public class Experimento {

	public Experimento(TextSearcher algoritm, Patterns the_patterns, String text, PrintWriter output_file,
			String file_path) {
		int matchs;
		String to_print;
		for (int l = Patterns.l_i; l <= Patterns.l_f; l++) {
			String[] patterns = the_patterns.getPatternsFromLarge(l);
			for (String pattern : patterns) {
				matchs = algoritm.doSearch(pattern, text);
				to_print = file_path + "\t" + matchs + "\t" + algoritm.getNumberOFComparations() + "\t"
						+ algoritm.getTimeOfExecutionInNano() + "\t" + algoritm.getTimeOfExecutionInMill() + "\t"
						+ algoritm.getTimeOfExecutionInDate();
				output_file.println(to_print);
				System.out.println(to_print);
			}
		}
	}

	public double calcular_promedio(ArrayList<Integer> datos) {

		double suma = 0;

		for (Integer i : datos) {
			suma += i;
		}

		double promedio = suma / datos.size();

		return promedio;

	}

	public double calcular_varianza(ArrayList<Integer> datos) {

		double sumatoria = 0;
		for (Integer t : datos) {
			sumatoria += Math.pow(t, 2);
		}
		return (sumatoria - datos.size() * Math.pow(calcular_promedio(datos), 2)) / (datos.size() - 1);
	}

	public double calcular_varianza_estimada(ArrayList<Integer> datos) {
		return Math.sqrt(calcular_varianza(datos) / datos.size());
	}

	public double calcular_error(ArrayList<Integer> datos) {
		return (4 * calcular_varianza_estimada(datos) * 100) / calcular_promedio(datos);
	}
}
