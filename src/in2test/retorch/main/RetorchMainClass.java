package in2test.retorch.main;
import java.util.LinkedList;

import in2test.retorch.classes.*;
import in2test.retorch.executor.RetorchExecutor;

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



		RetorchExecutor executor =  new RetorchExecutor();


		SystemClass systemLight= new SystemClass("LightSystem");
		systemLight.resources.add(openviduLight);


		SystemClass systemMedium= new SystemClass("MediumSystem");
		systemMedium.resources.add(openviduMedium);

		
		SystemClass systemHeavy= new SystemClass("HeavySystem");
		systemHeavy.resources.add(openviduMedium);
		
		
		executor.retorchSystems.add(systemLight);
		
		executor.retorchSystems.add(systemMedium);
		
		executor.retorchSystems.add(systemHeavy);














	}

}
