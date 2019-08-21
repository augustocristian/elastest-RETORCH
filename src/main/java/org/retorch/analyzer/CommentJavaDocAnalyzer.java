package main.java.org.retorch.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
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
                public void visit(ClassOrInterfaceDeclaration n, Object arg) {
                    super.visit(n, arg);
                    if (n.getComment() != null && n.getComment() instanceof JavadocComment) {
                        String title = String.format("%s (%s)", n.getName(), path);
                        System.out.println(title);
                        System.out.println(Strings.repeat("=", title.length()));
                        System.out.println(n.getComment());
                    }
                }
            }.visit(JavaParser.parse(file), null);
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }).explore(projectDir);

}

}
