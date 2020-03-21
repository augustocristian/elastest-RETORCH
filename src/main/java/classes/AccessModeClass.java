package main.java.classes;



public class AccessModeClass {
	public enum type {READONLY,READWRITE,WRITEONLY,DYNAMIC,NOACCESS};
	public type typeofAccessMode;
	public boolean sharing= false;
	public int concurrency=1;
	public ResourceClass resource;
}
