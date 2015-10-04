package experimentos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class TextGenerator {
	public static void main(String[] args) throws IOException{
		//createInglesSintetico();
		//createBinaryText();
		//createADNsintetico();
		//makeSimpleAlfabet();
		//getMoreShortFile("DNAReal",1);
		//getMoreShortFile("InglesReal",2);
		//makeSimpleAlfabet("InglesReal");
	}
	
	public static String getText(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public static void example() throws IOException{
		String x = getText("example");
		System.out.println(x.length());
	} 
	
	public static void createBinaryText() throws FileNotFoundException, UnsupportedEncodingException {
		createRandomAlfabetText((new char[]{'0','1'}),"AlfabetoBinario");
	}
	
	public static void createRandomAlfabetText(char[] alfabeto, String nameFile) throws FileNotFoundException, UnsupportedEncodingException {
		int size = 1048576;
		Random randomWord = new Random();
		StringBuilder x = new StringBuilder();
		for(int i=0; i<size;i++) {		
			x.append(alfabeto[randomWord.nextInt(alfabeto.length)]);
		}
		PrintWriter p = new PrintWriter(nameFile, "UTF-8");
		p.println(x);
		p.close();
	}
	
	public static void getMoreShortFile(String name_file, int number_of_megas) throws IOException{
		int size = 1048576 * number_of_megas;
		FileReader fr = new FileReader(name_file);
		BufferedReader br = new BufferedReader(fr);
		char c;
		PrintWriter p = new PrintWriter(name_file + "_short", "UTF-8");
		for (int i=0; i < size; i++) {
			c = (char) br.read();
			p.print(c);
		}		
		br.close();
		fr.close();
		p.close();
	}
	
	public static void makeSimpleAlfabet(String name_file) throws IOException{
		int size = 1048576;
		char[] alf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ".toCharArray();
		FileReader fr = new FileReader(name_file);
		BufferedReader br = new BufferedReader(fr);
		char c;
		PrintWriter p = new PrintWriter(name_file + "_simple", "UTF-8");
		boolean contain;
		int current_size=0;
		while(true) {
			c = (char) br.read();
			contain=false;
			for(char char_alf : alf){
				if(char_alf == c){
					contain=true;
				}
			}
			if(contain){
				p.print(c);
				current_size++;
			}
			else{
				while(!contain){
					c = (char) br.read();
					for(char char_alf : alf){
						if(char_alf == c){
							contain=true;
						}
					}
				}
				p.print(" ");
				current_size++;
				if(current_size >= size){
					break;
				}
				p.print(c);
				current_size++;
			}
			if(current_size >= size){
				break;
			}
		}		
		br.close();
		fr.close();
		p.close();
	}
	
	
	
	public static void createADNsintetico() throws FileNotFoundException, UnsupportedEncodingException {
		createRandomAlfabetText((new char[]{'G','C','A','T'}),"ADNSintetico");
	}
	
	public static void createInglesSintetico() throws FileNotFoundException, UnsupportedEncodingException{
		char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPRSTUVWXYZ ".toCharArray();
		createRandomAlfabetText(alphabet,"InglesSintetico");
	}
	
}
