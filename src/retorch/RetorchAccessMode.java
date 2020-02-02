package retorch;

import java.lang.annotation.Documented;

@Documented
public @interface RetorchAccessMode {
	public enum type {READONLY,READWRITE,WRITEONLY,DYNAMIC,NOACCESS} ;

}
