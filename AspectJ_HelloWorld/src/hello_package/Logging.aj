package hello_package;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public aspect Logging {

	
	long startTime;
	long endTime;
	String methodName; 
	String className;
	
	pointcut log():
		//all functions in HelloWorld class
		execution(* HelloWorld.hello*(..));
		
	before():log(){
		startTime = System.currentTimeMillis();
	}
	
	after():log(){
		endTime = System.currentTimeMillis();
		methodName = thisJoinPointStaticPart.getSignature().getName();
		className = thisJoinPointStaticPart.getSignature().getDeclaringType()
					.getName();
		String fileName = "data/" + className + "." + methodName + ".txt";
		File file = new File(fileName);
		
		try{
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter out = new FileWriter(file,true);
		long delta = endTime-startTime;
		out.write( delta + "\n");
		out.close();
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
	}
		
		
	
	
}
