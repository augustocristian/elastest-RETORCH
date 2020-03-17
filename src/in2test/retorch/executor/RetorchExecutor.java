package in2test.retorch.executor;

import java.util.LinkedList;

import org.junit.internal.TextListener;
import org.junit.runner.notification.RunNotifier;

import in2test.retorch.classes.SystemClass;

public class RetorchExecutor {
	RunNotifier notifier;
	public LinkedList<SystemClass> retorchSystems;
	
	public RetorchExecutor() {
		this.notifier= new RunNotifier();
		this.retorchSystems=new LinkedList<SystemClass>();
		
	}
	
	public void classifyResources (String pathTestSuite) {} 
	
	public void deployResources () {
		
		
	}
	
	public void executeTestCases() {
		
		for (SystemClass systemretorch : retorchSystems) {
		
		RetorchTestRunner runner= new RetorchTestRunner(systemretorch.testcases);
		this.notifier.addListener(new TextListener(System.out));
		
		runner.run(notifier);
		
		
		
		
		}
	}
	

}
