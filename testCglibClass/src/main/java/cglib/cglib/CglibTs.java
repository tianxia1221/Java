package cglib.cglib;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;
 
public class CglibTs implements MethodInterceptor{
 
	private Enhancer enhancer = new Enhancer();
	
	
	public Object getProxy(Class clazz){
//		enhancer.setSuperclass(clazz);
//		enhancer.setCallback(this);
		enhancer.setSuperclass(clazz);
        CallbackFilter callbackFilter = new TargetMethodCallbackFilter();
        
        /**
         * (1)callback1：方法拦截器
           (2)NoOp.INSTANCE：这个NoOp表示no operator，即什么操作也不做，代理类直接调用被代理的方法不进行拦截。
           (3)FixedValue：表示锁定方法返回值，无论被代理类的方法返回什么值，回调方法都返回固定值。
         */
        Callback noopCb=NoOp.INSTANCE;
       // Callback callback1=new TargetInterceptor();
        Callback callback1= this;
        Callback callback2 = new TargetInterceptorOther();
        Callback fixedValue=new TargetResultFixed();
        Callback[] cbarray=new Callback[]{callback1,noopCb,fixedValue, callback2};
        //enhancer.setCallback(new TargetInterceptor());
        enhancer.setCallbacks(cbarray);
        enhancer.setCallbackFilter(callbackFilter);
		return enhancer.create();
	}
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("&&&intercept " + method.getName()+"调用前");
		//一不小心写成下面被注释一行代码了。 StackOverflowError
		//Object result = method.invoke(obj, args); 想不通
		Object result = proxy.invokeSuper(obj,args);
		System.out.println("&&&intercept " + method.getName()+"调用前");
		return result;
	}
	
}