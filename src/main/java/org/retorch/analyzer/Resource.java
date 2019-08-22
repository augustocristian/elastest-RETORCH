package main.java.org.retorch.analyzer;

import java.util.LinkedList;

public class Resource {
	
	private String name;
	private LinkedList<String> attributes;
	public Resource(String name) {
		attributes= new LinkedList<String>();
		this.name=name;
		
			
	}
	public void addAtribute(String newer) {
		attributes.add(newer);
	}
	public LinkedList<String> getAttributes(){
		return attributes;
	}
	public String getName() {
		return name;
	}

}
