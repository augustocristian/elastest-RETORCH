package retorch;

import java.lang.annotation.Documented;

@Documented
public @interface RetorchStaticAttributes {
	public int elasticity () default  0;
	public String [] hierarchyMembers() default {"None"};
	public boolean shareableResource () default true;
	public String replaceableResource () default "SomeResourceID";
	public String [] lifeCyclePhases () default {"SET-UP","EXECUTION","DISPOSE"};
	
	

}
