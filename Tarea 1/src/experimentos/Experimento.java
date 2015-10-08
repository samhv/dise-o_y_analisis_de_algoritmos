package experimentos;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import algoritmos.TextSearcher;

public class Experimento {

	TextSearcher algoritm;
	String text;
	PrintWriter output_file;
	String file_path;
	Integer pattern;
	Integer error_permitido;
	
	public Experimento(TextSearcher algoritm, String text, PrintWriter output_file, String file_path, Integer pattern, Integer error_permitido) {
		this.algoritm=algoritm;
		this.text=text;
		this.output_file=output_file;
		this.file_path=file_path;
		this.pattern=pattern;
		this.error_permitido=error_permitido;
	}	
	
	public ArrayList<Integer> muestras_comparaciones;
	public ArrayList<Integer> muestras_tiempo;
	
	public void go(){	
		muestras_tiempo = new ArrayList<Integer>();
		muestras_comparaciones = new ArrayList<Integer>();
		Patterns the_patterns;
		Integer matchs;
		String to_print;
		double prom;
		double sigma;
		String a;
		String b;
		String c;
		int muestras_itarcion=100;
		for ( int l = Patterns.l_i; l <= Patterns.l_f; l++){
			System.out.println("l= "+l);
			muestras_tiempo = new ArrayList<Integer>();
			muestras_comparaciones = new ArrayList<Integer>();
			while(calcular_error(muestras_tiempo)>error_permitido){
				System.out.print("t:"+calcular_error(muestras_tiempo)+" ");
				if(pattern==1){
					the_patterns=new TextPatterns(muestras_itarcion,text);					
				}
				else{
					the_patterns=new BinaryPatterns(muestras_itarcion);
				}
				String[] patterns = the_patterns.getPatternsFromLarge(l);
				for ( String pattern : patterns){
					matchs = algoritm.doSearch(pattern, text);
					muestras_tiempo.add((int) (algoritm.getTimeOfExecutionInNano()));
					muestras_comparaciones.add(matchs);
				}
			}
			System.out.println("t:"+calcular_error(muestras_tiempo)+" ");
			while(calcular_error(muestras_comparaciones)>error_permitido){
				System.out.print("c:"+calcular_error(muestras_comparaciones)+" ");
				if(pattern==1){
					the_patterns=new TextPatterns(muestras_itarcion,text);					
				}
				else{
					the_patterns=new BinaryPatterns(muestras_itarcion);
				}
				String[] patterns = the_patterns.getPatternsFromLarge(l);
				for ( String pattern : patterns){
					matchs = algoritm.doSearch(pattern, text);
					//System.out.println(matchs);
					muestras_comparaciones.add(matchs);
				}
			}
			if(Double.isNaN(calcular_error(muestras_tiempo)))
				System.out.print("nan");
			if(Double.isNaN(calcular_error(muestras_comparaciones)))
				System.out.print("nan");
			System.out.println("c:"+calcular_error(muestras_comparaciones)+" ");
			prom = calcular_promedio(muestras_tiempo);
			sigma = calcular_varianza_estimada(muestras_tiempo);
			to_print=  "t:\t" + (prom-2*sigma) + "\t" + (prom+2*sigma) + "\t" + 2*sigma*100+ "\t"+ prom + "\t" + l + "\t" + file_path;
			output_file.println(to_print);
			prom = calcular_promedio(muestras_comparaciones);
			sigma = calcular_varianza_estimada(muestras_comparaciones);
			to_print=  "c:\t" + (prom-2*sigma) + "\t" + (prom+2*sigma) + "\t" + 2*sigma*100+ "\t"+ prom+ "\t" + l + "\t" + file_path;
			output_file.println(to_print);
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
		double prom = calcular_promedio(datos);
		for (Integer t : datos) {
			sumatoria += Math.pow(t, 2);
		}
		//return sumatoria/(datos.size()-1);
		return (sumatoria - datos.size() * Math.pow(calcular_promedio(datos), 2)) / (datos.size() - 1);
	}

	public double calcular_varianza_estimada(ArrayList<Integer> datos) {
		return Math.sqrt(calcular_varianza(datos) / datos.size());
	}

	public double calcular_error(ArrayList<Integer> datos) {
		if(datos.isEmpty()){
			return 100;
		}
		return (2 * calcular_varianza_estimada(datos)) * 100 / calcular_promedio(datos);
	}
}
