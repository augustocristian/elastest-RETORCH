package main.java.org.retorch.analyzer;

import java.util.LinkedList;

public class RetorchResourceStructure {
	LinkedList <RetorchResource> listResources;
	String testname;
	public RetorchResourceStructure(String TestName,String resources) {
		this.listResources= new LinkedList<RetorchResource>();
		this.testname=TestName;
		RetorchResource current=null;
		String []  splitedresources= resources.split(" ");
		for (String dat:splitedresources) {
			if(!dat.equals("")) {
			if(dat.contains("@")) {
				if(listResources.isEmpty()&&current==null) {
					current= new RetorchResource(dat);
				}
				else {
					listResources.add(current);
					current= new RetorchResource(dat);
				}

			}

			else {
				
				current.addAtribute(dat);

			}
			}

		}
		if(current!=null) {
			listResources.add(current);
		}
	}
	public LinkedList<RetorchResource> getListResources() {
		return listResources;
	}
	public void setListResources(LinkedList<RetorchResource> listResources) {
		this.listResources = listResources;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}

}


