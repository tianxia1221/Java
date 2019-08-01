package testJdkProxy;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

public class $Proxy0 implements testJdkProxy.ProductService {
	private InvocationHandler h;

	public $Proxy0(InvocationHandler h) {
		this.h = h;
	}

	public void addProduct(String productName) {
		try {
			Method m = testJdkProxy.ProductService.class.getMethod("addProduct", Class.forName("java.lang.String"));
			this.h.invoke(this, m, new Object[] { productName });
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}