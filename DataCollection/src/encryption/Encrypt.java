package encryption;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sorting.Sort;

public class Encrypt {

	
	int n;
	
	public byte[] aesEncrypt(byte[] input){
		 try{
		    	KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
				// create a key
				SecretKey secretkey = keygenerator.generateKey();
			    // create a cipher based upon Blowfish
			    Cipher cipher = Cipher.getInstance("AES");
		
			    // initialise cipher to with secret key
			    cipher.init(Cipher.ENCRYPT_MODE, secretkey);
		
			    // encrypt message
			    byte[] output = cipher.doFinal(input);
			    return output;
		    } catch (Exception e){
		    	System.out.println("Error: " + e);
		    	byte[] error = {0};
		    	return error;
		    }
	}
	
	/*Code found at http://stackoverflow.com/questions/5244950/encryption
	 * -with-blowfish-in-java*/
	public byte[] blowfishEncrypt(byte[] input){
		
	    try{
	    	KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
			// create a key
			SecretKey secretkey = keygenerator.generateKey();
		    // create a cipher based upon Blowfish
		    Cipher cipher = Cipher.getInstance("Blowfish");
	
		    // initialise cipher to with secret key
		    cipher.init(Cipher.ENCRYPT_MODE, secretkey);
	
		    // encrypt message
		    byte[] output = cipher.doFinal(input);
		    return output;
	    } catch (Exception e){
	    	System.out.println("Error: " + e);
	    	byte[] error = {0};
	    	return error;
	    }
	    
	}
	/*null encryption*/
	public byte[] arrayCopyEncrypt(byte[] input){
		byte[] output = new byte[input.length]; 
		System.arraycopy( input, 0, output, 0, output.length);
		return output;
	}
	
	public byte[] arrayGenerator(int n){
		byte[] output = new byte[(int) Math.pow(2,n)];
		Random rnd = new Random();
		byte[] first = new byte[1];
		rnd.nextBytes(first);
		output[0] = first[0];
		
		for (int i = 1; i < Math.pow(2,n); i++){
			byte[] next = new byte[1];
			rnd.nextBytes(next);
			output[i] = next[0];
		}
		return output;
	}
	
	public int getNumber(){
		return(n);
	}
	
	public static void main(String[] args) {
		
		Encrypt s = new Encrypt();
		
		int[] number = {8,10,12,14}; 
		for( int length : number){
			for (int i = 0; i < 101000; i++){
				s.n = length;
				byte[] input = s.arrayGenerator(length);
				s.aesEncrypt(input);
			}
			for (int i = 0; i < 101000; i++){
				s.n = length;
				byte[] input = s.arrayGenerator(length);
				s.blowfishEncrypt(input);	
			}
			for (int i = 0; i < 101000; i++){
				s.n = length;
				byte[] input = s.arrayGenerator(length);
				s.arrayCopyEncrypt(input);	
			}
		}
		
		
		
	}

}
