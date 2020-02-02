package main;

import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

import main.java.org.retorch.analyzer.CommentJavaDocAnalyzer;
import main.java.org.retorch.analyzer.RetorchResource;
import main.java.org.retorch.analyzer.RetorchResourceStructure;


public class MainClass {
	public static void main(String [] args) {
		Logger log = Logger.getLogger(MainClass.class.getName());
		String teacherpath="C:\\Users\\crist\\Desktop\\full-teaching-tunon-tests\\e2e-test\\no-Elastest\\src\\test\\java\\com\\fullteaching\\e2e\\no_elastest\\functional\\test\\teacher\\";
		String teacherclassname="CourseTeacherTest.java";		
		String pathold = "C:\\Users\\crist\\Desktop\\full-teaching-tunon-tests\\e2e-test\\no-Elastest\\src\\test\\java\\com\\fullteaching\\e2e\\no_elastest\\functional\\test\\";

		String className="UserTest.java";
		LinkedList<String> listclases=(LinkedList<String>) CommentJavaDocAnalyzer.getProjectClasses(pathold,className);




		LinkedList<RetorchResourceStructure> lista =(LinkedList<RetorchResourceStructure>) CommentJavaDocAnalyzer.getResources(teacherpath);
		for (RetorchResourceStructure it :lista) {
			log.fine(String.format("Test Name : %s  \n Resources: %n",it.getTestname()));
			for(RetorchResource res:it.getListResources()) {
				log.fine(String.format("Name : %s  Type : ",res.getName() ));
				for (String type:res.getAttributes()) {
					log.fine(type + ",");
				}
				log.fine("\n");

			}


		}

		Map<String,LinkedList <String>> pruebados= CommentJavaDocAnalyzer.getGroupedResources(lista);
		log.fine("end");

		String salida=CommentJavaDocAnalyzer.getStagesElastest(pruebados,"openvidu");

		log.fine(salida);

	}





}
