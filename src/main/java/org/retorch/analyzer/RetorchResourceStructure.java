package main.java.org.retorch.analyzer;

import java.util.LinkedList;

public class RetorchResourceStructure {
	LinkedList <Resource> listResources;
	String testname;
	public RetorchResourceStructure(String TestName,String resources) {
		this.listResources= new LinkedList<Resource>();
		this.testname=TestName;
		Resource current=null;
		String []  splitedresources= resources.split(" ");
		for (String dat:splitedresources) {
			if(!dat.equals("")) {
			if(dat.contains("@")) {
				if(listResources.isEmpty()&&current==null) {
					current= new Resource(dat);
				}
				else {
					listResources.add(current);
					current= new Resource(dat);
				}

			}

			else {
				current.addAtribute(dat);

			}
			}

		}
	}

}


