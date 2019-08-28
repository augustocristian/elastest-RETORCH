package main.java.org.retorch.analyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.google.common.base.Strings;

public class CommentJavaDocAnalyzer {

	public static LinkedList<String> getProjectClasses(String path,String className){
		LinkedList< String> clasessnames= new LinkedList<String>();

		String rootpath=path.substring(0, path.lastIndexOf("\\"));
		File classfolder = new File(rootpath);
		File[] listofallfiles = classfolder.listFiles();
		if(listofallfiles.length==0) {
			return clasessnames;
		}
		for (File f:listofallfiles) {
			if (!f.getName().endsWith(".java")) {
				continue;
			}

			String name = getClassNameForSource(f.getName(), className);
			if (name != null && !name.equals(className) && !name.contains("package-info")) {
				clasessnames.add(name);
			}	

		}


		return clasessnames;

	}
	private static String getClassNameForSource(String sourceFileName, String analyzedClassName) {
		int lastDot = analyzedClassName.lastIndexOf(".");
		if (lastDot == -1) {
			return null;
		}
		return analyzedClassName.substring(0, lastDot) + "." + sourceFileName.replace(".java", "");
	}


	public static void printallComments(String relativepath) {

		File projectDir = new File(relativepath);
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(JavadocComment comment, Object arg) {
						super.visit(comment, arg);
						String title = null;
						if (comment.getCommentedNode().isPresent()) {
							title = String.format("%s (%s)", describe(comment.getCommentedNode().get()), path);
						} else {
							title = String.format("No element associated (%s)", path);
						}
						System.out.println(title);
						System.out.println(Strings.repeat("=", title.length()));
						System.out.println(comment);
					}
				}.visit(JavaParser.parse(file), null);
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);
	}

	private static String describe(Node node) {
		if (node instanceof MethodDeclaration) {
			MethodDeclaration methodDeclaration = (MethodDeclaration)node;
			return "Method " + methodDeclaration.getDeclarationAsString();

		}
		if (node instanceof ConstructorDeclaration) {
			ConstructorDeclaration constructorDeclaration = (ConstructorDeclaration)node;
			return "Constructor " + constructorDeclaration.getDeclarationAsString();
		}
		if (node instanceof ClassOrInterfaceDeclaration) {
			ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration)node;
			if (classOrInterfaceDeclaration.isInterface()) {
				return "Interface " + classOrInterfaceDeclaration.getName();
			} else {
				return "Class " + classOrInterfaceDeclaration.getName();
			}
		}
		if (node instanceof EnumDeclaration) {
			EnumDeclaration enumDeclaration = (EnumDeclaration)node;
			return "Enum " + enumDeclaration.getName();
		}
		if (node instanceof FieldDeclaration) {
			FieldDeclaration fieldDeclaration = (FieldDeclaration)node;
			List<String> varNames = fieldDeclaration.getVariables().stream().map(v -> v.getName().getId()).collect(Collectors.toList());
			return "Field " + String.join(", ", varNames);
		}
		return node.toString();




	}


	public static LinkedList<RetorchResourceStructure> getResources(String relativepath) {
		LinkedList<RetorchResourceStructure> listresourcestestrequired= new LinkedList<RetorchResourceStructure>();
		File projectDir = new File(relativepath);
		new DirExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
			try {
				new VoidVisitorAdapter<Object>() {
					@Override
					public void visit(JavadocComment comment, Object arg) {
						super.visit(comment, arg);
						String title = null;
						if (comment.getCommentedNode().isPresent()) {
							getTestName(comment.getCommentedNode().get(), comment, listresourcestestrequired);

						}


					}
 				}.visit(JavaParser.parse(file), null);
			} catch (IOException e) {
				new RuntimeException(e);
			}
		}).explore(projectDir);

		return listresourcestestrequired;
	}

	private static String getTestName(Node node,JavadocComment comment,LinkedList<RetorchResourceStructure> listresourcestestrequired) {
		if (node instanceof MethodDeclaration) {
			MethodDeclaration methodDeclaration = (MethodDeclaration)node;
			listresourcestestrequired.add(analyzeComment(String.format("%s",methodDeclaration.getName()),comment.toString()));
			return "Method " + methodDeclaration.getName();

		}
		else {

		}
		return node.toString();




	}

	private static final String VALUE = "value";


	public static RetorchResourceStructure analyzeComment(String testname,String comment) {
		String[] lines = comment.split("\n");
		int i=0;
		RetorchResourceStructure struct=null;
		for (String linea:lines) {
			if(linea.contains("@retorch")) {
				linea=linea.replace("*", "");
				linea=linea.replace("/", "");
				linea=linea.replace("@retorch ","" );
				struct= new RetorchResourceStructure(testname, linea);
				System.out.printf("linea %d "+linea,i);

			}
			i++;

		}
		return struct;


	}

	public static Expression getValueParameter(AnnotationExpr annotationExpr){
		Expression expression = getParamater(annotationExpr, VALUE);
		if(expression == null){
			List<Expression> children = annotationExpr.getNodesByType(Expression.class);
			if(!children.isEmpty()){
				expression = children.get(0);
			}
		}
		return expression;
	}

	public static Expression getParamater(AnnotationExpr annotationExpr, String parameterName){
		List<MemberValuePair>children = annotationExpr.getNodesByType(MemberValuePair.class);
		for(MemberValuePair memberValuePair : children){
			if(parameterName.equals(memberValuePair.getNameAsString())){
				return memberValuePair.getValue();
			}
		}
		return null;
	}

	public static Map<String,LinkedList <String>> getGroupedResources(List<RetorchResourceStructure> struct){
		Map<String,LinkedList <String>> listoutput= new HashMap<String, LinkedList<String>>();
		LinkedList<String> listtests;
		for (RetorchResourceStructure item :struct ) {
			for (RetorchResource res: item.listResources) {

				for (String resattrib:res.getAttributes()) {
					String resourcename= String.format("%s-%s", res.getName(),resattrib);
					if(listoutput.containsKey(resourcename)) {
						listtests= listoutput.get(resourcename);
						listtests.add(item.testname);
						listoutput.put(resourcename,listtests);
					}
					else {
						listtests= new LinkedList<String>();
						listtests.add(item.testname);
						listoutput.put(resourcename,listtests);



					}

				}
			}


		}



		return listoutput;

	}

	public static String getStagesElastest(Map<String,LinkedList <String>> GroupedResources,String resource) {

		StringBuilder output= new StringBuilder();
		StringBuilder testformatted= new StringBuilder();
	    Properties prop=new Properties();
	    try {
	    FileInputStream ip= new FileInputStream("C:\\Users\\crist\\eclipse-workspace\\RETORCH\\src\\resources\\config.properties");

	   
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean init;
		for (Map.Entry<String,LinkedList <String>> entry : GroupedResources.entrySet()) {
			if(entry.getKey().contains(resource)) {
				testformatted= new StringBuilder();
				init=false;
				for(String tests:entry.getValue()) {
					if(init==false) {
						init=true;
						testformatted.append(tests);
					}	
					else {
						testformatted.append(","+tests);

					}
				}
				output.append(String.format("	stage(\"%s\") {\r\n" + 
						"                echo 'Running test'\r\n" + 
						"                mvnHome = %s + "+
						"                sh \"'${mvnHome}/bin/mvn' -Dapp.url=https://\" + sutIp +\":5001/ -Dtest=%s -B -DforkCount=0 test\"\r\n" + 
						"                step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])\r\n" + 
						"         \r\n" + 
						"        	} \n", entry.getKey(),prop.getProperty("MavenHome"),testformatted.toString()));

				

			}

		}


		return output.toString();
	}




}
