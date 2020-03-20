package main.java.data.structures;

public class InterTestOrchestrationScript {
public String resourcesCompose;
public String mavenCommand;

@Override
public boolean equals(Object o) { 
  
    if (o == this) { 
        return true; 
    } 

    if (!(o instanceof InterTestOrchestrationScript)) { 
        return false; 
    } 
      

    InterTestOrchestrationScript c = (InterTestOrchestrationScript) o; 
      
    // Compare the data members and return accordingly  
    return resourcesCompose.equals(c.resourcesCompose) && mavenCommand.equals(c.mavenCommand); 
} 
} 
