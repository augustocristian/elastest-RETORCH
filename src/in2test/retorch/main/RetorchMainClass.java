package in2test.retorch.main;
import java.util.LinkedList;

import in2test.retorch.classes.*;

public class RetorchMainClass {

	public static void main(String[] args) {
		
		//Diferent Elasticity Models
		
		ElasticityModelClass elasModelLight= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduLightElastModel";
		elasModelLight.elasticity=Integer.MAX_VALUE;
		elasModelLight.elasticityCost=0.0;
		
		
		ElasticityModelClass elasModelMedium= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduMediumElastModel";
		elasModelMedium.elasticity=5;
		elasModelMedium.elasticityCost=5.0;
		
		ElasticityModelClass elasModelHeavy= new ElasticityModelClass();
		elasModelLight.elasticityID= "OpenViduHeavyElastModel";
		elasModelHeavy.elasticity=1;
		elasModelHeavy.elasticityCost=Double.MAX_VALUE;
		
		
		//Different Resources

		ResourceClass openvidu = new ResourceClass();
		openvidu.resourceID = "Openvidu";
		openvidu.reemplazable=new LinkedList<ResourceClass>();
		
		
		ResourceClass openviduLight = new ResourceClass();
		openviduLight.resourceID = "OpenviduLight";
		openviduLight.hierarchyParent=openvidu;
		openviduLight.reemplazable=new LinkedList<ResourceClass>();
		openviduLight.elasticityModel=elasModelLight;
		
		
		ResourceClass openviduMedium = new ResourceClass();
		openviduMedium.resourceID = "OpenviduMedium";
		openviduMedium.hierarchyParent=openvidu;
		openviduMedium.reemplazable=new LinkedList<ResourceClass>();
		openviduMedium.elasticityModel=elasModelMedium;
		
		
		ResourceClass openviduHeavy = new ResourceClass();
		openviduHeavy.resourceID = "OpenviduHeavy";
		openviduHeavy.hierarchyParent=openvidu;
		openviduHeavy.reemplazable=new LinkedList<ResourceClass>();
		openviduHeavy.elasticityModel=elasModelHeavy;
		
		
		//Reemplazable clasess;
		
		openvidu.reemplazable.add(openviduLight);
		openvidu.reemplazable.add(openviduMedium);
		openvidu.reemplazable.add(openviduHeavy);
		
		openviduLight.reemplazable.add(openviduMedium);
		openviduLight.reemplazable.add(openviduHeavy);
		
		openviduMedium.reemplazable.add(openviduHeavy);
		
		
		

		
		
		
		
	}

}
