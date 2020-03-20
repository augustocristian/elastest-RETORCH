package main.java.tags;

import java.lang.annotation.Documented;

@Documented
public @interface AccessMode {
	public enum type {READONLY,READWRITE,WRITEONLY,DYNAMIC,NOACCESS};
	public boolean sharing() default false;
}
