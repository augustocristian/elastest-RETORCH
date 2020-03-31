package test.java;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import main.java.executor.RetorchClassifier;
import test.resources.HeavyTestCases;

public class TestClassifier {

	
	@Test
	public void testdummy() {
		assertEquals(true, true);
	}
	
	
	@Test
	public void testGetOneClassMethods() {
		
		
		
		RetorchClassifier classifier = new RetorchClassifier();
		
		
		List<Method> listMethods= classifier.getClassMethods(HeavyTestCases.class);
		LinkedList<String> namesMethods= new LinkedList<String>();
		namesMethods.add("testOne");
		namesMethods.add("testTwo");
		namesMethods.add("testThree");
		
		boolean isEqual=true;
		
		for (Method m : listMethods) {
			
			if(!namesMethods.contains(m.getName())) {
				isEqual=false;
				
			}	
			
		}
		assertEquals(true,isEqual);
		assertEquals(true,listMethods.size()==namesMethods.size());
		
		
		
		
		
	}
	
	
}
