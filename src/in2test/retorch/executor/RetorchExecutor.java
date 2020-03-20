package in2test.retorch.executor;

import java.lang.reflect.Method;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import in2test.retorch.classes.SystemClass;
import in2test.retorch.classes.TestCaseClass;

public class RetorchExecutor {
	RunNotifier notifier;
	public LinkedList<SystemClass> retorchSystems;
	private Class testClass;
	public RetorchExecutor() {
		this.notifier= new RunNotifier();
		this.retorchSystems=new LinkedList<SystemClass>();

	}

	public void classifyResources (String pathTestSuite) {} 

	public void deployResources () {


	}

	public void executeTestCases() {

		for (SystemClass systemretorch : retorchSystems) {

			for (TestCaseClass tc : systemretorch.testcases) {
				String testName=tc.name;
				testClass=tc.testcase;
				System.out.println("running the tests from MyRunner: " + testClass);
				try {
					Object testObject = testClass.newInstance();

					for (Method method : testClass.getMethods()) {
						boolean nameequal=method.getName().equals(testName);

						if (method.isAnnotationPresent( Test.class)&&nameequal) {
							String nameclass= testClass.getName();
							String name=method.getName();
							Request request = Request.method(Class.forName(testClass.getName()),name);

							Result result = new JUnitCore().run(request);
							//	System.exit(result.wasSuccessful() ? 0 : 1);
							   for (Failure failure : result.getFailures()) {
						            System.out.println(failure.toString());
						        }
						        System.out.println("Successful: " + result.wasSuccessful() + " ran " + result.getRunCount() + " tests");
						    


						}
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}



		}
	}


}
