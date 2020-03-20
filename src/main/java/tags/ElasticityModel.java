package main.java.tags;

import java.lang.annotation.Documented;

@Documented
public @interface ElasticityModel {
	public String elasticityID () default "DefaultId"; 
	public int elasticity () default 1;
	public double elasticityCost () default 5.0;
	

	
}
