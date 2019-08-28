package main.java.org.retorch.analyzer;

import java.util.LinkedList;

public class RetorchResource {
	
	private String name;
	private LinkedList<String> attributes;
	public RetorchResource(String name) {
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
	
public boolean equals(RetorchResource a) {
	return this.name.equals(a.getName())&&this.attributes.containsAll(a.attributes);
		
	}
}
