package testJdkProxy;

public class ProductServiceImpl implements ProductService{
    public void addProduct(String productName) {
        System.out.println("�������"+productName);
    }
}