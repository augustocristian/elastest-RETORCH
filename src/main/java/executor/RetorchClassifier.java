package main.java.executor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RetorchClassifier {

		
		   
	public List<Method>  getClassMethods (Class testClass) {

		    // Get the methods
		    Method[] methods = testClass.getDeclaredMethods();
		    LinkedList<Method> output = new LinkedList<Method>();
		    // Loop through the methods and print out their names
		    for (Method method : methods) {
		      System.out.println(method.getName());
		      output.add(method);
		    }
		
		    return output;
		   
		    
		
		
	}



}


