package in2test.retorch.executor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import junit.framework.Test;

public class RetorchTestRunner  extends Runner {
		 
	    private Class testClass;
	    public RetorchTestRunner(Class testClass) {
	        super();
	        this.testClass = testClass;
	    }
	 
	    @Override
	    public Description getDescription() {
	        return Description
	          .createTestDescription(testClass, "My runner description");
	    }
	 
	    @Override
	    public void run(RunNotifier notifier) {
	        System.out.println("running the tests from MyRunner: " + testClass);
	        try {
	            Object testObject = testClass.newInstance();
	            for (Method method : testClass.getMethods()) {
	                if (method.isAnnotationPresent((Class<? extends Annotation>) Test.class)) {
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
	

