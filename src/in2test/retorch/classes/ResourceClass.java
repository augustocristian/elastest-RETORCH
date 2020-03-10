package in2test.retorch.classes;

import java.util.LinkedList;

public class ResourceClass {

	public String resourceID ; 
	public enum type {PHYSICAL,LOGICAL,COMPUTATIONAL};
	public ResourceClass hierarchyParent;
	public LinkedList<ResourceClass> reemplazable;
	public ElasticityModelClass elasticityModel;
	
}
