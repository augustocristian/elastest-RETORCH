package main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import main.java.org.retorch.analyzer.CommentJavaDocAnalyzer;
import main.java.org.retorch.analyzer.DirExplorer;
import main.java.org.retorch.analyzer.RetorchResource;
import main.java.org.retorch.analyzer.RetorchResourceStructure;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;


public class MainClass {
	public static void main(String [] args) {
		String teacherpath="C:\\Users\\crist\\Desktop\\full-teaching-tunon-tests\\e2e-test\\no-Elastest\\src\\test\\java\\com\\fullteaching\\e2e\\no_elastest\\functional\\test\\teacher\\";
		String teacherclassname="CourseTeacherTest.java";		
		String pathold = "C:\\Users\\crist\\Desktop\\full-teaching-tunon-tests\\e2e-test\\no-Elastest\\src\\test\\java\\com\\fullteaching\\e2e\\no_elastest\\functional\\test\\";

		String className="UserTest.java";
		LinkedList<String> listclases=CommentJavaDocAnalyzer.getProjectClasses(pathold,className);
		for (String cla:listclases) {
			//System.out.print(cla+"\n");
		}


		//CommentJavaDocAnalyzer.printallComments(teacherpath);

		LinkedList<RetorchResourceStructure> lista =CommentJavaDocAnalyzer.getResources(teacherpath);
		for (RetorchResourceStructure it :lista) {
			System.out.print(String.format("Test Name : %s  \n Resources: \n",it.getTestname()));
			for(RetorchResource res:it.getListResources()) {
				System.out.print(String.format("Name : %s  Type : ",res.getName() ));
				for (String type:res.getAttributes()) {
					System.out.print(type + ",");
				}
				System.out.print("\n");

			}


		}

		Map<String,LinkedList <String>> pruebados= CommentJavaDocAnalyzer.getGroupedResources(lista);
		System.out.print("end");

		String salida=CommentJavaDocAnalyzer.getStagesElastest(pruebados,"openvidu");

		System.out.print(salida);

	}





}
