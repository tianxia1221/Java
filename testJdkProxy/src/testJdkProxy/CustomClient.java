package testJdkProxy;
public class CustomClient {
    public static void main(String[] args){
        ProductService productService = new ProductServiceImpl();
        ProductService proxy = (ProductService) new CustomInvocationHandler().getInstance(productService);
        proxy.addProduct("huawei");
    }
}