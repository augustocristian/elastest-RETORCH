package main.java.executor;

import java.io.File;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class RetorchClassifier {

	public List<Method> getPackageMethods(String sourcePath) {

		LinkedList<Method> allPackageMethods = new LinkedList<Method>();
		
		LinkedList< String> clasessnames= new LinkedList<>();

		
		File classfolder = new File(sourcePath);
		File[] listofallfiles = classfolder.listFiles();
		if(listofallfiles.length==0) {
			return allPackageMethods;
		}
		for (File f:listofallfiles) {
			if (!f.getName().endsWith(".java")) {
				continue;
			}

			Class<? extends Object> name = f.getClass();
			
			allPackageMethods.addAll(this.getClassMethods(name));
			

		}
		

		return allPackageMethods;

	}

	public List<Method> getClassMethods(Class testClass) {

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
