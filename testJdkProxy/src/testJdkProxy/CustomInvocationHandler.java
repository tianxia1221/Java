package testJdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomInvocationHandler implements InvocationHandler {
    private ProductService target;

    public Object getInstance(ProductService target){
        this.target = target;
        Class clazz = this.target.getClass();
        // ����1������������������ ����2:��������Ľӿ� ����3
        // �����MyClassLoader����new�ķ�ʽ��֤���벻����������޸�
        return MyProxy.newProxyInstance(new MyClassLoader(),
                clazz.getInterfaces(),
                this);
    }
	@Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate  = simpleDateFormat.format(new Date());
        System.out.println("���ڡ�"+currentDate + "�������һ���Ʒ");

        return method.invoke(this.target,args);
    }



}
