package main;

import java.util.ArrayList;
import java.util.Random;

public class StringGenerator{

	//private int max;
	
	
	/*Create an instance of StringGenerator and parse the arguments
	 * to get the max */
	public StringGenerator(){
	}
	
	public ArrayList<Object[]> generate(int length){
		
		ArrayList<Object[]> output = new ArrayList<Object[]>();
		
			String str[] = new String[1];
			Random rnd = new Random();
			
			Character c = (char)(rnd.nextInt(26) + 'a');
			String s = c.toString();
			str[0] = s;
			for (int j = 0; j < length; j++){
				
				char next = (char)(rnd.nextInt(26) + 'a');
				str[0] += next;
			}
			//Object obj[] = new Object[1];
			//obj[0] = str;
			output.add(str);
			
		
		return output;
		
	}

	/*
	public ArrayList<Object[]> generate(int count, int length){
	
		ArrayList<Object[]> output = new ArrayList<Object[]>();
		for (int i = 0; i < count; i++ ){
			String str[] = new String[1];
			Random rnd = new Random();
			
			Character c = (char)(rnd.nextInt(26) + 'a');
			String s = c.toString();
			str[0] = s;
			for (int j = 0; j < length; j++){
				
				char next = (char)(rnd.nextInt(26) + 'a');
				str[0] += next;
			}
		
			output.add(str);
			
		}
		return output;
		
	}*/
	/*
	public static void main(String[] args){
		StringGenerator test = new StringGenerator();
		int count = 3;
		int length = 6;
		ArrayList<String> output = test.generate(count, length);
		
		for (int i = 0; i < count; i++ ){
			System.out.println("string " + i + " is:");
			System.out.println(output.get(i));
		}
	}
	*/
}

