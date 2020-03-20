package test.java;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import main.java.classes.ElasticityModelClass;
import main.java.classes.ResourceClass;
import main.java.classes.SystemClass;
import main.java.classes.TestCaseClass;
import main.java.data.structures.InterTestOrchestrationScript;
import main.java.executor.RetorchExecutor;
import test.resources.HeavyTestCases;
import test.resources.LightTestCases;
import test.resources.MediumTestCases;

public class TestExecutor {



	@Test
	public void testExecutorGenerateMavenCode() {



		LinkedList<InterTestOrchestrationScript> outputExecutor;

		//Diferent Elasticity Models

		ElasticityModelClass elasModelLight= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduLightElastModel";
		elasModelLight.elasticity=Integer.MAX_VALUE;
		elasModelLight.elasticityCost=0.0;


		ElasticityModelClass elasModelMedium= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduMediumElastModel";
		elasModelMedium.elasticity=5;
		elasModelMedium.elasticityCost=5.0;

		ElasticityModelClass elasModelHeavy= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduHeavyElastModel";
		elasModelHeavy.elasticity=1;
		elasModelHeavy.elasticityCost=Double.MAX_VALUE;


		//Different Resources

		ResourceClass openvidu = new ResourceClass();
		openvidu.resourceID = "Openvidu";
		openvidu.reemplazable=new LinkedList<ResourceClass>();


		ResourceClass openviduLight = new ResourceClass();
		openviduLight.resourceID = "OpenviduLight";
		openviduLight.hierarchyParent=openvidu;
		openviduLight.reemplazable=new LinkedList<ResourceClass>();
		openviduLight.elasticityModel=elasModelLight;


		ResourceClass openviduMedium = new ResourceClass();
		openviduMedium.resourceID = "OpenviduMedium";
		openviduMedium.hierarchyParent=openvidu;
		openviduMedium.reemplazable=new LinkedList<ResourceClass>();
		openviduMedium.elasticityModel=elasModelMedium;


		ResourceClass openviduHeavy = new ResourceClass();
		openviduHeavy.resourceID = "OpenviduHeavy";
		openviduHeavy.hierarchyParent=openvidu;
		openviduHeavy.reemplazable=new LinkedList<ResourceClass>();
		openviduHeavy.elasticityModel=elasModelHeavy;


		//Reemplazable clasess;

		openvidu.reemplazable.add(openviduLight);
		openvidu.reemplazable.add(openviduMedium);
		openvidu.reemplazable.add(openviduHeavy);

		openviduLight.reemplazable.add(openviduMedium);
		openviduLight.reemplazable.add(openviduHeavy);

		openviduMedium.reemplazable.add(openviduHeavy);



		RetorchExecutor executor =  new RetorchExecutor();


		SystemClass systemLight= new SystemClass("LightSystem");
		systemLight.resources.add(openviduLight);
		
		
		//Adding Light TestCases
		TestCaseClass testCaseLight= new TestCaseClass("testOne", LightTestCases.class);
		systemLight.testcases.add(testCaseLight);



		SystemClass systemMedium= new SystemClass("MediumSystem");
		systemMedium.resources.add(openviduMedium);
		
		
		//Adding Medium test cases
		TestCaseClass testCaseMedium= new TestCaseClass("testThree", MediumTestCases.class);
		systemMedium.testcases.add(testCaseMedium);

		//End TestCases

		SystemClass systemHeavy= new SystemClass("HeavySystem");
		systemHeavy.resources.add(openviduMedium);
		TestCaseClass testCaseHeavy= new TestCaseClass("testTwo", HeavyTestCases.class);
		systemMedium.testcases.add(testCaseHeavy);



		executor.retorchSystems.add(systemLight);

		executor.retorchSystems.add(systemMedium);

		executor.retorchSystems.add(systemHeavy);

		
		outputExecutor=(LinkedList<InterTestOrchestrationScript>) executor.generateMavenScripts();
		
		
		






		//Here the expected output of our system
		LinkedList<InterTestOrchestrationScript> expectedoutput = new LinkedList<InterTestOrchestrationScript>();

		InterTestOrchestrationScript scriptTestsLight = new InterTestOrchestrationScript();
		scriptTestsLight.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCourseMainTest,"
				+ "CourseStudentTest#studentCourseMainTest,LoggedForumTest#forumLoadEntriesTest,LoggedLinksTests#spiderLoggedTest,"
				+ "UserTest#loginTest -B -DforkCount=0 test\r\n" ;
		scriptTestsLight.resourcesCompose="";
		
		
		InterTestOrchestrationScript scriptTestsMedium = new InterTestOrchestrationScript();
		scriptTestsMedium.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCreateAndDeleteCourseTest,"
				+ "CourseTeacherTest#teacherEditCourseValues,CourseTeacherTest#teacherDeleteCourseTest,LoggedForumTest#forumNewEntryTest,"
				+ "LoggedForumTest#forumNewCommentTest,LoggedForumTest#forumNewReply2CommentTest,FullTeachingTestE2EREST#courseRestOperations,"
				+ "FullTeachingTestE2EREST#courseInfoRestOperations,FullTeachingTestE2EREST#sessionRestOperations,FullTeachingTestE2EREST#forumRestOperations,"
				+ "FullTeachingTestE2EREST#filesRestOperations -B -DforkCount=0 test\r\n" + 
				"\r\n" + 
				"" ;
		scriptTestsMedium.resourcesCompose="";
		
		InterTestOrchestrationScript scriptTestsHeavy = new InterTestOrchestrationScript();
		scriptTestsHeavy.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=FullTeachingTestE2EChat#oneToOneChatInSessionChrome,"
				+ "FullTeachingTestE2EVideoSession#oneToOneVideoAudioSessionChrome,LoggedVideoSession#sessionTest,"
				+ "FullTeachingTestE2EREST#attendersRestOperations -B -DforkCount=0 test\r\n" + 
				"\r\n" ;
		scriptTestsHeavy.resourcesCompose="";
		
		
		
		expectedoutput.add(scriptTestsLight);
		expectedoutput.add(scriptTestsMedium);
		expectedoutput.add(scriptTestsHeavy);
		
		
				
		//END
		
		
		assertEquals(areIdenticalLists(outputExecutor, expectedoutput),true);


	}



	public boolean areIdenticalLists(List<InterTestOrchestrationScript> one ,List<InterTestOrchestrationScript> two ) {
		if (one.size()!=two.size()) {
			return false;
		}


		return true;


	}
}
