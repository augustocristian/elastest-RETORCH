package retorch;

import java.lang.annotation.Documented;

@Documented
public @interface RetorchResourceId {
	public String resourceId () default "";
	public enum deploymentWay {NORMAL,CONTENERIZED};

}
