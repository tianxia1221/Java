package cglib.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;
public class TargetMethodCallbackFilter implements CallbackFilter {
 
    /**
     * 过滤方法
     * 返回的值为数字，代表了Callback数组中的索引位置，要到用的Callback
     */
    public int accept(Method method) {
        if(method.getName().equals("method1")){
            System.out.println("filter method1 ==0");
            return 0;
        }
        if(method.getName().equals("method2")){
            System.out.println("filter method2 ==1");
            return 1;
        }
        if(method.getName().equals("method3")){
            System.out.println("filter method3 ==2");
            return 2;
        }
        
        if(method.getName().equals("method4")){
            System.out.println("filter method4 ==3");
            return 3;
        }
        return 0;
    }
 
}