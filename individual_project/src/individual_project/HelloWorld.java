package individual_project;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class HelloWorld {

	private String str = "Hello World!";
	
	public HelloWorld(){
		
	}
	
	public void hello1(){
		
		Random rand = new Random();
		for (int i=0; i < str.length(); i++){
			/*sleep for a random time, 0 - 10 seconds*/
			int time = rand.nextInt(10000);
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				
				System.err.println("Error: " + e.getMessage());
			}
			System.out.print(str.charAt(i));
			
		}
		System.out.print("\n");
		return;
	}
	
	
	public void hello2(){
		for(int i=0; i<str.length(); i++){
			int time = 1000;
			try{
			Thread.sleep(time);
			}catch(InterruptedException e){
				System.err.println("Error: " + e.getMessage());				
			}
			System.out.print(str.charAt(i));
		}
		System.out.print("\n");
		return;
	}
	
	public void hello3(){
		System.out.print(str + "\n");
		return;
	}
	
	/*Linked list uses memory storing objects and get and add 
	 * run in O(n) time*/
	public void hello4(){
		LinkedList<Character> list = new LinkedList<Character>();
		for (int i = 0; i< str.length(); i++){
			list.add(str.charAt(i));
		}
		for(int i = 0; i < str.length(); i++){
		System.out.print(list.get(i));
		}
		System.out.print("\n");
		return;
	}
	
	/*Using ArrayList get and add run in amortised O(1) time*/
	public void hello5(){
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i< str.length(); i++){
			list.add(str.charAt(i));
		}
		for(int i = 0; i < str.length(); i++){
		System.out.print(list.get(i));
		}
		System.out.print("\n");
		return;
	}
	
	public static void main(String[] args) throws InterruptedException{
		HelloWorld run = new HelloWorld(); 
		run.hello1();
		run.hello4();
		run.hello5();
		return;
	}
	
}
