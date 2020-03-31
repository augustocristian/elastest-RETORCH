package test.java;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import main.java.executor.RetorchClassifier;
import test.resources.HeavyTestCases;

public class TestClassifier {
	RetorchClassifier classifier ;

	@Before
	public void setup() {
		
		classifier = new RetorchClassifier();
	}
	
	
	@Test
	public void testdummy() {
		assertEquals(true, true);
	}


	@Test
	public void testGetOneClassMethods() {



		


		List<Method> listMethods= classifier.getClassMethods(HeavyTestCases.class);
		LinkedList<String> namesMethods= new LinkedList<String>();
		namesMethods.add("testOneH");
		namesMethods.add("testTwoH");
		namesMethods.add("testThreeH");




		assertEquals(true,containsAllTestCases(namesMethods,listMethods));
		assertEquals(true,listMethods.size()==namesMethods.size());





	}
	@Test
	public void testGetAllPackageMethods () {
		
		LinkedList<String> namesMethods= new LinkedList<String>();
		namesMethods.add("testOneH");
		namesMethods.add("testTwoH");
		namesMethods.add("testThreeH");
		namesMethods.add("testOneL");
		namesMethods.add("testTwoL");
		namesMethods.add("testThreeL");
		namesMethods.add("testOneM");
		namesMethods.add("testTwoM");
		namesMethods.add("testThreeM");
		
		List<Method> listMethods= classifier.getPackageMethods ("/test/resources");
		
		assertEquals(true, this.containsAllTestCases(namesMethods, listMethods));
		assertEquals(true,listMethods.size()==namesMethods.size());
		
		
		
		
	}
	
	


	public boolean containsAllTestCases(List<String> namesMethods, List<Method> listMethods ) {


		boolean isEqual=true;

		for (Method m : listMethods) {

			if(!namesMethods.contains(m.getName())) {
				isEqual=false;

			}	

		}
		return isEqual;
	}


}
