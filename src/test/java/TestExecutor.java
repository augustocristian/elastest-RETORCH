package test.java;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import main.java.classes.AccessModeClass;
import main.java.classes.ElasticityModelClass;
import main.java.classes.ResourceClass;
import main.java.classes.SystemClass;
import main.java.classes.TestCaseClass;
import main.java.data.structures.InterTestOrchestrationScript;
import main.java.executor.RetorchExecutor;

import test.resources.functional.LoggedForumTest;
import test.resources.functional.LoggedLinksTests;
import test.resources.functional.UserTest;
import test.resources.functional.media.FullTeachingTestE2EChat;
import test.resources.functional.media.FullTeachingTestE2EREST;
import test.resources.functional.media.FullTeachingTestE2EVideoSession;
import test.resources.functional.media.LoggedVideoSession;
import test.resources.student.CourseStudentTest;
import test.resources.teacher.CourseTeacherTest;

public class TestExecutor {


	@Test
	public void testTestCasesFormat() {

		SystemClass systemHeavy= new SystemClass("HeavySystem");
		//Adding Heavy test cases

		//AccessMode for the Light Resources

		AccessModeClass accessModeHeavy = new AccessModeClass();
		//End AccessMode

		systemHeavy.testcases.add(new  TestCaseClass("oneToOneChatInSessionChrome",FullTeachingTestE2EChat.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("oneToOneVideoAudioSessionChrome",FullTeachingTestE2EVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("sessionTest",LoggedVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("attendersRestOperations",FullTeachingTestE2EREST.class,accessModeHeavy ));

		RetorchExecutor executor =  new RetorchExecutor();
		String output=executor.getFormattedTestCases(systemHeavy.testcases);
		String expectedOutput = "-Dtest=FullTeachingTestE2EChat#oneToOneChatInSessionChrome,"
				+ "FullTeachingTestE2EVideoSession#oneToOneVideoAudioSessionChrome,LoggedVideoSession#sessionTest,"
				+ "FullTeachingTestE2EREST#attendersRestOperations ";

		assertEquals(expectedOutput, output);

	}

	@Test
	public void testConcurrencyFunction() {

		RetorchExecutor executor =  new RetorchExecutor();

		ElasticityModelClass elasModelHeavy= new ElasticityModelClass();
		elasModelHeavy.elasticityID= "OpenViduHeavyElastModel";
		elasModelHeavy.elasticity=1;
		elasModelHeavy.elasticityCost=Double.MAX_VALUE;
		//Different Resources
		ResourceClass openviduHeavy = new ResourceClass();
		openviduHeavy.resourceID = "OpenviduHeavy";
		openviduHeavy.reemplazable=new LinkedList<ResourceClass>();
		openviduHeavy.elasticityModel=elasModelHeavy;


		SystemClass systemHeavy= new SystemClass("HeavySystem");
		systemHeavy.resources.add(openviduHeavy);
		//Adding Heavy test cases

		//AccessMode for the Light Resources

		AccessModeClass accessModeHeavy = new AccessModeClass();
		accessModeHeavy.sharing=false;
		accessModeHeavy.concurrency=1;
		accessModeHeavy.resource=openviduHeavy;
		accessModeHeavy.typeofAccessMode=AccessModeClass.type.READWRITE;

		//End AccessMode

		systemHeavy.testcases.add(new  TestCaseClass("oneToOneChatInSessionChrome",FullTeachingTestE2EChat.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("oneToOneVideoAudioSessionChrome",FullTeachingTestE2EVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("sessionTest",LoggedVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("attendersRestOperations",FullTeachingTestE2EREST.class,accessModeHeavy ));

		int outputconcurrency=executor.getConcurrency(systemHeavy.testcases);
		int expectedconcurrency= 0;
		assertEquals(expectedconcurrency, outputconcurrency);
		


	}

	@Test
	public void testExecutorgetMvnScript() {

		ResourceClass openvidu = new ResourceClass();
		openvidu.resourceID = "Openvidu";
		openvidu.reemplazable=new LinkedList<ResourceClass>();


		//Diferent Elasticity Models

		ElasticityModelClass elasModelHeavy= new ElasticityModelClass();
		elasModelHeavy.elasticityID= "OpenViduHeavyElastModel";
		elasModelHeavy.elasticity=1;
		elasModelHeavy.elasticityCost=Double.MAX_VALUE;
		//Different Resources
		ResourceClass openviduHeavy = new ResourceClass();
		openviduHeavy.resourceID = "OpenviduHeavy";
		openviduHeavy.hierarchyParent=openvidu;
		openviduHeavy.reemplazable=new LinkedList<ResourceClass>();
		openviduHeavy.elasticityModel=elasModelHeavy;


		RetorchExecutor executor =  new RetorchExecutor();

		SystemClass systemHeavy= new SystemClass("HeavySystem");
		systemHeavy.resources.add(openviduHeavy);
		//Adding Heavy test cases

		//AccessMode for the Light Resources

		AccessModeClass accessModeHeavy = new AccessModeClass();
		accessModeHeavy.sharing=false;
		accessModeHeavy.concurrency=1;
		accessModeHeavy.resource=openviduHeavy;
		accessModeHeavy.typeofAccessMode=AccessModeClass.type.READWRITE;

		//End AccessMode

		systemHeavy.testcases.add(new  TestCaseClass("oneToOneChatInSessionChrome",FullTeachingTestE2EChat.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("oneToOneVideoAudioSessionChrome",FullTeachingTestE2EVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("sessionTest",LoggedVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("attendersRestOperations",FullTeachingTestE2EREST.class,accessModeHeavy ));

		executor.retorchSystems.add(systemHeavy);

		String expectedOutput = "mvn -Dapp.url=https://localhost:5001/ -Dtest=FullTeachingTestE2EChat#oneToOneChatInSessionChrome,"
				+ "FullTeachingTestE2EVideoSession#oneToOneVideoAudioSessionChrome,LoggedVideoSession#sessionTest,"
				+ "FullTeachingTestE2EREST#attendersRestOperations -B -DforkCount=0 test\r\n" ;
		String output = executor.getMvnScript(systemHeavy);
		assertEquals(expectedOutput, output);



	}



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
		//AccessMode for the Light Resources

		AccessModeClass accessModeLight = new AccessModeClass();
		accessModeLight.sharing=true;
		accessModeLight.concurrency=4;
		accessModeLight.resource=openviduLight;
		accessModeLight.typeofAccessMode=AccessModeClass.type.READONLY;

		//End AccessMode

		//Adding Light TestCases
		systemLight.testcases.add(new  TestCaseClass("teacherCourseMainTest",CourseTeacherTest.class,accessModeLight ));
		systemLight.testcases.add(new  TestCaseClass("studentCourseMainTest",CourseStudentTest.class,accessModeLight ));
		systemLight.testcases.add(new  TestCaseClass("forumLoadEntriesTest",LoggedForumTest.class,accessModeLight ));		
		systemLight.testcases.add(new  TestCaseClass("spiderLoggedTest",LoggedLinksTests.class,accessModeLight ));
		systemLight.testcases.add(new  TestCaseClass("loginTest",UserTest.class,accessModeLight ));





		//End Adding Light Test Cases

		SystemClass systemMedium= new SystemClass("MediumSystem");
		systemMedium.resources.add(openviduMedium);

		//AccessMode for the Light Resources

		AccessModeClass accessModeMedium = new AccessModeClass();
		accessModeMedium.sharing=false;
		accessModeMedium.concurrency=1;
		accessModeMedium.resource=openviduMedium;
		accessModeMedium.typeofAccessMode=AccessModeClass.type.READWRITE;

		//End AccessMode




		//Adding Medium test cases
		systemMedium.testcases.add(new  TestCaseClass("teacherCreateAndDeleteCourseTest",CourseTeacherTest.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("teacherEditCourseValues",CourseTeacherTest.class ,accessModeMedium));
		systemMedium.testcases.add(new  TestCaseClass("teacherDeleteCourseTest",CourseTeacherTest.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("forumNewEntryTest",LoggedForumTest.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("forumNewCommentTest",LoggedForumTest.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("forumNewReply2CommentTest",LoggedForumTest.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("courseRestOperations",FullTeachingTestE2EREST.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("courseInfoRestOperations",FullTeachingTestE2EREST.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("sessionRestOperations",FullTeachingTestE2EREST.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("forumRestOperations",FullTeachingTestE2EREST.class,accessModeMedium ));
		systemMedium.testcases.add(new  TestCaseClass("filesRestOperations",FullTeachingTestE2EREST.class,accessModeMedium ));

		//End Adding Medium Test Cases

		SystemClass systemHeavy= new SystemClass("HeavySystem");
		systemHeavy.resources.add(openviduHeavy);

		//AccessMode for the Light Resources

		AccessModeClass accessModeHeavy = new AccessModeClass();
		accessModeHeavy.sharing=false;
		accessModeHeavy.concurrency=1;
		accessModeHeavy.resource=openviduHeavy;
		accessModeHeavy.typeofAccessMode=AccessModeClass.type.READWRITE;

		//End AccessMode


		//Adding Heavy test cases

		systemHeavy.testcases.add(new  TestCaseClass("oneToOneChatInSessionChrome",FullTeachingTestE2EChat.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("oneToOneVideoAudioSessionChrome",FullTeachingTestE2EVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("sessionTest",LoggedVideoSession.class,accessModeHeavy ));
		systemHeavy.testcases.add(new  TestCaseClass("attendersRestOperations",FullTeachingTestE2EREST.class,accessModeHeavy ));

		//End Adding Heavy Test Cases



		executor.retorchSystems.add(systemLight);

		executor.retorchSystems.add(systemMedium);

		executor.retorchSystems.add(systemHeavy);


		outputExecutor=(LinkedList<InterTestOrchestrationScript>) executor.generateMavenScripts();









		//Here the expected output of our system
		LinkedList<InterTestOrchestrationScript> expectedoutput = new LinkedList<InterTestOrchestrationScript>();

		InterTestOrchestrationScript scriptTestsLight = new InterTestOrchestrationScript();
		scriptTestsLight.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCourseMainTest,"
				+ "CourseStudentTest#studentCourseMainTest,LoggedForumTest#forumLoadEntriesTest,LoggedLinksTests#spiderLoggedTest,"
				+ "UserTest#loginTest -B -DforkCount=4 test\r\n" ;
		scriptTestsLight.resourcesCompose="";


		InterTestOrchestrationScript scriptTestsMedium = new InterTestOrchestrationScript();
		scriptTestsMedium.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=CourseTeacherTest#teacherCreateAndDeleteCourseTest,"
				+ "CourseTeacherTest#teacherEditCourseValues,CourseTeacherTest#teacherDeleteCourseTest,LoggedForumTest#forumNewEntryTest,"
				+ "LoggedForumTest#forumNewCommentTest,LoggedForumTest#forumNewReply2CommentTest,FullTeachingTestE2EREST#courseRestOperations,"
				+ "FullTeachingTestE2EREST#courseInfoRestOperations,FullTeachingTestE2EREST#sessionRestOperations,FullTeachingTestE2EREST#forumRestOperations,"
				+ "FullTeachingTestE2EREST#filesRestOperations -B -DforkCount=0 test\r\n";
		scriptTestsMedium.resourcesCompose="";

		InterTestOrchestrationScript scriptTestsHeavy = new InterTestOrchestrationScript();
		scriptTestsHeavy.mavenCommand= "mvn -Dapp.url=https://localhost:5001/ -Dtest=FullTeachingTestE2EChat#oneToOneChatInSessionChrome,"
				+ "FullTeachingTestE2EVideoSession#oneToOneVideoAudioSessionChrome,LoggedVideoSession#sessionTest,"
				+ "FullTeachingTestE2EREST#attendersRestOperations -B -DforkCount=0 test\r\n" ;
		scriptTestsHeavy.resourcesCompose="";



		expectedoutput.add(scriptTestsLight);
		expectedoutput.add(scriptTestsMedium);
		expectedoutput.add(scriptTestsHeavy);



		//END


		assertEquals(areIdenticalLists(outputExecutor, expectedoutput),true);


	}



	public boolean areIdenticalLists(List<InterTestOrchestrationScript> one ,List<InterTestOrchestrationScript> two ) {
		if (one.size()!=two.size()||!one.containsAll(two)) {
			return false;
		}





		return true;


	}
}
