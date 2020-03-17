package in2test.retorch.executor;
import org.junit.Test;
import java.lang.reflect.Method;
import java.util.LinkedList;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import in2test.retorch.classes.TestCaseClass;

public class RetorchTestRunner  extends Runner {
		 
	    private Class testClass;
	    private LinkedList<TestCaseClass>testCases;
	    public RetorchTestRunner(LinkedList<TestCaseClass> testClass) {
	        super();
	        this.testCases = testClass;
	    }
	 
	    @Override
	    public Description getDescription() {
	        return Description
	          .createTestDescription(testClass, "My runner description");
	    }
	 
	    @Override
	    public void run(RunNotifier notifier) {
	    	
	    	for (TestCaseClass tc : testCases) {
	    	String testName=tc.name;
	    	testClass=tc.testcase;
	        System.out.println("running the tests from MyRunner: " + testClass);
	        try {
	            Object testObject = testClass.newInstance();
	            
	            for (Method method : testClass.getMethods()) {
	            	boolean nameequal=method.getName().equals(testName);
	            	
	                if (method.isAnnotationPresent( Test.class)&&nameequal) {
	                	
	                    notifier.fireTestStarted(Description
	                      .createTestDescription(testClass, method.getName()));
	                    
	                    method.invoke(testObject);
	                    
	                    notifier.fireTestFinished(Description
	                      .createTestDescription(testClass, method.getName()));
	                    
	                    
	                }
	            }
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    	}
	    }
	}
	

