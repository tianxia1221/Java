package testJdkProxy;

import java.io.FileOutputStream;
import sun.misc.ProxyGenerator; 

@SuppressWarnings("restriction")
public class Test {
    public static void main(String[] args) throws Exception {
        ProductService productService = new ProductServiceImpl();
        ProductService proxy = (ProductService) new JdkInvocationHandler().getInstance(productService);
        proxy.addProduct("iphone");


        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{productService.getClass()});

        FileOutputStream os = new FileOutputStream("Proxy0.class");
        os.write(bytes);
        os.close();
    }
}