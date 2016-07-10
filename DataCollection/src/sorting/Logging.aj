package sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public aspect Logging {

	double startTime;
	double endTime;
	String methodName; 
	String className;

	/**/
	pointcut log(Sort s):
		target(s)
		&& (execution(* Sort.insertionSort(..))
		|| execution (* Sort.dualPivotSort(..)));
	
	before(Sort s):log(s){
		startTime = System.currentTimeMillis();
	}
	
	after(Sort s):log(s){
		endTime = System.currentTimeMillis();
		methodName = thisJoinPointStaticPart.getSignature().getName();
		className = thisJoinPointStaticPart.getSignature().getDeclaringType()
					.getName();
		String fileName = "data/" + className + "." + methodName + "_" + s.getNumber() + ".txt";
		File file = new File(fileName);
		
		try{
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter out = new FileWriter(file,true);
		double delta = endTime-startTime;
		out.write( delta + "\n");
		out.close();
		}catch(IOException e){
			System.out.println("Error: " + e.getMessage());
		}
	}
}
