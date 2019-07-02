package testAspectJ;

public aspect HelloAspect {
	 
	pointcut HelloWorldPointCut() : execution(* testAspectJ.HelloWorldApp.main(..));
	
	
	
	before() : HelloWorldPointCut(){
		System.out.println("before Hello world");
	}
	
	after() : HelloWorldPointCut(){
		System.out.println("after Hello world");
	}

}