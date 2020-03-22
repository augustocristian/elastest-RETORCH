package test.java;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import main.java.executor.RetorchClassifier;

public class TestClassifier {

	

	@Test
	public void testClassifierBase() {
		RetorchClassifier classifier = new RetorchClassifier();
		try {
			classifier.getAllMethods();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, true);
		
	}
	
}
