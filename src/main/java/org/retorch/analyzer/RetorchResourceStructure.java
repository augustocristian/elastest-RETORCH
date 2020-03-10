package main.java.org.retorch.analyzer;

import java.util.LinkedList;
import java.util.List;

public class RetorchResourceStructure {
	List <RetorchResource> listResources;
	String testname;
	public RetorchResourceStructure(String testName,String resources) {
		this.listResources= new LinkedList<>();
		this.testname=testName;
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
				if(current!=null)
					current.addAtribute(dat);

			}
			}

		}
		if(current!=null) {
			listResources.add(current);
		}
	}
	public List<RetorchResource> getListResources() {
		return listResources;
	}
	public void setListResources(List<RetorchResource> listResources) {
		this.listResources = listResources;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}

}


