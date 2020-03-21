package main.java.executor;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import main.java.classes.AccessModeClass;
import main.java.classes.ResourceClass;
import main.java.classes.SystemClass;
import main.java.classes.TestCaseClass;
import main.java.data.structures.InterTestOrchestrationScript;

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
				testClass=tc.testcClass;
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



	public List<InterTestOrchestrationScript> generateMavenScripts() {

		LinkedList<InterTestOrchestrationScript > listScripts = new  LinkedList<>();
		for (SystemClass systemretorch : retorchSystems) {

		InterTestOrchestrationScript dummy = new InterTestOrchestrationScript();
		dummy.resourcesCompose="";
		dummy.mavenCommand=getMvnScript(systemretorch);
		listScripts.add(dummy);

		}



		return listScripts;

	}

	public String getMvnScript (SystemClass system) {
		StringBuilder strBuilder = new StringBuilder();
		String application="mvn ";
		strBuilder.append(application);
		String arguments ="-Dapp.url=https://localhost:5001/ ";
		strBuilder.append(arguments);
		strBuilder.append(this.getFormattedTestCases(system.testcases));
		String option="-B ";
		strBuilder.append(option);
		String parallelism=String.format("-DforkCount=%d ", getConcurrency(system.testcases));		
		strBuilder.append(parallelism);
		String endScript="test\r\n" ;
		strBuilder.append(endScript);
		
		return strBuilder.toString();
	}

	public String getFormattedTestCases(List <TestCaseClass> testCases){
		StringBuilder strBuilder = new StringBuilder();
		String parameterSpecfTests="-Dtest=";
		strBuilder.append(parameterSpecfTests);
		boolean firstElement= true;
		String testClassName;
		for (TestCaseClass testcase : testCases) {

			if(firstElement) {
				firstElement=false;
			}
			else {
				strBuilder.append(",");
			}
			testClassName=testcase.testcClass.getSimpleName();
			strBuilder.append(testClassName);
			strBuilder.append("#");
			strBuilder.append(testcase.name);
		}
		strBuilder.append(" ");
		return strBuilder.toString();

	}
	
	public int getConcurrency (LinkedList<TestCaseClass> listTestCaseClasses) {
		AccessModeClass commonAccess = listTestCaseClasses.getFirst().accessMode;
		
		if(commonAccess.sharing) 
			return commonAccess.concurrency;
		
		return 0;
		
		
	}


}
