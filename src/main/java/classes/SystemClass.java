package main.java.classes;

import java.util.LinkedList;

public class SystemClass {
		public String name;
		public LinkedList <TestCaseClass> tGroup;
		public LinkedList<ResourceClass> resources;
		public SystemClass (String name) {
			this.name=name;
			this.tGroup= new LinkedList<TestCaseClass>();
			this.resources= new LinkedList<ResourceClass>();
		}
		

}
