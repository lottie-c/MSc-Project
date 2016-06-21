package individual_project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class HelloWorld {

	
	public HelloWorld(){
		
	}
	
	public void helloWorld() throws InterruptedException{
		
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("enter a String");
		String str = keyboard.nextLine();
		
		BufferedWriter out = null;
		try {
			File file = new File("testfile.txt");
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fstream = new FileWriter(file.getAbsoluteFile());
			out = new BufferedWriter(fstream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Can't open file");
		}
		
		
		
		Random rand = new Random();
		for (int i=0; i < str.length(); i++){
			int sleep = rand.nextInt(5000);
			Thread.sleep(sleep);
			System.out.print(str.charAt(i));
			try {
				out.write(str.charAt(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println("Error: " + e.getMessage());
				
			}
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("\n");
		boolean loop = true;
		
		while(loop==true){
			System.out.println("Do you want to continue?");
			String ans = keyboard.nextLine();
			if ((ans.equals("no"))||(ans.equals("n"))){
				loop = false; 
			}	
		}
		keyboard.close();
		return;
	}
	
	public static void main(String[] args) throws InterruptedException{
		HelloWorld run = new HelloWorld(); 
		run.helloWorld();
	}
	
}
