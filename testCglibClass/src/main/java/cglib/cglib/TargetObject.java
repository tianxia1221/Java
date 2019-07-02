package cglib.cglib;


public class TargetObject {
	
    public String method1(String paramName) {
    	System.out.println("TargetObject:method1 " + paramName);
        return paramName;
    }
 
    public int method2(int count) {
    	System.out.println("TargetObject:method2 " + count);
        return count;
    }
 
    public int method3(int count) {
    	System.out.println("TargetObject:method3 " +count);
        return count;
    }
    
    public int method4(int count) {
    	System.out.println("TargetObject:method4 " + count);
        return count;
    }
 
    @Override
    public String toString() {
        return "TargetObject []"+ getClass();
    }
}