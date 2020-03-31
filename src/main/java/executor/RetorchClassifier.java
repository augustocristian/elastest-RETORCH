package main.java.executor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.Action;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import test.resources.HeavyTestCases;

public class RetorchClassifier {

	public List<Method> getPackageMethods(String sourcePath) {

		LinkedList<Method> allPackageMethods = new LinkedList<Method>();
		/*
		 * System.out.print(HeavyTestCases.class.getPackageName());
		 * 
		 * 
		 * 
		 * Reflections reflections = new Reflections(sourcePath);
		 * 
		 * Set<Class<? extends Object>> allClasses =
		 * reflections.getSubTypesOf(Object.class);
		 */

		/*
		 * ClassLoader loader = Thread.currentThread() .getContextClassLoader();
		 * loader.getClass().getName(); ClassPath classpath;
		 * 
		 * ImmutableSet<ClassInfo> allClasses=null; try { classpath =
		 * ClassPath.from(loader);
		 * allClasses=classpath.getTopLevelClassesRecursive(sourcePath); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		 * classLoadersList.add(ClasspathHelper.contextClassLoader());
		 * classLoadersList.add(ClasspathHelper.staticClassLoader());
		 * 
		 * Reflections reflections = new Reflections(new ConfigurationBuilder()
		 * .setScanners(new SubTypesScanner(false don't exclude Object.class ), new
		 * ResourcesScanner())
		 * .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new
		 * ClassLoader[0]))) .filterInputsBy(new
		 * FilterBuilder().include(FilterBuilder.prefix(sourcePath))));
		 */

	//	Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

		/*
		 * for (Class a : allClasses) {
		 * 
		 * allPackageMethods.addAll(this.getClassMethods(a)); }
		 */

		return allPackageMethods;

	}

	public List<Method> getClassMethods(Class testClass) {

		// Get the methods
		Method[] methods = testClass.getDeclaredMethods();
		LinkedList<Method> output = new LinkedList<Method>();
		// Loop through the methods and print out their names
		for (Method method : methods) {
			System.out.println(method.getName());
			output.add(method);
		}

		return output;

	}

}
