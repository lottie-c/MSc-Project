package encryption;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public aspect Logging {

	double startTime;
	double endTime;
	String methodName; 
	String className;
	
	pointcut log(Encrypt e):
		target(e)
		&&(execution(* Encrypt.aesEncrypt(..))
			|| execution (* Encrypt.blowfishEncrypt(..))
			|| execution (* Encrypt.arrayCopyEncrypt(..)));
	
	before(Encrypt e):log(e){
		startTime = System.currentTimeMillis();
	}
	
	after(Encrypt e):log(e){
		endTime = System.currentTimeMillis();
		methodName = thisJoinPointStaticPart.getSignature().getName();
		className = thisJoinPointStaticPart.getSignature().getDeclaringType()
					.getName();
		String fileName = "data/encryption/" + className + "." + methodName + "_" + e.getNumber() + ".txt";
		File file = new File(fileName);
		
		try{
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter out = new FileWriter(file,true);
		double delta = endTime-startTime;
		out.write( delta + "\n");
		out.close();
		}catch(IOException error){
			System.out.println("Error: " + error.getMessage());
		}
	}
}
