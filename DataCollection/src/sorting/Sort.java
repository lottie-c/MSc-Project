package sorting;

import java.util.Random;
import java.util.Arrays;

public class Sort {

	int[] a;
	int number;
	
	private void generateArray(int n){
		Random rnd = new Random();
		int[] output = new int[n];
		
		for (int i = 0; i < n ; i++){
			output[i] = rnd.nextInt(100);
		}
		
		a = output;
		return;
	}
	
	private void insertionSort(){
		
		for (int i = 1; i < a.length; i++){
			int j = i;
			while ( j > 0 && a[j-1] > a[j]){
				swap(a, j-1, j);
				j = j-1;
			}
		}
		return;
		
	}
	
	public static int[] swap(int[] A, int i,int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		return A;
	}
	
	private void dualPivotSort(){
		Arrays.sort(a);
		return;
	}

	public int[] getA(){
		return a;
	}
	
	public int getNumber(){
		return number;
	}
	
	public static void main(String[] args){
		Sort s = new Sort();
		
		int[] n = {100,500,1000,5000}; 
		for( int number : n){
			for (int i = 0; i < 101000; i++){
				s.number = number;
				s.generateArray(number);
				s.insertionSort();
			}
			for (int i = 0; i < 101000; i++){
				s.number = number;
				s.generateArray(number);
				s.dualPivotSort();
			}
		}
		
	}
}
