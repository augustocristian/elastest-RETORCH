package main.java.executor;

import java.io.IOException;
import java.util.Set;

import org.reflections.Reflections;

import com.google.common.reflect.ClassPath;

import main.java.tags.AccessMode;
import main.java.tags.ElasticityModel;

public class RetorchClassifier {



	public void getAllMethods () throws IOException {

	//	final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Reflections ref = new Reflections("main");
        for (Class<?> cl : ref.getTypesAnnotatedWith(AccessMode.class)) {
            
            System.out.printf("Found class: %s, with meta nam",
                    cl.getSimpleName());
        }
    
		
		
		/*
		 * for (final ClassPath.ClassInfo info :
		 * ClassPath.from(loader).getTopLevelClasses()) { if
		 * (info.getName().startsWith("test.resources")) { final Class<?> clazz =
		 * info.load(); // do something with your clazz } }
		 */
		//Reflections reflections = new Reflections("test.resources");
		//Set<Class<? extends Object>> allClasses = 
			//	reflections.getSubTypesOf(Object.class);

	//	System.out.print(allClasses.toString());	
		
	}



}


