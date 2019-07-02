package cglib.cglib;


public class testCGLib {
	public static void main(String[] args) {
		CglibTs ct = new CglibTs();
		TargetObject targetObject2 = (TargetObject) ct.getProxy(TargetObject.class);
        System.out.println("---main: method1(mmm1)\n" + targetObject2.method1("mmm1"));
        System.out.println("---main: method2(200)\n" + targetObject2.method2(200));
        System.out.println("---main: method3(310)\n" + targetObject2.method3(310));
        System.out.println("---main: method3(20)\n" + targetObject2.method3(320));
        System.out.println("---main:method4(400)\n" + targetObject2.method4(400));
        
        //other method except filter method
        System.out.println("other method except filter method");
        System.out.println("---main:targetObject2.equals(null) \n" + targetObject2.equals(null));
        System.out.println("---main: targetObject2.getClass() \n" + targetObject2.getClass());
        
        System.out.println("---main: targetObject2.hashCode() \n" + targetObject2.hashCode());
        System.out.println("---main: targetObject2.toString() \n" + targetObject2.toString());
	}
}