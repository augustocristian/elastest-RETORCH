package retorch;

import java.lang.annotation.Documented;


@Documented
public @interface RetorchDynamicAttributes {
public String allocated () default "Some URL"; 
public String [] measurableOIndicators () default {"system"};
public double elasticityCost() default 0.0;
public String [] idTestInstance() default "0x00";
public enum availability {RENEWABLE,NONRENEWABLE};
	

}
