package in2test.retorch.tags;
import java.lang.annotation.Documented;

@Documented
public @interface Resource {

	public String resourceID () default "Some URL"; 
	public enum type {PHYSICAL,LOGICAL,COMPUTATIONAL};
	public String hierarchyParent () default "None";
	public String [] reemplazable () default "None";
	public ElasticityModel elasticityModel () ;
	

	
}
