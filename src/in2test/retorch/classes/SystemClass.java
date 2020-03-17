package in2test.retorch.classes;

import java.util.LinkedList;

public class SystemClass {
		public String name;
		public LinkedList <testCaseClass> testcases;
		public LinkedList<ResourceClass> resources;
		public SystemClass (String name) {
			this.name=name;
			this.testcases= new LinkedList<testCaseClass>();
			this.resources= new LinkedList<ResourceClass>();
		}
		

}
