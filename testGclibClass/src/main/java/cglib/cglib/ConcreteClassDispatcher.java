package cglib.cglib;

import net.sf.cglib.proxy.Dispatcher;

public class ConcreteClassDispatcher implements Dispatcher{
    static int a = 0;
    public Object loadObject() throws Exception {
        System.out.println("before Dispatcher...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("xxx" + a);
        propertyBean.setValue(new TargetObject());
        System.out.println("after Dispatcher...");
        a++;
        return propertyBean;
    }
 
}