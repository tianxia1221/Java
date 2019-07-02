package cglib.cglib;

public class testLazyBean {
	public static void main(String[] args) {
		LazyBean loader=new LazyBean("tom", 18);  
		System.out.println(loader.getName());  
		System.out.println(loader.getAge());  
		PropertyBean propertyBean=loader.getPropertyBean();//访问延迟加载对象  
		System.out.println(propertyBean.getKey());  
		System.out.println(propertyBean.getValue());  
		System.out.println("after...");  
		//当再次访问延迟加载对象时,就不会再执行回调了  
		System.out.println(propertyBean.getKey());  
		
		
		
		 propertyBean=loader.getPropertyBeanDispatcher();//访问延迟加载对象  
		System.out.println(propertyBean.getKey());  
		System.out.println(propertyBean.getValue());  
		System.out.println("after...");  
		//当再次访问延迟加载对象时,就不会再执行回调了  
		System.out.println(propertyBean.getKey());  
	}

}
